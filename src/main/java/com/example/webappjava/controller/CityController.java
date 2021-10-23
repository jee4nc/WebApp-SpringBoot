package com.example.webappjava.controller;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.CityService;
import com.example.webappjava.service.CountryService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    // Esta funcion permitira devolver un array con las ciudades del
    //    pais seleccionado en el campo de seleccion de pais
    @GetMapping()
    public @ResponseBody
    List<City> findAllCities(
            @RequestParam(value = "countryId", required = true) Integer countryId){
        Optional<Country> country = countryService.getOne(countryId);
        return cityService.findAllByCountry(countryId);
    }

}
