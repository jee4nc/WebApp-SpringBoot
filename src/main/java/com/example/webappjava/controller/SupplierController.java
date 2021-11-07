package com.example.webappjava.controller;

import com.example.webappjava.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

}
