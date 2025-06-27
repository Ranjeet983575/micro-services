package com.arg.user.user.services;

import com.arg.user.user.entities.UserEntity;


import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    UserEntity getUser(UUID userId);

    List<UserEntity> getAllUser();

    void delete(UUID userId);

}
