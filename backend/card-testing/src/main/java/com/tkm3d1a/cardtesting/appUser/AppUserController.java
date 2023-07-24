package com.tkm3d1a.cardtesting.appUser;

import com.tkm3d1a.cardtesting.appUser.objects.AddUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/app-user")
@RestController
public class AppUserController {

    @Resource
    private AppUserService appUserService;

    @PostMapping("/add-user")
    public ResponseEntity<?> addAppUser(@RequestBody AddUser addUser) {
        String message;

        String newId = appUserService.addUser(addUser.getUserName());
        message = "New UserId for user:\n" +
                "Username:\t" + addUser.getUserName() + "\n" +
                "User ID:\t" + newId;

        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }
}
