package com.tkm3d1a.cardtesting.scryfall.objects.identifiers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectorNumAndSet {
    @JsonProperty
    private String collectorNumber;
    @JsonProperty
    private String set;

    @Override
    public String toString() {
        return "{" +
                "\"collector_number\":\"" + collectorNumber + "\"," +
                "\"set\":\"" + set + "\"" +
                "}";
    }
}
