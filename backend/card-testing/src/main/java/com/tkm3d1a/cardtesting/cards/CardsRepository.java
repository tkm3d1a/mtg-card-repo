package com.tkm3d1a.cardtesting.cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, String> {
    Optional<Cards> findByCardSetLettersAndCardCollectorNumber(String cardSetLetters, String cardCollectorNumber);
}
