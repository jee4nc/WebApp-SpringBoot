package com.example.webappjava.repository;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    //    Esto puede ocasionar un error, debido a que no estoy seguro si optional deberia recibir un carrier
//            o un app user
    Optional<Supplier> findByAppUser(AppUser appUser);
    boolean existsByAppUser(AppUser appUser);
}
