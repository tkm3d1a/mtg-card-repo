package com.tkm3d1a.cardtesting.scryfall.objects.identifiers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Identifiers {
    @JsonProperty
    private Object[] identifiers;

    @Override
    public String toString() {
        return "{" +
                "\"identifiers\":" + Arrays.toString(identifiers) +
                "}";
    }
}
