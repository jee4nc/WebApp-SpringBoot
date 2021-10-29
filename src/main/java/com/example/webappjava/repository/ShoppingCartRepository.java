package com.example.webappjava.repository;

import com.example.webappjava.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByTokenSession(String tokenSession);
}
