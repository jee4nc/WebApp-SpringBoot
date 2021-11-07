package com.example.webappjava.repository;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer> {
//    Esto puede ocasionar un error, debido a que no estoy seguro si optional deberia recibir un carrier
//    o un app user
    Optional<Carrier> findByAppUser(AppUser appUser);
    boolean existsByAppUser(AppUser appUser);
}
