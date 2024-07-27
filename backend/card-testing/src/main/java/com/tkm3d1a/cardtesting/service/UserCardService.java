package com.tkm3d1a.cardtesting.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tkm3d1a.cardtesting.entity.AppUser;
import com.tkm3d1a.cardtesting.entity.Cards;
import com.tkm3d1a.cardtesting.entity.UserCard;
import com.tkm3d1a.cardtesting.repository.UserCardRepository;
import com.tkm3d1a.cardtesting.scryfall.ScryfallService;
import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import com.tkm3d1a.cardtesting.DTO.UserCardCSVtoDTO;
import com.tkm3d1a.cardtesting.DTO.UserCardDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCardService {

    private final @NonNull UserCardRepository userCardRepository;
    private final @NonNull ScryfallService scryfallService;
    private final @NonNull CardsService cardsService;

    public void uploadBulkCards(MultipartFile multipartFile, AppUser appUser) throws IOException {
        //TODO: identify and create temporary storage location for file storage
        log.info("********UserCardService uploadBulkCards*********");
        log.info("file passed: {}", multipartFile.getOriginalFilename());
        Path filePath = Files.createTempFile(null, ".csv");
        log.info("temp file path: {}", filePath.toAbsolutePath());
        File file = new File(filePath.toUri());
        multipartFile.transferTo(file);
        log.info("File transferred!");

        List<UserCardCSVtoDTO> userCardCSVtoDTOS = new CsvToBeanBuilder<UserCardCSVtoDTO>(new FileReader(file))
                .withType(UserCardCSVtoDTO.class)
                .build()
                .parse();

        List<UserCard> userCards = new ArrayList<>();
        for(UserCardCSVtoDTO userCardCSVtoDTO : userCardCSVtoDTOS){
            UserCard userCard = new UserCard();
            userCard.setAppUser(appUser);

            Cards card = findCard(userCardCSVtoDTO.getSetID(), userCardCSVtoDTO.getCollectorNumber());
            userCard.setCard(card);

            userCard.setSetID(userCardCSVtoDTO.getSetID());
            userCard.setCollectorNumber(userCardCSVtoDTO.getCollectorNumber());
            userCard.setIsFoil(userCardCSVtoDTO.isFoil());
            userCard.setIsList(userCardCSVtoDTO.isList());

            userCards.add(userCard);
        }
        userCardRepository.saveAll(userCards);
        log.info("************************************************");
    }


    public int addSingleCard(UserCardDTO userCardDTO, AppUser appUser) {
        UserCard userCard = new UserCard();
        userCard.setAppUser(appUser);

        Cards card = findCard(userCardDTO.getSetLetters(), userCardDTO.getCollectorNumber());
        userCard.setCard(card);

        userCard.setCollectorNumber(userCardDTO.getCollectorNumber());
        userCard.setSetID(userCardDTO.getSetLetters());
        userCard.setIsFoil(userCardDTO.isFoil());
        userCard.setIsList(userCardDTO.isList());

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
            String[] header = {"ScryfallID","SetID","CollectorNumber","CardName"};
            csvWriter.writeNext(header);
            log.info("File created");
            List<UserCard> userCards = userCardRepository.findAllByAppUserIs(foundUser);
            String[] line = new String[4];
            for(UserCard userCard : userCards){
                line[0] = userCard.getCard().getId();
                //TODO: add counting of total cards somewhere?
                line[1] = userCard.getSetID(); //TODO: change to total count of the card ID above
                line[2] = userCard.getCollectorNumber();
                line[3] = userCard.getCard().getCardName();
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
    private Cards findCard(String setID, String collectorNumber) {
        Cards foundCard;

        try {
            foundCard = cardsService.getCardBySetAndCollector(setID, collectorNumber);
//            log.info("Card found in database!");
        } catch (Exception e){
            log.info("Card not in Database, adding to database now...");
            SingleCard scryfallFoundCard = scryfallService.callCardSearchSetAndCollectorNumber(
                    setID,
                    collectorNumber);
//            log.info("Post Scryfall call: {} {}", scryfallFoundCard.getName(), scryfallFoundCard.getSet());
            foundCard = cardsService.addSingleCard(scryfallFoundCard);
        }

        return foundCard;
    }
}
