package com.example.webappjava.repository;

import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);
}
