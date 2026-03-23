package com.example.ticketmanager.repository;

import com.example.ticketmanager.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
