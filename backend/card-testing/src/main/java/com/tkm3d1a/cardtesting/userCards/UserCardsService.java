package com.tkm3d1a.cardtesting.userCards;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class UserCardsService {

    @Resource
    private UserCardsRepository userCardsRepository;

    public void uploadBulkCards(MultipartFile multipartFile) throws IOException {
        //TODO: need to figure out how to handle temporary file storage for upload?
        File file = new File("src/main/resources/tempCSV.tmp");
        multipartFile.transferTo(file);
        List<UserCardsCSVBean> userCardsCSVBeans = new CsvToBeanBuilder<UserCardsCSVBean>(new FileReader(file))
                .withType(UserCardsCSVBean.class)
                .build()
                .parse();
    }
}
