package com.tkm3d1a.cardtesting.service;

import com.tkm3d1a.cardtesting.entity.AppUser;
import com.tkm3d1a.cardtesting.repository.AppUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserService {

    private final @NonNull AppUserRepository appUserRepository;

    public String addUser(String userName){
        AppUser appUser = new AppUser();
        appUser.setUserName(userName);
        AppUser addedUser = appUserRepository.save(appUser);
        log.info("Successfully added user: {}", addedUser);

        return addedUser.getAppUserId();
    }

    public AppUser confirmUserExists(String userName) throws Exception{
        if(appUserRepository.existsAppUserByUserNameEquals(userName)){
            return appUserRepository.findByUserNameEquals(userName);
        } else {
            throw new Exception("User not found!");
        }
    }
}
