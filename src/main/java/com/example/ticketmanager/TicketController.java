package com.example.ticketmanager;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    Ticket createTicket(@Valid @RequestBody CreateTicketRequest request) {
        return ticketService.createTicket(request.getName(), request.getContent(),
                request.getUserId());
    }

    @GetMapping("/{id}")
    Ticket getTicket(@PathVariable long id){
        return ticketService.getTicketById(id);
    }

    @GetMapping
    List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @PutMapping("/{id}/name")
    Ticket updateTicketName(@Valid @PathVariable long id, @RequestBody UpdateTicketRequest request){
        return ticketService.updateTicketName(id, request.getValue());
    }

    @PutMapping("/{id}/content")
    Ticket updateTicketContent(@Valid @PathVariable long id,
                               @RequestBody UpdateTicketRequest request){
        return ticketService.updateTicketContent(id, request.getValue());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> handleDeleteTicket(@PathVariable long id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}
