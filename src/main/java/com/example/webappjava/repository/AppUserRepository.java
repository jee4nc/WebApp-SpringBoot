package com.example.webappjava.repository;

import com.example.webappjava.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByName(String name);
    boolean existsByName(String name);
}
