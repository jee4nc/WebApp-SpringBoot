package com.example.webappjava.controller;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import com.example.webappjava.entity.Role;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.CityService;
import com.example.webappjava.service.CountryService;
import com.example.webappjava.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLOutput;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    AppUserService appUserService;

    // Testing code for load data into selection fields
    @Autowired
    CountryService countryService;

    // end of code

    @Autowired
    RoleService roleService;

    @Autowired
    CityService cityService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    Esta funcion permite poder entregar una lista
//    de los paises al fronted register.html
    @GetMapping("/register")
    public String register(Model model){
        // Testing code for load data into selection fields
        List<Country> listTest = countryService.list();
        model.addAttribute("countries", listTest);
        List<City> listCity = cityService.list();
        model.addAttribute("cities", listCity);
        // End of teting code
        return "register";
    }


//    ACTION CREATE CONTROLLER (NOT THE VIEW, THE FUNCTION)
    @PostMapping("/registerUser")
    public ModelAndView registerUser(String email,
                                     String password,
                                     String firstName,
                                     String lastName,
                                     String street,
                                     Country country
                                     ) {
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
        if(StringUtils.isBlank(street)) {
            mv.setViewName("/register");
            mv.addObject("error", "La Calle no puede estar vacia");
            return mv;
        }
        String countryName = country.getName();

        if(StringUtils.isBlank(countryName)) {
            mv.setViewName("/register");
            mv.addObject("error", "Debes seleccionar un pais");
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
        appUser.setStreet(street);
        appUser.setCountry(country);
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
