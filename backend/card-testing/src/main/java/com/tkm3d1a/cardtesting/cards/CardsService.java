package com.tkm3d1a.cardtesting.cards;

import com.tkm3d1a.cardtesting.scryfall.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CardsService {

    @Resource
    private CardsRepository cardsRepository;

    public void addSingleCard(SingleCard singleCard){
        //check if card is in DB all ready
        if(cardsRepository.existsById(singleCard.getId())){
            log.warn("CARD EXISTS");
        } else {
            Cards card = new Cards();
            card.setId(singleCard.getId());
            card.setCardCMC(singleCard.getCmc());
            card.setCardCollectorNumber(singleCard.getCollector_number());
            String colorId = convertArrayListToString(singleCard.getColor_identity());
            card.setCardColorIdentity(colorId);
            String colors = convertArrayListToString(singleCard.getColors());
            card.setCardColors(colors);
//            card.setCardIsFullArt(singleCard.isFull_art());
//            card.setCardIsPromo(singleCard.isPromo());
//            card.setCardIsReprint(singleCard.isReprint());
//            card.setCardIsReserved(singleCard.isReserved());
//            card.setCardIsVariation(singleCard.isVariation());
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
    }

    private static String convertArrayListToString(ArrayList<String> passedArrayList) {
        StringBuilder newStringBuilder = new StringBuilder();
        for(String s : passedArrayList){
            newStringBuilder.append(s);
            newStringBuilder.append(',');
        }
        int i = newStringBuilder.length();
        return newStringBuilder.substring(0,i-1);
    }
}
