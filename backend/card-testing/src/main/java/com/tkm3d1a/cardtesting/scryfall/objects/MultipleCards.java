package com.tkm3d1a.cardtesting.scryfall.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleCards {
    private ArrayList<SingleCard> bulkCards;
}
