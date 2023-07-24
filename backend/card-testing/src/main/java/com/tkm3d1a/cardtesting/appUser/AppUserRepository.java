package com.tkm3d1a.cardtesting.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUserNameEquals(String userName);
    boolean existsAppUserByUserNameEquals(String userName);
}
