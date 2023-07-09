package com.tkm3d1a.cardtesting.cardLeagalities;

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
public class CardLegalities {

    @Id
    @Column(
            length = 36
    )
    private String id;

    private String standard;
    private String future;
    private String historic;
    private String gladiator;
    private String pioneer;
    private String explorer;
    private String modern;
    private String legacy;
    private String pauper;
    private String vintage;
    private String penny;
    private String commander;
    private String oathbreaker;
    private String brawl;
    private String historicbrawl;
    private String alchemy;
    private String paupercommander;
    private String duel;
    private String oldschool;
    private String premodern;
    private String predh;
}
