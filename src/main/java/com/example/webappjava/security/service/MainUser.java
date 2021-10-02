package com.example.webappjava.security.service;

import com.example.webappjava.entity.AppUser;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MainUser implements UserDetails {

    private int id;
    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public static  MainUser build(AppUser appUser) {
        List<GrantedAuthority> authorityList =
            appUser.getRoles().stream().map(role -> new SimpleGrantedAuthority
                    (role.getRoleName().name())).collect(Collectors.toList());
        return new MainUser(appUser.getId(), appUser.getName(), appUser.getPassword(), authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }
}
