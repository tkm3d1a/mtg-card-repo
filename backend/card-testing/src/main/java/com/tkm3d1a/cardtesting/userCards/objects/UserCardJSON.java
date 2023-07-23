package com.tkm3d1a.cardtesting.userCards.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCardJSON {
    private String setLetters;
    private int collectorNumber;
    private boolean isFoil;
    private boolean isList;
}