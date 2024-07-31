package ticket;

public class Ticket {
    private static int idCounter = 0;
    private Integer id;
    private TicketType tipo;
    private Boolean status;

    public Ticket(TicketType tipo){
        this.id = idCounter++;
        this.tipo = tipo;
        this.status = false;
    }



}
