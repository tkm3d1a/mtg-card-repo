package com.tkm3d1a.cardtesting.service;

import com.tkm3d1a.cardtesting.entity.Cards;
import com.tkm3d1a.cardtesting.repository.CardsRepository;
import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardsService {

    private final @NonNull CardsRepository cardsRepository;
    private final @NonNull CardPricesService cardPricesService;
    private final @NonNull CardLegalitiesService cardLegalitiesService;

    public Cards addSingleCard(SingleCard singleCard){
        //check if card is in DB all ready
        log.info("Adding card: {} {} {}", singleCard.getCollector_number(),singleCard.getSet(),
                singleCard.getColor_identity());
        Cards addedCard = saveNewSingleCard(singleCard);
        log.info("Added Card");
        return addedCard;
    }

    public Cards getCardById(String id) throws Exception {
        if (cardsRepository.findById(id).isPresent()) {
            return cardsRepository.findById(id).get();
        } else {
            throw new Exception("Card not found!");
        }
    }

    public Cards getCardBySetAndCollector(String setLetters, String collectorNumber) throws Exception {
        if(cardsRepository.findByCardSetLettersAndCardCollectorNumber(
                setLetters, collectorNumber).isPresent()) {
            return cardsRepository.findByCardSetLettersAndCardCollectorNumber(
                    setLetters, collectorNumber).get();
        } else {
            throw new Exception("Card not found!");
        }
    }

    public void addMultipleCards(List<SingleCard> multipleCards) {
        for(SingleCard singleCard : multipleCards){
            saveNewSingleCard(singleCard);
        }
    }

    /**
     * Extraction of {@code singleCard} information to save into database.
     *
     * @param singleCard a POJO for a json object gotten from the Scryfall API representing a single MTG card
     */
    private Cards saveNewSingleCard(SingleCard singleCard) {
        Cards savedCard = new Cards();
        if(cardsRepository.existsById(singleCard.getId())){
            log.warn("CARD EXISTS: {}", singleCard.getName());
        } else {
            Cards card = new Cards();
            card.setId(singleCard.getId());
            card.setCardCMC(singleCard.getCmc());
            card.setCardCollectorNumber(singleCard.getCollector_number());
            String colorId = convertArrayListToString(singleCard.getColor_identity());
            card.setCardColorIdentity(colorId);
            if(!singleCard.getLayout().equals("transform") && singleCard.getColors() != null){
                String colors = convertArrayListToString(singleCard.getColors());
                card.setCardColors(colors);
            }
            card.setCardLayout(singleCard.getLayout());
            card.setCardManaCost(singleCard.getMana_cost());
            card.setCardName(singleCard.getName());
            card.setCardPower(singleCard.getPower());
            card.setCardRarity(singleCard.getRarity());
            card.setCardSetLetters(singleCard.getSet());
            card.setCardSetName(singleCard.getSet_name());
            card.setCardToughness(singleCard.getToughness());
            card.setCardTypeLine(singleCard.getType_line());

            savedCard = cardsRepository.save(card);
        }
//        log.info("--------------------calling cardPricesService--------------------");
        cardPricesService.updateCardPrices(singleCard);
//        log.info("--------------------calling cardLegalitiesService----------------");
        cardLegalitiesService.updateCardLegalities(singleCard);
        return savedCard;

    }

    /**
     * Takes an {@link ArrayList<String>} and converts it to a regular string for database storage.
     *
     * @param passedArrayList a passed array of {@link String} objects to be converted to a single string
     * @return string A String representation of the {@code passedArrayList}
     */
    private static String convertArrayListToString(ArrayList<String> passedArrayList) {
        StringBuilder newStringBuilder = new StringBuilder();
        log.info("Passed Array List: {}", passedArrayList);
        for(String s : passedArrayList){
            newStringBuilder.append(s);
            newStringBuilder.append(',');
        }
        int i = newStringBuilder.length();
        if(i == 0) return "";
        return newStringBuilder.substring(0,i-1);
    }

}
