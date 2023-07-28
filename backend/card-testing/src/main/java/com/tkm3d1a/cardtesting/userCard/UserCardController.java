package com.tkm3d1a.cardtesting.userCard;

import com.tkm3d1a.cardtesting.appUser.AppUser;
import com.tkm3d1a.cardtesting.appUser.AppUserService;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardJSON;
import com.tkm3d1a.cardtesting.util.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user-cards")
public class UserCardController {

    @Resource
    private UserCardService userCardService;

    @Resource
    private AppUserService appUserService;

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
     * @param userCardJSON A JSON described by {@link UserCardJSON}
     * @param userName include in Header to indicate which username this card belongs too
     * @return {@link ResponseEntity}
     */
    @PostMapping(value = "/add-single-card")
    public ResponseEntity<?> addSingleCard(@RequestBody UserCardJSON userCardJSON,
                                           @RequestHeader("userName") String userName) {
        AppUser foundUser;
        try{
            foundUser = appUserService.confirmUserExists(userName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

        int cardCount = userCardService.addSingleCard(userCardJSON, foundUser);
        String message = "Card inserted:\n\tSet: " +
                userCardJSON.getSetLetters() +
                "\n\tCN: " +
                userCardJSON.getCollectorNumber() +
                "\nTotal number collected:\n" +
                "\t" + cardCount;

        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    /**
     * Finds and returns all cards that are owned by the passed user.
     * <br>
     * @param userName include in Header to indicate which username to retrieve cards for
     * @return {@code JSON} object containing all cards owned by the passed user
     */
    @GetMapping(value = "/cards")
    public ResponseEntity<?> getAllCards(@RequestHeader("userName") String userName) {
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
}
