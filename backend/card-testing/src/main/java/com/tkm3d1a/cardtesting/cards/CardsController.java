package com.tkm3d1a.cardtesting.cards;

import com.tkm3d1a.cardtesting.scryfall.SingleCard;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
        log.info("--------------------/test-add end point start--------------------");
        log.info("--------------------calling cardService--------------------------");
        cardsService.addSingleCard(singleCard);
        log.info("--------------------/test-add end point end----------------------");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test-bulk-add")
    public void addBulkCards(@RequestBody List<SingleCard> multipleCards){
        log.info("--------------------/test-add end point start--------------------");
        log.info("--------------------calling cardService--------------------------");
        cardsService.addMultipleCards(multipleCards);
        log.info("--------------------/test-add end point end----------------------");
    }
}
