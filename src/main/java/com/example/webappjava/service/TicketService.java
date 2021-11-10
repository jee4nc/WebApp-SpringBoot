package com.example.webappjava.service;

import com.example.webappjava.entity.Ticket;
import com.example.webappjava.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public boolean existsById(int id) {
        return ticketRepository.existsById(id);
    }
}
