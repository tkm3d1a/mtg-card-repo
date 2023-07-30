package com.tkm3d1a.cardtesting.userCard;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tkm3d1a.cardtesting.appUser.AppUser;
import com.tkm3d1a.cardtesting.cards.Cards;
import com.tkm3d1a.cardtesting.cards.CardsService;
import com.tkm3d1a.cardtesting.scryfall.ScryfallService;
import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardCSVBean;
import com.tkm3d1a.cardtesting.userCard.objects.UserCardJSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    @Resource
    private ScryfallService scryfallService;

    @Resource
    private CardsService cardsService;

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

            Cards card = findCard(bean.getSetID(), bean.getCollectorNumber());
            userCard.setCard(card);

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

        Cards card = findCard(userCardJSON.getSetLetters(), userCardJSON.getCollectorNumber());
        userCard.setCard(card);

        userCard.setCollectorNumber(userCardJSON.getCollectorNumber());
        userCard.setSetID(userCardJSON.getSetLetters());
        userCard.setIsFoil(userCardJSON.isFoil());
        userCard.setIsList(userCardJSON.isList());

        userCardRepository.save(userCard);

        return userCardRepository.countByAppUserEqualsAndCollectorNumberEqualsAndSetIDEquals(
                appUser,
                userCard.getCollectorNumber(),
                userCard.getSetID());
    }

    public List<UserCard> getAllCards(AppUser appUser) {
        return userCardRepository.findAllByAppUserIs(appUser);
    }

    public File writeCardsToCSV(AppUser foundUser) throws IOException {
        log.info("********UserCardService writeCardsToCSV*********");
        log.info("Creating file...");
        Path filePath = Files.createTempFile(null, ".csv");
        File file = new File(filePath.toUri());
        try (FileWriter outputFile = new FileWriter(file)) {
            CSVWriter csvWriter = new CSVWriter(outputFile);
            String[] header = {"ScryfallID","Count"};
            csvWriter.writeNext(header);
            log.info("File created");
            List<UserCard> userCards = userCardRepository.findAllByAppUserIs(foundUser);
            String[] line = new String[2];
            for(UserCard userCard : userCards){
                line[0] = userCard.getCard().getId();
                //TODO: add counting of total cards somewhere?
                line[1] = userCard.getSetID(); //TODO: change to total count of the card ID above
                csvWriter.writeNext(line);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("************************************************");
        return file;
    }

    ///////////////////////
    // PRIVATE FUNCTIONS //
    ///////////////////////
    private Cards findCard(String setID, int collectorNumber) {
        Cards foundCard;

        try {
            foundCard = cardsService.getCardBySetAndCollector(setID, Integer.toString(collectorNumber));
            log.info("Card found in database!");
        } catch (Exception e){
            log.info("Card not in Database, adding to database now...");
            SingleCard scryfallFoundCard = scryfallService.callCardSearchSetAndCollectorNumber(
                    setID,
                    Integer.toString(collectorNumber));
            foundCard = cardsService.addSingleCard(scryfallFoundCard);
        }

        return foundCard;
    }
}
