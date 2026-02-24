import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //bool running that's true (not constant)
        boolean running = true;

        //Ticket Service
        TicketService ticketService = new TicketService();

        //scanner object!
        Scanner sc = new Scanner(System.in);

        //while running == true loop
        while (running){
            //print MainMenu()
            //1) Exit/Back()
            //2) MakeTicket()
            //3) GetTicket(id)
            //4) ListTickets()
            //5) UpdateTicketName
            //6) UpdateTicketContent(id)
            //7) DeleteTicket()

            //save user input to variable
            int choice = getMenuChoice(sc);

            //switch statement to read user input
            switch (choice){
                case 1:{
                    running = false;
                    break;
                }
                case 2:{
                    try{
                        handleCreateTicket(sc, ticketService);
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                case 3:{
                    try{
                        System.out.println(ticketService.getTicketById(promptForTicketId(sc, "read")).toString());
                    } catch(NullPointerException e){
                        System.out.println("Ticket with that id does not exist!");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Listing all tickets!");
                    for(Ticket t : ticketService.getAllTickets()){
                        System.out.println(t);
                    }
                    break;
                }
                case 5:{
                    try{
                        handleUpdateTicketName(sc, ticketService);
                    } catch(NullPointerException e){
                        System.out.println("Ticket with that id does not exist!");
                    }
                    break;
                }
                case 6:{
                    try{
                        handleUpdateTicketContent(sc, ticketService);
                    } catch(NullPointerException e){
                        System.out.println("Ticket with that id does not exist!");
                    }
                    break;
                }
                case 7:{
                    try{
                        handleDeleteTicket(sc, ticketService);
                    } catch(NullPointerException e){
                        System.out.println("Ticket with that id does not exist!");
                    }
                    break;
                }
            }

            if(running){
                System.out.println("Press enter to return to main menu.");
                System.in.read();
            }

        }
        System.out.println("Thank you for using my console CRUD app :)");

        sc.close();
    }

    static void handleCreateTicket(Scanner sc, TicketService ticketService){
        Ticket ticket = ticketService.createTicket(promptForTicketInput(sc,"name"),
                promptForTicketInput(sc, "content"));
        System.out.println("Ticket created:");
        System.out.println(ticket.toString());
    }

    static void handleUpdateTicketName(Scanner sc, TicketService ticketService){
        //prompts user for ticket id
        Ticket ticket = ticketService.getTicketById(promptForTicketId(sc, "edit(name)"));
        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        //display ticket info
        System.out.println(ticket);
        System.out.println("What would you like to replace the ticket's name with?");

        if (ticketService.updateTicketName(ticket.getId(), sc.nextLine())){
            System.out.println("Ticket updated.\n" + ticket);
        }
    }

    static void handleUpdateTicketContent(Scanner sc, TicketService ticketService){
        //prompts user for ticket id
        Ticket ticket = ticketService.getTicketById(promptForTicketId(sc, "edit(content)"));
        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        //display ticket info
        System.out.println(ticket);
        System.out.println("What would you like to replace the ticket's content with?");

        if (ticketService.updateTicketContent(ticket.getId(), sc.nextLine())){
            System.out.println("Ticket updated.\n" + ticket);
        }
    }

    static void handleDeleteTicket(Scanner sc, TicketService ticketService){
        //prompts user for ticket id
        Ticket ticket = ticketService.getTicketById(promptForTicketId(sc, "delete"));
        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        //display ticket info
        System.out.println(ticket);
        System.out.println("Is this the ticket that you'd like to delete? y/n?");
        String confirmation = sc.nextLine().trim();
        if(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")){
            if (ticketService.deleteTicket(ticket.getId())){
                System.out.println("Ticket deleted successfully");
            }
        } else {
            System.out.println("Ticket not deleted");
        }
    }

    static int getMenuChoice(Scanner sc){
        System.out.println("""
        Please enter the number for the task you want to complete:
        
        1) Exit / Back
        2) MakeTicket()
        3) GetTicket(id)
        4) ListTickets()
        5) UpdateTicketName(id)
        6) UpdateTicketContent(id)
        7) DeleteTicket(id)
        """);
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    static String promptForTicketInput(Scanner sc, String input){
        System.out.println("What would you like the ticket's " + input + " to be?" );
        return sc.nextLine();

    }

    static int promptForTicketId(Scanner sc, String input){
        System.out.println("What is the id of the ticket you would like to " + input + "?" );
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}