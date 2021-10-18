package com.example.webappjava.config;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    AppUserService appUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
//        AppUser appUser = new AppUser();
//        String passwordEncoded = passwordEncoder.encode("admin");
//        appUser.setEmail("admin@admin.com");
//        appUser.setFirstName("Don Administrador");
//        appUser.setLastName("Apellido");
//        appUser.setPassword(passwordEncoded);
//        Role roleAdmin = roleService.getByRoleName(RoleName.ROLE_ADMIN).get();
//        Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleAdmin);
//        roles.add(roleUser);
//        appUser.setRoles(roles);
//        appUserService.save(appUser);
    }
}
