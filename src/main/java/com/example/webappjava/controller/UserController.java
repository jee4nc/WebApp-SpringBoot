package com.example.webappjava.controller;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/registerUser")
    public ModelAndView registerUser(String name, String password) {
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isBlank(name)) {
            mv.setViewName("/register");
            mv.addObject("error", "El nombre no puede estar vacio");
            return mv;
        }
        if(StringUtils.isBlank(password)) {
            mv.setViewName("/register");
            mv.addObject("error", "La contraseña no puede estar vacia");
            return mv;
        }

        if(appUserService.existsByName(name)) {
            mv.setViewName("/register");
            mv.addObject("error", "El user ya existe");
            return mv;
        }
        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setPassword(passwordEncoder.encode(password));
        Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        appUser.setRoles(roles);
        appUserService.save(appUser);
        mv.setViewName("/login");
        mv.addObject("registroOK", "Cuenta creada, " + appUser.getName() + ", Inicia Sesion!");
        return mv;
    }
}
