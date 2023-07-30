package com.tkm3d1a.cardtesting.scryfall.objects;

import com.tkm3d1a.cardtesting.scryfall.objects.identifiers.Identifiers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardCollectionBody {
    private Identifiers[] identifiers;
}
