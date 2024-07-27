package com.tkm3d1a.cardtesting.service;

import com.tkm3d1a.cardtesting.entity.CardPrices;
import com.tkm3d1a.cardtesting.repository.CardPricesRepository;
import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardPricesService {

    @Resource
    CardPricesRepository cardPricesRepository;

    public void updateCardPrices(SingleCard singleCard) {
        // check if card exists
        // if card exists with prices, update the prices
        // if card does not exist, add new row with card data

        if(cardPricesRepository.existsById(singleCard.getId())){
            log.warn("PRICES EXIST: {}", singleCard.getName());
        } else {
            CardPrices cardPrices = new CardPrices(
                    singleCard.getId(),
                    singleCard.getPrices().getUsd(),
                    singleCard.getPrices().getUsd_foil(),
                    singleCard.getPrices().getUsd_etched(),
                    singleCard.getPrices().getEur(),
                    singleCard.getPrices().getEur_foil(),
                    singleCard.getPrices().getTix()
            );
            cardPricesRepository.save(cardPrices);
        }
    }
}
