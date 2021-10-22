package com.example.webappjava.service;

import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import com.example.webappjava.repository.CityRepository;
import com.example.webappjava.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<City> list() {return cityRepository.findAll();}
    public Optional<City> getOne(int id) { return cityRepository.findById(id);}
    public Optional<City> getByName(String name) {return cityRepository.findByName(name);}
    public void save(City city) {cityRepository.save(city);}
    public void delete(int id) {cityRepository.deleteById(id);}
    public boolean exitsById(int id) {return cityRepository.existsById(id);}
    public boolean exitsByName(String name) {return cityRepository.existsByName(name);}
}
