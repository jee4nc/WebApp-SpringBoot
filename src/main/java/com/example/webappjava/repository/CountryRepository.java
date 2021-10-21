package com.example.webappjava.repository;

import com.example.webappjava.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByName(String name);
    boolean existsByName(String name);
}
