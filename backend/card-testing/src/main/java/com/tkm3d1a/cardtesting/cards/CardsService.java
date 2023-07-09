package com.tkm3d1a.cardtesting.cards;

import com.tkm3d1a.cardtesting.cardLeagalities.CardLegalitiesService;
import com.tkm3d1a.cardtesting.cardPrices.CardPricesService;
import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CardsService {

    @Resource
    private CardsRepository cardsRepository;

    @Resource
    private CardPricesService cardPricesService;

    @Resource
    private CardLegalitiesService cardLegalitiesService;

    public void addSingleCard(SingleCard singleCard){
        //check if card is in DB all ready
        saveNewSingleCard(singleCard);
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
    private void saveNewSingleCard(SingleCard singleCard) {
        if(cardsRepository.existsById(singleCard.getId())){
            log.warn("CARD EXISTS: {}", singleCard.getName());
        } else {
            Cards card = new Cards();
            card.setId(singleCard.getId());
            card.setCardCMC(singleCard.getCmc());
            card.setCardCollectorNumber(singleCard.getCollector_number());
            String colorId = convertArrayListToString(singleCard.getColor_identity());
            card.setCardColorIdentity(colorId);
            String colors = convertArrayListToString(singleCard.getColors());
            card.setCardColors(colors);
            card.setCardLayout(singleCard.getLayout());
            card.setCardManaCost(singleCard.getMana_cost());
            card.setCardName(singleCard.getName());
            card.setCardPower(singleCard.getPower());
            card.setCardRarity(singleCard.getRarity());
            card.setCardSetLetters(singleCard.getSet());
            card.setCardSetName(singleCard.getSet_name());
            card.setCardToughness(singleCard.getToughness());
            card.setCardTypeLine(singleCard.getType_line());

            cardsRepository.save(card);
        }
//        log.info("--------------------calling cardPricesService--------------------");
        cardPricesService.updateCardPrices(singleCard);
//        log.info("--------------------calling cardLegalitiesService----------------");
        cardLegalitiesService.updateCardLegalities(singleCard);

    }

    /**
     * Takes an {@link ArrayList<String>} and converts it to a regular string for database storage.
     *
     * @param passedArrayList a passed array of {@link String} objects to be converted to a single string
     * @return string A String representation of the {@code passedArrayList}
     */
    private static String convertArrayListToString(ArrayList<String> passedArrayList) {
        StringBuilder newStringBuilder = new StringBuilder();
        for(String s : passedArrayList){
            newStringBuilder.append(s);
            newStringBuilder.append(',');
        }
        int i = newStringBuilder.length();
        if(i == 0) return "";
        return newStringBuilder.substring(0,i-1);
    }

}
