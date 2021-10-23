package com.example.webappjava.repository;

import com.example.webappjava.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
//    List<City> findAllByCountry(Integer id);
//    @Query(value = "SELECT id , name FROM city;", nativeQuery = true)
//    List<City> getSomeColumn();
    List<City> findByCountryId(Integer id);
    boolean existsByName(String name);
}
