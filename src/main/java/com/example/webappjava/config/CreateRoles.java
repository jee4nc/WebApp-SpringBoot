package com.example.webappjava.config;

import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateRoles  implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        //COmentar primero
//        Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
//        Role rolUser = new Role(RoleName.ROLE_USER);
//        roleService.save(rolAdmin);
//        roleService.save(rolUser);
    }
}
