package com.arg.user.service.services;

import com.arg.user.service.entities.User;
import com.arg.user.service.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(UUID userId) throws ResourceNotFoundException;
}
