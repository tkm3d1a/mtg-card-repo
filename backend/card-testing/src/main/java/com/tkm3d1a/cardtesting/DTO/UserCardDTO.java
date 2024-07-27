package com.tkm3d1a.cardtesting.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCardDTO {
    private String setLetters;
    private String collectorNumber;
    private boolean isFoil;
    private boolean isList;
}
