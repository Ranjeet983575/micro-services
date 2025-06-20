package com.arg.user.user.service;

import com.arg.user.user.entities.RoleEntity;
import com.arg.user.user.entities.UserEntity;
import com.arg.user.user.respositories.UserRepository;
import com.arg.user.user.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity user;
    private List<UserEntity> users;

    @BeforeEach
    void setUp() {
        // Initialize mocks manually if not using @ExtendWith(MockitoExtension.class)
        MockitoAnnotations.openMocks(this);

        // Setup a single user with role
        user = new UserEntity();
        user.setId(UUID.randomUUID());

        RoleEntity role = new RoleEntity();
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        // Setup a list of users
        UserEntity user1 = new UserEntity();
        user1.setId(UUID.randomUUID());
        UserEntity user2 = new UserEntity();
        user2.setId(UUID.randomUUID());
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    @Test
    public void getAllUserTest() {
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<UserEntity> allUser = userService.getAllUser();
        Assertions.assertNotNull(allUser);
        Assertions.assertFalse(allUser.isEmpty());
        Assertions.assertEquals(2, allUser.size());
    }

    @Test
    public void saveUserTest() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser = userService.saveUser(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user.getId(), savedUser.getId());
    }
}


