package com.tkm3d1a.cardtesting.DTO;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCardCSVtoDTO {

    @CsvBindByName
    private String collectorNumber;
    @CsvBindByName
    private String setID;
    @CsvBindByName
    private boolean isFoil;
    @CsvBindByName
    private boolean isList;
}
