package com.example.webappjava.config;

import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import com.example.webappjava.service.CityService;
import com.example.webappjava.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateCity  implements CommandLineRunner {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @Override
    public void run(String... args) throws Exception {
//        City city = new City();
//        city.setName("Valparaiso");
//        Country countryFrom = countryService.getByName("Chile").get();
//        city.setCountry(countryFrom);
//        cityService.save(city);
    }
}
