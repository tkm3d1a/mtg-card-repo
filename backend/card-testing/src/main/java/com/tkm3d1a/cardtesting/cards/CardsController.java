package com.tkm3d1a.cardtesting.cards;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/all-cards")
public class CardsController {

    @Resource
    private CardsService cardsService;

    @RequestMapping("")
    public String base(){
        return "Base URL for all-cards";
    }

    @RequestMapping("/test-add")
    public void addTestCard(){
        cardsService.addSingleCard();
    }
}
