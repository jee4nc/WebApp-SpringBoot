package com.example.webappjava.security.service;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return MainUser.build(appUser);
    }
}
