package com.tkm3d1a.cardtesting.cards;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cards {
    @Id
    @Column(
            length = 36
    )
    private String id;

    private String cardName;

    @Column(
            length = 5
    )
    private String cardSetLetters;
    private String cardSetName;
    private String cardCollectorNumber;
    private String cardRarity;

    private String cardLayout;
    private String cardManaCost;

    @Column(
            precision = 1
    )
    private double cardCMC;

    private String cardTypeLine;
    private String cardPower;
    private String cardToughness;
    private String cardColors;
    private String cardColorIdentity;

    //TODO: Decide on removal of tracking these items
    //  They are only used for if these variations exist for this card itself
    //  They do not act as a boolean check on if this specific card is or is not foil
//    private boolean cardIsReserved;
//    private boolean cardIsOversize;
//    private boolean cardIsPromo;
//    private boolean cardIsReprint;
//    private boolean cardIsVariation;
//    private boolean cardIsFullArt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cards cards = (Cards) o;
        return Objects.equals(id, cards.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
