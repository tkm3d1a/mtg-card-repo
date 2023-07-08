package com.tkm3d1a.cardtesting.cards;

import com.tkm3d1a.cardtesting.scryfall.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/all-cards")
public class CardsController {

    @Resource
    private CardsService cardsService;

    @RequestMapping("")
    public String base(){
        return "Base URL for all-cards";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test-add")
    public void addTestCard(@RequestBody SingleCard singleCard){
        log.info("Class: {}", singleCard.getClass());
        log.info("Request body: {}", singleCard.getSet());
        Double usd = Double.parseDouble(singleCard.getPrices().getUsd());
        log.info("Price - usd: {}", usd);
        log.info("Price - mvID: {}", singleCard.getMultiverse_ids().get(0));
        log.info("Image: {}", singleCard.getImage_uris().getSmall());
        cardsService.addSingleCard(singleCard);
    }
}
