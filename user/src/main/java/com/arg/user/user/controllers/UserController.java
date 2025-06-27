package com.arg.user.user.controllers;

import com.arg.user.user.dto.ApiResponse;
import com.arg.user.user.entities.UserEntity;
import com.arg.user.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<UserEntity>> saveUser(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.saveUser(user);
        ApiResponse<UserEntity> build = ApiResponse.<UserEntity>builder()
                .data(userEntity)
                .success(true)
                .build();
        return new ResponseEntity<>(build, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable UUID userId) {
        UserEntity user = userService.getUser(userId);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> deleteUser(@PathVariable UUID userId) {
        userService.delete(userId);
        return ResponseEntity.ok(ApiResponse.<UserEntity>builder().success(true).build());
    }


}
