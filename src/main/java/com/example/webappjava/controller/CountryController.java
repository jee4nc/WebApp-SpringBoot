package com.example.webappjava.controller;

import com.example.webappjava.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrier")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("list")
    public String list() {
        return "hola";
    }


}
