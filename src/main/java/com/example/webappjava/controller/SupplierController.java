package com.example.webappjava.controller;

import com.example.webappjava.entity.*;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.CityService;
import com.example.webappjava.service.ProductService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    ProductService productService;

    Optional<AppUser> currentUserEmailTop;
    Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @PostMapping
    public String createForm(Model model, @RequestParam String emailUser) {
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);

        Integer idUser = currentUserEmailTop.get().getId();
        Country country = currentUserEmailTop.get().getCountry();
        List<City> cities = country.getCities();
        List<Product> products = productService.list();

        model.addAttribute("products", products);
        model.addAttribute("idUser", idUser);
        model.addAttribute("cities", cities);

        logger.info("Id usuario: {} ", idUser);
        logger.info("Pais : {} ", country);

        return "/supplier/supplierForm";
    }

    @PostMapping("create")
    public String createSupplier(Model model,
                                 @RequestParam String emailUser,
                                 @RequestParam String rut,
                                 @RequestParam String birthday,
                                 @RequestParam Double price,
                                 @RequestParam Integer city,
                                 @RequestParam Integer product) throws ParseException {

        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);

        City city1 = cityService.getOne(city).get();
        Product product1 = productService.getOne(product).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(birthday);

        Supplier supplier = new Supplier(rut , date, currentUserEmailTop.get(),city1,product1,price);
        supplierService.save(supplier);

        AppUser appUser =  appUserService.getByEmail(emailUser).get();

        appUser.setSupplier(supplier);
        appUserService.save(appUser);

        logger.info("Rut usuario: {} ", rut);
        logger.info("Date usuario: {} ", birthday);
        logger.info("Precio usuario: {} ", price);
        logger.info("City usuario: {} ", city);
        logger.info("Product : {} ", product);
        return "index";
    }
}
