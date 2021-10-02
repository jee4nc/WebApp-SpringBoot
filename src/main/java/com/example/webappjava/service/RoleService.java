package com.example.webappjava.service;

import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

    public Optional<Role> getByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public boolean existsByRoleName(RoleName roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
