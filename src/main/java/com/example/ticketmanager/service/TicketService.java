package com.example.ticketmanager.service;

import com.example.ticketmanager.model.Ticket;
import com.example.ticketmanager.model.User;
import com.example.ticketmanager.repository.TicketRepository;
import com.example.ticketmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Ticket createTicket(String name, String content, long userId){
        Ticket ticket = new Ticket();
        User user =
                userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
                        "User not found, with id: " + userId));
        ticket.setName(name);
        ticket.setContent(content);
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(long id){
        return ticketRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found " +
                        "with id: " + id));
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket updateTicketName(long id, String newName){
        Ticket ticket = ticketRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found " +
                        "with id: " + id));

        ticket.setName(newName);
        return ticketRepository.save(ticket);
    }
    public Ticket updateTicketContent(long id, String newContent){
        Ticket ticket = ticketRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found " +
                        "with id: " + id));

        ticket.setContent(newContent);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(long id){
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found " +
                    "with id: " + id);
        }
        ticketRepository.deleteById(id);
    }




}
