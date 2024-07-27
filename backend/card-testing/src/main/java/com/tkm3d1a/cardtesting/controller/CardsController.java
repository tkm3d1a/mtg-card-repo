package com.tkm3d1a.cardtesting.controller;

import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import com.tkm3d1a.cardtesting.service.CardsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/all-cards")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardsController {

    private final @NonNull CardsService cardsService;

    @RequestMapping("")
    public String base(){
        return "Base URL for all-cards";
    }

    @PostMapping(value = "/test-add")
    public void addTestCard(@RequestBody SingleCard singleCard){
        log.info("--------------------/test-add end point start--------------------");
        log.info("--------------------calling cardService--------------------------");
        cardsService.addSingleCard(singleCard);
        log.info("--------------------/test-add end point end----------------------");
    }

    @PostMapping(value = "/test-bulk-add")
    public void addBulkCards(@RequestBody List<SingleCard> multipleCards){
        log.info("--------------------/test-bulk-add end point start---------------");
        log.info("--------------------calling cardService--------------------------");
        cardsService.addMultipleCards(multipleCards);
        log.info("--------------------/test-bulk-add end point end-----------------");
    }
}
