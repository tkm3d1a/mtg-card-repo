package com.tkm3d1a.cardtesting.userCards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardsRepository extends JpaRepository<UserCards, Integer> {

    int countByCollectorNumberEqualsAndSetIDEquals(int collectorNumber, String setID);

}
