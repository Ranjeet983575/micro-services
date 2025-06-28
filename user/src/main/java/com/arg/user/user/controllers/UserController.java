package com.arg.user.user.controllers;

import com.arg.user.user.Auth.*;
import com.arg.user.user.dto.ApiResponse;
import com.arg.user.user.entities.UserEntity;
import com.arg.user.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<UserEntity>> saveUser(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.saveUser(user);
        ApiResponse<UserEntity> build = ApiResponse.<UserEntity>builder()
                .data(userEntity)
                .success(true)
                .build();
        return new ResponseEntity<>(build, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        if (auth.isAuthenticated()) {

            List<String> roles = auth.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String token = jwtUtil.generateToken(loginRequest.getEmail(), roles);
            String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
            ApiResponse<TokenResponse> response = ApiResponse.<TokenResponse>builder()
                    .data(new TokenResponse(token, refreshToken))
                    .message("Login Successfully")
                    .success(true)
                    .status("200")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            ApiResponse<TokenResponse> response = ApiResponse.<TokenResponse>builder()
                    .success(false)
                    .message("Invalid Credentials")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<TokenResponse>> refreshAccessToken(@RequestBody TokenRequest tokenRequest) {

        String refreshToken = tokenRequest.getRefreshToken();

        if (refreshToken == null || refreshToken.isEmpty()) {
            ApiResponse<TokenResponse> response = ApiResponse.<TokenResponse>builder()
                    .success(false)
                    .message("Refresh token is missing")
                    .status("400")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);

            var userDetails = userDetailsService.loadUserByUsername(username);

            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String newAccessToken = jwtUtil.generateToken(username, roles);

            ApiResponse<TokenResponse> response = ApiResponse.<TokenResponse>builder()
                    .data(new TokenResponse(newAccessToken, refreshToken)) // You can rotate refresh token if desired
                    .message("Token refreshed successfully")
                    .success(true)
                    .status("200")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            ApiResponse<TokenResponse> response = ApiResponse.<TokenResponse>builder()
                    .success(false)
                    .message("Invalid or expired refresh token")
                    .status("401")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
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
