package com.tkm3d1a.cardtesting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardPrices {

    @Id
    @Column(
            length = 36
    )
    private String id;

    private String usd;
    private String usd_foil;
    private String usd_etched;
    private String eur;
    private String eur_foil;
    private String tix;
}
