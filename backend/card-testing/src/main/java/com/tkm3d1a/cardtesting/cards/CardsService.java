package com.tkm3d1a.cardtesting.cards;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardsService {

    @Resource
    private CardsRepository cardsRepository;

    public void addSingleCard(){
        Cards card = new Cards();
        card.setId("TEST-id-number5");
        card.setCardCMC(6.0);
        card.setCardIsFoil(true);
        //check if card is in DB all ready
        if(cardsRepository.existsById(card.getId())){
            log.warn("CARD EXISTS");
        } else {
            cardsRepository.save(card);
        }
    }
}
