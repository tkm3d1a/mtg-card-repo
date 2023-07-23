package com.tkm3d1a.cardtesting.userCards;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tkm3d1a.cardtesting.userCards.objects.UserCardJSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserCardsService {

    @Resource
    private UserCardsRepository userCardsRepository;

    public void uploadBulkCards(MultipartFile multipartFile) throws IOException {
        //TODO: identify and create temporary storage location for file storage
        log.info("********UserCardService uploadBulkCards*********");
        log.info("file passed: {}", multipartFile.getOriginalFilename());
        Path filePath = Files.createTempFile(null, ".csv");
        log.info("temp file path: {}", filePath.toAbsolutePath());
        File file = new File(filePath.toUri());
        multipartFile.transferTo(file);
        log.info("File transferred!");

        List<UserCardsCSVBean> userCardsCSVBeans = new CsvToBeanBuilder<UserCardsCSVBean>(new FileReader(file))
                .withType(UserCardsCSVBean.class)
                .build()
                .parse();
        log.info("First Line from CSVBean: {}\t{}\t{}\t{}",
                userCardsCSVBeans.get(0).getSetID(),
                userCardsCSVBeans.get(0).getCollectorNumber(),
                userCardsCSVBeans.get(0).isFoil(),
                userCardsCSVBeans.get(0).isList());
        log.info("21st Line from CSVBean : {}\t{}\t{}\t{}",
                userCardsCSVBeans.get(20).getSetID(),
                userCardsCSVBeans.get(20).getCollectorNumber(),
                userCardsCSVBeans.get(20).isFoil(),
                userCardsCSVBeans.get(20).isList());
        log.info("Adding Cards to database...");

        List<UserCards> userCards = new ArrayList<>();
        for(UserCardsCSVBean bean : userCardsCSVBeans){
            UserCards userCard = new UserCards();
            userCard.setSetID(bean.getSetID());
            userCard.setCollectorNumber(bean.getCollectorNumber());
            userCard.setIsFoil(bean.isFoil());
            userCard.setIsList(bean.isList());
            userCards.add(userCard);
        }
        userCardsRepository.saveAll(userCards);
        log.info("************************************************");
    }

    public int addSingleCard(UserCardJSON userCardJSON) {
        UserCards userCard = new UserCards();
        //TODO: validate is not negative and is integer
        userCard.setCollectorNumber(userCardJSON.getCollectorNumber());
        //TODO: validate string is between 3-5 letters
        //TODO: ensure string is set to all capital letters
        userCard.setSetID(userCardJSON.getSetLetters());
        //TODO: validate is boolean
        userCard.setIsFoil(userCardJSON.isFoil());
        //TODO: validate is boolean
        userCard.setIsList(userCardJSON.isList());
        userCardsRepository.save(userCard);

        return userCardsRepository.countByCollectorNumberEqualsAndSetIDEquals(
                userCard.getCollectorNumber(),
                userCard.getSetID());
    }
}
