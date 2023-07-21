package com.tkm3d1a.cardtesting.userCards;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCardsCSVBean {

    @CsvBindByName
    private int collectorNumber;
    @CsvBindByName
    private String setID;
    @CsvBindByName
    private boolean isFoil;
    @CsvBindByName
    private boolean isList;
}
