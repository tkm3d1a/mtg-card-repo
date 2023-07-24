package com.tkm3d1a.cardtesting.appUser;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUserService {

    @Resource
    private AppUserRepository appUserRepository;

    public String addUser(String userName){
        AppUser appUser = new AppUser();
        appUser.setUserName(userName);
        AppUser addedUser = appUserRepository.save(appUser);
        log.info("Successfully added user: {}", addedUser.toString());

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
