package com.example.ticketmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(String name, String content, User user){
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setContent(content);
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(long id){
        return ticketRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket updateTicketName(long id, String newName){
        Ticket ticket = ticketRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ticket.setName(newName);
        return ticketRepository.save(ticket);
    }
    public Ticket updateTicketContent(long id, String newContent){
        Ticket ticket = ticketRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ticket.setContent(newContent);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(long id){
        if (!ticketRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }




}
