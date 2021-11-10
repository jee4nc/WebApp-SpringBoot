package com.example.webappjava.repository;

import com.example.webappjava.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
//    Optional<Ticket> findById(Id id);
//    boolean existsById(Id id);
}
