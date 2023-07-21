package com.tkm3d1a.cardtesting.userCards;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCards {

    @Id
    private int userCardId;
}
