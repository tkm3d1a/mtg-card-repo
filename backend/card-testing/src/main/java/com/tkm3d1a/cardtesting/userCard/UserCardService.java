package com.tkm3d1a.cardtesting.userCard;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tkm3d1a.cardtesting.appUser.AppUser;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardCSVBean;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardJSON;
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
public class UserCardService {

    @Resource
    private UserCardRepository userCardRepository;

    public void uploadBulkCards(MultipartFile multipartFile, AppUser appUser) throws IOException {
        //TODO: identify and create temporary storage location for file storage
        log.info("********UserCardService uploadBulkCards*********");
        log.info("file passed: {}", multipartFile.getOriginalFilename());
        Path filePath = Files.createTempFile(null, ".csv");
        log.info("temp file path: {}", filePath.toAbsolutePath());
        File file = new File(filePath.toUri());
        multipartFile.transferTo(file);
        log.info("File transferred!");

        List<UserCardCSVBean> userCardCSVBeans = new CsvToBeanBuilder<UserCardCSVBean>(new FileReader(file))
                .withType(UserCardCSVBean.class)
                .build()
                .parse();
        log.info("First Line from CSVBean: {}\t{}\t{}\t{}",
                userCardCSVBeans.get(0).getSetID(),
                userCardCSVBeans.get(0).getCollectorNumber(),
                userCardCSVBeans.get(0).isFoil(),
                userCardCSVBeans.get(0).isList());
        log.info("21st Line from CSVBean : {}\t{}\t{}\t{}",
                userCardCSVBeans.get(20).getSetID(),
                userCardCSVBeans.get(20).getCollectorNumber(),
                userCardCSVBeans.get(20).isFoil(),
                userCardCSVBeans.get(20).isList());
        log.info("Adding Cards to database...");

        List<UserCard> userCards = new ArrayList<>();
        for(UserCardCSVBean bean : userCardCSVBeans){
            UserCard userCard = new UserCard();
            userCard.setAppUser(appUser);
            userCard.setSetID(bean.getSetID());
            userCard.setCollectorNumber(bean.getCollectorNumber());
            userCard.setIsFoil(bean.isFoil());
            userCard.setIsList(bean.isList());
            userCards.add(userCard);
        }
        userCardRepository.saveAll(userCards);
        log.info("************************************************");
    }

    public int addSingleCard(UserCardJSON userCardJSON, AppUser appUser) {
        UserCard userCard = new UserCard();
        userCard.setAppUser(appUser);
        //TODO: validate is not negative and is integer
        userCard.setCollectorNumber(userCardJSON.getCollectorNumber());
        //TODO: validate string is between 3-5 letters
        //TODO: ensure string is set to all capital letters
        userCard.setSetID(userCardJSON.getSetLetters());
        //TODO: validate is boolean
        userCard.setIsFoil(userCardJSON.isFoil());
        //TODO: validate is boolean
        userCard.setIsList(userCardJSON.isList());
        userCardRepository.save(userCard);

        return userCardRepository.countByAppUserEqualsAndCollectorNumberEqualsAndSetIDEquals(
                appUser,
                userCard.getCollectorNumber(),
                userCard.getSetID());
    }
}
