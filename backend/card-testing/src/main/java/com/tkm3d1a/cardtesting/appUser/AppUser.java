package com.tkm3d1a.cardtesting.appUser;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String appUserId;

    @Column(
            unique = true,
            updatable = false,
            length = 20,
            nullable = false
    )
    private String userName;

    @Column(
            nullable = false
    )
    private String password = "password";
}
