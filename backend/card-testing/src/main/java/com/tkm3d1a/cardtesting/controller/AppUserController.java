package com.tkm3d1a.cardtesting.controller;

import com.tkm3d1a.cardtesting.DTO.AddUserDTO;
import com.tkm3d1a.cardtesting.service.AppUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/app-user")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserController {

    private final @NonNull AppUserService appUserService;

    @PostMapping("/add-user")
    public ResponseEntity<?> addAppUser(@RequestBody AddUserDTO addUserDTO) {
        String message;

        String newId = appUserService.addUser(addUserDTO.getUserName());
        message = "New UserId for user:\n" +
                "Username:\t" + addUserDTO.getUserName() + "\n" +
                "User ID:\t" + newId;

        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }
}