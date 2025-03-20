package com.arg.user.user.controllers;

import com.arg.user.user.entities.ProfileEntity;
import com.arg.user.user.entities.UserEntity;
import com.arg.user.user.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequestMapping("/api/ms/user/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileEntity> getProfile(@PathVariable UUID profileId) {
        ProfileEntity profile = profileService.getUserProfile(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
