package com.springsecurity.jakob.repositories;

import com.springsecurity.jakob.models.AppRole;
import com.springsecurity.jakob.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}
