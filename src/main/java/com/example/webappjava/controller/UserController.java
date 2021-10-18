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

//    ACTION CREATE CONTROLLER (NOT THE VIEW, THE FUNCTION)
    @PostMapping("/registerUser")
    public ModelAndView registerUser(String email, String password, String firstName, String lastName) {
        ModelAndView mv = new ModelAndView();

//        VALIDATIONS OF FIELDS
        if(StringUtils.isBlank(email)) {
            mv.setViewName("/register");
            mv.addObject("error", "El email no puede estar vacio.");
            return mv;
        }
        if(StringUtils.isBlank(firstName)) {
            mv.setViewName("/register");
            mv.addObject("error", "Debes escribir tu nombre.");
            return mv;
        }
        if(StringUtils.isBlank(lastName)) {
            mv.setViewName("/register");
            mv.addObject("error", "Debes escribir tu apellido.");
            return mv;
        }
        if(StringUtils.isBlank(password)) {
            mv.setViewName("/register");
            mv.addObject("error", "La contraseña no puede estar vacia");
            return mv;
        }

        if(appUserService.existsByEmail(email)) {
            mv.setViewName("/register");
            mv.addObject("error", "El email ingresado ya esta relacionado a una cuenta.");
            return mv;
        }
//        END VALIDATIONS FIELD

        //Create the new User
        AppUser appUser = new AppUser();

        //Set the values
        appUser.setEmail(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setPassword(passwordEncoder.encode(password));
        Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        appUser.setRoles(roles);
        //End set values

        appUserService.save(appUser);

        mv.setViewName("/login");
        mv.addObject("registroOK", "Cuenta creada, " + appUser.getEmail() + ", Inicia Sesion!");
        return mv;
    }
}
