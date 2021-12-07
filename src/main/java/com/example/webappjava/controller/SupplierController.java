package com.example.webappjava.controller;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.CityService;
import com.example.webappjava.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @Autowired
    CityService cityService;

    @Autowired
    AppUserService appUserService;

    Optional<AppUser> currentUserEmailTop;
    Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @PostMapping
    public String createForm(Model model, @RequestParam String emailUser) {
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);
        Integer idUser = currentUserEmailTop.get().getId();
        Country country = currentUserEmailTop.get().getCountry();
        List<City> cities = country.getCities();
        model.addAttribute("idUser", idUser);
        model.addAttribute("cities", cities);
        logger.info("Id usuario: {} ", idUser);
        logger.info("Pais : {} ", country);
        return "/supplier/supplierForm";
    }
}
