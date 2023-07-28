package com.tkm3d1a.cardtesting.userCard;

import com.tkm3d1a.cardtesting.appUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Integer> {

    int countByCollectorNumberEqualsAndSetIDEquals(int collectorNumber, String setID);
    int countByAppUserEqualsAndCollectorNumberEqualsAndSetIDEquals(
            AppUser appUser,
            int collectorNumber,
            String setID);

    List<UserCard> findAllByAppUserIs(AppUser appUser);

}
