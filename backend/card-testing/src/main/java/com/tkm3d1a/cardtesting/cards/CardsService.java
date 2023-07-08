package com.tkm3d1a.cardtesting.cards;

import com.tkm3d1a.cardtesting.scryfall.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardsService {

    @Resource
    private CardsRepository cardsRepository;

    public void addSingleCard(SingleCard singleCard){
        Cards card = new Cards();
        card.setId(singleCard.getId());
        card.setCardCMC(singleCard.getCmc());
        card.setCardIsFoil(singleCard.isFoil());
        //check if card is in DB all ready
        if(cardsRepository.existsById(card.getId())){
            log.warn("CARD EXISTS");
        } else {
            cardsRepository.save(card);
        }
    }
}
