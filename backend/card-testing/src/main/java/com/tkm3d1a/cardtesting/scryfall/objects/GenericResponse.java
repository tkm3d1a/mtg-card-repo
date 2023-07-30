package com.tkm3d1a.cardtesting.scryfall.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private String object;
    private Object[] not_found;
    private MultipleCards data;
}
