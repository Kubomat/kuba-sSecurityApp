package com.springsecurity.jakob.services;

import com.springsecurity.jakob.dtos.UserDTO;
import com.springsecurity.jakob.models.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUsername(String username);

    void updateAccountLockStatus(Long userId, boolean lock);

    void updateAccountExpiryStatus(Long userId, boolean expiry);

    void updateAccountEnabledStatus(Long userId, boolean enabled);

    User findUserById(Long userId);
}
