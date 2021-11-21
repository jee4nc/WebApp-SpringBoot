package com.example.webappjava.repository;

import com.example.webappjava.entity.Ticket;
import com.example.webappjava.entity.TicketDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDetailRepository extends JpaRepository<TicketDetail, Integer> {
}
