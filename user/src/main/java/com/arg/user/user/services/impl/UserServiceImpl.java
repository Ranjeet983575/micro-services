package com.arg.user.user.services.impl;

import com.arg.user.user.entities.RoleEntity;
import com.arg.user.user.entities.UserEntity;
import com.arg.user.user.exception.ResourceNotFoundException;
import com.arg.user.user.respositories.UserRepository;
import com.arg.user.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity user) {
        for (RoleEntity role : user.getRoles()) {
            if (role != null) {
                role.setUser(user);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found "));
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

}
