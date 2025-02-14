package com.springsecurity.jakob.impl;

import com.springsecurity.jakob.dtos.UserDTO;
import com.springsecurity.jakob.models.AppRole;
import com.springsecurity.jakob.models.Role;
import com.springsecurity.jakob.models.User;
import com.springsecurity.jakob.repositories.RoleRepository;
import com.springsecurity.jakob.repositories.UserRepository;
import com.springsecurity.jakob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = findUserById(userId);
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = findUserById(id);
        return convertToDto(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException(String.format("User with username: %s not found", username)));
    }

    @Override
    public void updateAccountLockStatus(Long userId, boolean lock) {
        User user = findUserById(userId);
        user.setAccountNonLocked(!lock);
        userRepository.save(user);
    }

    @Override
    public void updateAccountExpiryStatus(Long userId, boolean expiry) {
        User user = findUserById(userId);
        user.setAccountNonExpired(!expiry);
        userRepository.save(user);
    }

    @Override
    public void updateAccountEnabledStatus(Long userId, boolean enabled) {
        User user = findUserById(userId);
        user.setEnabled(enabled);
        userRepository.save(user);

    }

    @Override
    public void updateCredentialsExpiryStatus(Long userId, boolean expire) {
        User user = findUserById(userId);
        user.setCredentialsNonExpired(!expire);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        try {
            User user = findUserById(userId);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Failed to update password");
        }
    }

    private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }


}
