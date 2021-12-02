package com.example.webappjava.service;

import com.example.webappjava.entity.Product;

import java.util.Comparator;

public class nameSorter implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
