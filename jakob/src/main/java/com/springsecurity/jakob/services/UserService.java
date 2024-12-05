package com.springsecurity.jakob.services;

import com.springsecurity.jakob.dtos.UserDTO;
import com.springsecurity.jakob.models.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);
}
