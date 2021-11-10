package com.example.webappjava.controller;


import com.example.webappjava.entity.Ticket;
import com.example.webappjava.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/create")
    public void createTicket() {
        Ticket ticket = new Ticket();
        Double total = 0.0;
        Date today = new Date();
        ticket.setCreationDate(today);
        ticket.setTotal(total);
        ticket.setDone(false);
        ticketService.save(ticket);
    }

}
