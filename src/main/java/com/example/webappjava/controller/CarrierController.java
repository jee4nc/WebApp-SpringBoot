package com.example.webappjava.controller;

import com.example.webappjava.entity.*;
import com.example.webappjava.service.*;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrier")
public class CarrierController {

    @Autowired
    CarrierService carrierService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    CityService cityService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    ProductService productService;

    Optional<AppUser> currentUserEmailTop;
    Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @GetMapping
    public String createForm(Model model, @RequestParam String emailUser) {
        String[] destinies = new String[] {"Chile", "Perú", "Argentina", "Bolivia"};
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);

        Country country = currentUserEmailTop.get().getCountry();
        List<City> cities = country.getCities();
        model.addAttribute("destinies", destinies);
        model.addAttribute("cities", cities);
        return ("/carrier/carrierForm");
    }

    @PostMapping("create")
    public String createCarrier(Model model,
                                @RequestParam String emailUser,
                                @RequestParam String rut,
                                @RequestParam String birthday,
                                @RequestParam Double tariff,
                                @RequestParam String destiny,
                                @RequestParam Integer city) throws ParseException {
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);

        City city1 = cityService.getOne(city).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(birthday);

        Carrier carrier = new Carrier(rut , date, destiny, tariff, currentUserEmailTop.get(),city1);
        carrierService.save(carrier);

        AppUser appUser =  appUserService.getByEmail(emailUser).get();

        appUser.setCarrier(carrier);
        appUserService.save(appUser);
        return "/carrier/carrierForm";
    }
}
