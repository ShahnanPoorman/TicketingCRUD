import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //bool running that's true (not constant)
        boolean running = true;

        //Ticket placeholder variable
        Ticket ticket;

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
            //5) UpdateTicketContent(id)
            //6) DeleteTicket()

            //save user input to variable
            int choice = getMenuChoice(sc);

            //switch statement to read user input
            switch (choice){
                case 1:{
                    running = false;
                    break;
                }
                case 2:{
                    ticket = ticketService.createTicket(promptForTicketInput(sc,"name"),
                            promptForTicketInput(sc, "content"));
                    System.out.println("Ticket created:");
                    System.out.println(ticket.toString());
                    break;
                }
                case 3:{
                    System.out.println(ticketService.getTicketById(promptForTicketId(sc, "read")).toString());
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
                    //prompts user for ticket id
                    ticket = ticketService.getTicketById(promptForTicketId(sc, "edit"));
                    //display ticket info
                    System.out.println(ticket);
                    System.out.println("What would you like to replace the ticket's content with?");

                    if (ticketService.updateTicketContent(ticket.getId(), sc.nextLine())){
                        System.out.println("Ticket updated.\n" + ticket);
                    }
                    break;
                }
                case 6:{
                    //prompts user for ticket id
                    ticket = ticketService.getTicketById(promptForTicketId(sc, "delete"));
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

    static int getMenuChoice(Scanner sc){
        System.out.println("""
        Please enter the number for the task you want to complete:
        
        1) Exit / Back
        2) MakeTicket()
        3) GetTicket(id)
        4) ListTickets()
        5) UpdateTicketContent(id)
        6) DeleteTicket()
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