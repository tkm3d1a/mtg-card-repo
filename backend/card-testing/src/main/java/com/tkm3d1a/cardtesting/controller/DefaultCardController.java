package com.tkm3d1a.cardtesting.controller;

import com.tkm3d1a.cardtesting.service.DefaultCardDataService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload-card-data")
public class DefaultCardController {

    @Resource
    private DefaultCardDataService defaultCardDataService;

    @PostMapping
    public ResponseEntity<?> uploadBulkDataFile(@RequestParam("file")MultipartFile file) throws IOException {
        //TODO: Identify and correct uploading issue
        //  Currently, have not been able to upload a file over 100MB in size
        //  Tested with 156MB, that was unsuccessful
        //  Need to get to ~400MB to handle full card data file storage needs

        //TODO: Update to get correct DTM when inserting file
        //  Currently, no DTM is being inserted on insertion
        String uploadFile = defaultCardDataService.uploadDefaultCardData(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }
}
