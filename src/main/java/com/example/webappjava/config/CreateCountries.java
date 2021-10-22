package com.example.webappjava.config;

import com.example.webappjava.entity.Country;
import com.example.webappjava.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateCountries implements CommandLineRunner {

    @Autowired
    CountryService countryService;

    @Override
    public void run(String... args) throws Exception {
//        Country country = new Country();
//        country.setName("Chile");
//        countryService.save(country);
//
//        Country country1 = new Country();
//        country1.setName("Perú");
//        countryService.save(country1);
//
//        Country country2 = new Country();
//        country2.setName("Argentina");
//        countryService.save(country2);
//
//        Country country3 = new Country();
//        country3.setName("Bolivia");
//        countryService.save(country3);

    }
}
