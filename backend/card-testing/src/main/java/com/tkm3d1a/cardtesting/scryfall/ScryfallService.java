package com.tkm3d1a.cardtesting.scryfall;

import com.tkm3d1a.cardtesting.scryfall.objects.SingleCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This {@code Service} class will be used for fetching and handling data from the Scryfall API
 */
@Slf4j
@Service
public class ScryfallService {

    private static final String SCRYFALL_BASE_URI = "https://api.scryfall.com";
    private static final String CARD_COLLECTION = "/cards/collection";
    private static final String CARDS = "/cards";
    private static final String SCRYFALL_HOST = "api.scryfall.com";

    public SingleCard callCardSearchSetAndCollectorNumber(String set, String collectorNumber){
        WebClient webClient = WebClient.create(SCRYFALL_BASE_URI);
//        CollectorNumAndSet cnAndSet = new CollectorNumAndSet(set, collectorNumber);
//        Identifiers identifiers = new Identifiers(cnAndSet);
//        long contentLength = identifiers.calculateContentSize();

//        log.info("Identifiers: {}", identifiers);
        SingleCard responseCardData = webClient.get()
                .uri(CARDS + "/" + set.toLowerCase() + "/" + collectorNumber)
                .header("Host", SCRYFALL_HOST)
                .retrieve()
                .bodyToMono(SingleCard.class)
                .block();
        assert responseCardData != null;
//        log.info("Response: {}", responseCardData.getId());
        return responseCardData;
    }

}
