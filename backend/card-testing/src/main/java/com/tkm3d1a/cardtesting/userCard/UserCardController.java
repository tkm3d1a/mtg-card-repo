package com.tkm3d1a.cardtesting.userCard;

import com.tkm3d1a.cardtesting.appUser.AppUser;
import com.tkm3d1a.cardtesting.appUser.AppUserService;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardJSON;
import com.tkm3d1a.cardtesting.util.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user-cards")
public class UserCardController {

    @Resource
    private UserCardService userCardService;

    @Resource
    private AppUserService appUserService;

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
}
