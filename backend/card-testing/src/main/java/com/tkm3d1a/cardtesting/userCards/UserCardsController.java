package com.tkm3d1a.cardtesting.userCards;

import com.tkm3d1a.cardtesting.userCards.objects.UserCardJSON;
import com.tkm3d1a.cardtesting.util.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user-cards")
public class UserCardsController {

    @Resource
    private UserCardsService userCardsService;

    @PostMapping(value = "/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("card-file") MultipartFile file) {
        String message;
        if(FileUtils.isCSVFile(file)){
            try{
                userCardsService.uploadBulkCards(file);
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
    public ResponseEntity<?> addSingleCard(@RequestBody UserCardJSON userCardJSON){

        int cardCount = userCardsService.addSingleCard(userCardJSON);
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
