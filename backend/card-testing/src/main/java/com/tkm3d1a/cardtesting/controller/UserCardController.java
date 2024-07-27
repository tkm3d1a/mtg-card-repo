package com.tkm3d1a.cardtesting.controller;

import com.tkm3d1a.cardtesting.entity.AppUser;
import com.tkm3d1a.cardtesting.service.AppUserService;
import com.tkm3d1a.cardtesting.entity.UserCard;
import com.tkm3d1a.cardtesting.service.UserCardService;
import com.tkm3d1a.cardtesting.DTO.UserCardDTO;
import com.tkm3d1a.cardtesting.util.FileUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user-cards")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCardController {

    private final @NonNull UserCardService userCardService;
    private final @NonNull AppUserService appUserService;

    /**
     * Endpoint for Adding a csv card file.
     * <br><br>
     * Structure of the CSV should be:
     * <br>
     * {@code CollectorNumber,SetID,isFoil,isList}
     * <br><br>
     * Collector number -> Integer only value, found in bottom border of card
     * SetID -> Maximum 5 Alphanumeric numbers representing the Set this card belongs too
     * <br>
     * @param file A Multipart file of a specific CSV format described in {@code exampleCSV.md}
     * @param userName include in Header to indicate which username this card belongs too
     * @return {@link ResponseEntity}
     */
    @PostMapping(value = "/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("card-file") MultipartFile file,
                                        @RequestHeader("userName") String userName) {
        String message;
        AppUser foundUser;
        if(FileUtils.isCSVFile(file)){
            try{
                foundUser = appUserService.confirmUserExists(userName);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(e.getMessage());
            }

            try{
                userCardService.uploadBulkCards(file, foundUser);
                message = "File " + file.getOriginalFilename() + " uploaded successfully!";
            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(message);
            }
        } else {
            message = "Is not CSV file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(message);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    /**
     * End point for adding a single card to the database for a specific user.
     * <br>
     * @param userCardDTO A JSON described by {@link UserCardDTO}
     * @param userName include in Header to indicate which username this card belongs too
     * @return {@link ResponseEntity}
     */
    @PostMapping(value = "/add-single-card")
    public ResponseEntity<?> addSingleCard(@RequestBody UserCardDTO userCardDTO,
                                           @RequestHeader("userName") String userName) {
        AppUser foundUser;
        try{
            foundUser = appUserService.confirmUserExists(userName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

        int cardCount = userCardService.addSingleCard(userCardDTO, foundUser);
        String message = "Card inserted:\n\tSet: " +
                userCardDTO.getSetLetters() +
                "\n\tCN: " +
                userCardDTO.getCollectorNumber() +
                "\nTotal number collected:\n" +
                "\t" + cardCount;

        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    /**
     * Finds and returns all cards that are owned by the passed user.
     * <br>
     * @param userName include in Header to indicate which username to retrieve cards for
     * @param returnType only used when requesting CSV data be returned instead of JSON
     * @return {@code JSON} object containing all cards owned by the passed user
     */
    @GetMapping(value = "/cards")
    public ResponseEntity<?> getAllCardsJSON(@RequestHeader("userName") String userName,
                                             @RequestHeader(value = "return", required = false) String returnType)
            throws IOException {
        if(returnType != null && !returnType.isEmpty()) {
            if (returnType.equalsIgnoreCase("file")) {
                return getAllCardsCSV(userName, returnType);
            }
        }

        AppUser foundUser;
        try{
            foundUser = appUserService.confirmUserExists(userName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        List<UserCard> userCards = userCardService.getAllCards(foundUser);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userCards);
    }

    /**
     * Finds and returns all cards that are owned by the passed user as a CSV file.
     * <br>
     * @param userName include in Header to indicate which username to retrieve cards for
     * @param returnType set as {@code file} when CSV data is to be returned instead of JSON (default is JSON)
     * @return {@code CSV} file containing all cards owned by the passed user
     */
    @GetMapping(value = "/cards", produces = "text/csv")
    public ResponseEntity<?> getAllCardsCSV(@RequestHeader("userName") String userName,
                                            @RequestHeader("return") String returnType)
            throws IOException {
        if(!returnType.equalsIgnoreCase("file")){
            return getAllCardsJSON(userName, returnType);
        }

        AppUser foundUser;
        try{
            foundUser = appUserService.confirmUserExists(userName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        String fileName = "cards.csv";
        File file = userCardService.writeCardsToCSV(foundUser);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename="+fileName)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));
    }
}
