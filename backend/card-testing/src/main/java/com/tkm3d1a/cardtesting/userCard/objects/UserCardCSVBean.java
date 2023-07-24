package com.tkm3d1a.cardtesting.userCard.objects;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCardCSVBean {

    @CsvBindByName
    private int collectorNumber;
    @CsvBindByName
    private String setID;
    @CsvBindByName
    private boolean isFoil;
    @CsvBindByName
    private boolean isList;
}
