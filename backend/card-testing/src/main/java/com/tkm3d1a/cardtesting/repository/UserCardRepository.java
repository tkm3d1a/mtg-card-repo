package com.tkm3d1a.cardtesting.repository;

import com.tkm3d1a.cardtesting.entity.AppUser;
import com.tkm3d1a.cardtesting.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Integer> {

    int countByCollectorNumberEqualsAndSetIDEquals(String collectorNumber, String setID);
    int countByAppUserEqualsAndCollectorNumberEqualsAndSetIDEquals(
            AppUser appUser,
            String collectorNumber,
            String setID);

    List<UserCard> findAllByAppUserIs(AppUser appUser);

}
