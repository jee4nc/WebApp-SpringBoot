package com.example.webappjava.service;

import com.example.webappjava.entity.Country;
import com.example.webappjava.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> list() {return countryRepository.findAll();}
    public Optional<Country> getOne(int id) { return countryRepository.findById(id);}
    public Optional<Country> getByName(String name) {return countryRepository.findByName(name);}
    public void save(Country country) {countryRepository.save(country);}
    public void delete(int id) {countryRepository.deleteById(id);}
    public boolean exitsById(int id) {return countryRepository.existsById(id);}
    public boolean exitsByName(String name) {return countryRepository.existsByName(name);}

}
