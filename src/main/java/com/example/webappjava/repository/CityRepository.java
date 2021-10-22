package com.example.webappjava.repository;

import com.example.webappjava.entity.City;
import com.example.webappjava.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
    boolean existsByName(String name);
}
