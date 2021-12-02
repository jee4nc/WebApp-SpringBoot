package com.example.webappjava.controller;

import com.example.webappjava.service.ProductService;
import com.example.webappjava.service.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticketdetail")
public class TicketDetailController {

    @Autowired
    TicketDetailService ticketDetailService;

    @Autowired
    ProductService productService;

}
