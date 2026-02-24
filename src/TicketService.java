import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {
    private final Map<Integer, Ticket> ticketMap = new HashMap<>();

    public Ticket createTicket(String name, String content){
        Ticket ticket = new Ticket(name, content);
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket getTicketById(int id){
        return ticketMap.get(id);
    }

    public List<Ticket> getAllTickets(){
        return new ArrayList<>(ticketMap.values());
    }

    public boolean updateTicketName(int id, String newName){
        Ticket ticket = ticketMap.get(id);
        if(ticket == null) return false;

        ticket.setName(newName);
        return true;
    }
    public boolean updateTicketContent(int id, String newContent){
        Ticket ticket = ticketMap.get(id);
        if(ticket == null) return false;

        ticket.setContent(newContent);
        return true;
    }

    public boolean deleteTicket(int id){
        return ticketMap.remove(id) != null;
    }




}
