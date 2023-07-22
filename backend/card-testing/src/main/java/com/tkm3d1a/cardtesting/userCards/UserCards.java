package com.tkm3d1a.cardtesting.userCards;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int userCardId;

    private int collectorNumber;
    private String setID;
    private Boolean isFoil;
    private Boolean isList;
}
