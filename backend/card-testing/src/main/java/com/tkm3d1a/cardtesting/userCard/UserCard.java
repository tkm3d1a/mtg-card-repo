package com.tkm3d1a.cardtesting.userCard;

import com.tkm3d1a.cardtesting.appUser.AppUser;
import com.tkm3d1a.cardtesting.cards.Cards;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int userCardId;

    @ManyToOne
    @JoinColumn(name = "appUserId")
    private AppUser appUser;

    //TODO: identify where to conduct linking of a user card to an actual card
    @ManyToOne
    @JoinColumn(name = "id")
    private Cards card;

    private int collectorNumber;
    private String setID;
    private Boolean isFoil;
    private Boolean isList;
}
