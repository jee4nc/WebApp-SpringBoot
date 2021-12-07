package com.example.webappjava.service;

import com.example.webappjava.entity.TicketDetail;
import com.example.webappjava.repository.TicketDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TicketDetailService {

    @Autowired
    TicketDetailRepository ticketDetailRepository;

    public void save(TicketDetail ticketDetail) {
        ticketDetailRepository.save(ticketDetail);
    }

    public boolean existsById(int id) {
        return ticketDetailRepository.existsById(id);
    }
}
