package com.tkm3d1a.cardtesting.cardLeagalities;

import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardLegalitiesService {

    @Resource
    CardLegalitiesRepository cardLegalitiesRepository;

    public void updateCardLegalities(SingleCard singleCard) {
        // check if card exists
        // if card exists with prices, update the prices
        // if card does not exist, add new row with card data

        if(cardLegalitiesRepository.existsById(singleCard.getId())){
            log.warn("LEAGLITIES EXIST: {}", singleCard.getName());
        } else {
            CardLegalities cardLegalities = new CardLegalities(
                    singleCard.getId(),
                    singleCard.getLegalities().getStandard(),
                    singleCard.getLegalities().getFuture(),
                    singleCard.getLegalities().getHistoric(),
                    singleCard.getLegalities().getGladiator(),
                    singleCard.getLegalities().getPioneer(),
                    singleCard.getLegalities().getExplorer(),
                    singleCard.getLegalities().getModern(),
                    singleCard.getLegalities().getLegacy(),
                    singleCard.getLegalities().getPauper(),
                    singleCard.getLegalities().getVintage(),
                    singleCard.getLegalities().getPenny(),
                    singleCard.getLegalities().getCommander(),
                    singleCard.getLegalities().getOathbreaker(),
                    singleCard.getLegalities().getBrawl(),
                    singleCard.getLegalities().getHistoricbrawl(),
                    singleCard.getLegalities().getAlchemy(),
                    singleCard.getLegalities().getPaupercommander(),
                    singleCard.getLegalities().getDuel(),
                    singleCard.getLegalities().getOldschool(),
                    singleCard.getLegalities().getPremodern(),
                    singleCard.getLegalities().getPredh()
            );

            cardLegalitiesRepository.save(cardLegalities);
        }
    }
}
