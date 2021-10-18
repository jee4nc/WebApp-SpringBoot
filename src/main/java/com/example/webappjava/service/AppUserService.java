package com.example.webappjava.service;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    public List<AppUser> list() {
      return appUserRepository.findAll();
    }

    public Optional<AppUser> getById(int id) {
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> getByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public boolean existsById(int id) {
        return appUserRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }
}
