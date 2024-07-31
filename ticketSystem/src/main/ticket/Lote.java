package ticket;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    private static int idCounter = 0;
    private int id;
    private List<Ticket> tickets;
    private double discount; // Desconto em porcentagem (0 a 25)

    public Lote(double discount) {
        this.id = idCounter++;
        this.tickets = new ArrayList<>();
        setDiscount(discount);
    }

    private void setDiscount(double discount) {
        if (discount < 0 || discount > 25) {
            throw new IllegalArgumentException("Desconto inválido. O desconto deve estar entre 0% e 25%.");
        }
        this.discount = discount;
    }

    public double applyDiscount(Ticket ticket, double price) {
        if (ticket.getType() == TicketType.VIP || ticket.getType() == TicketType.NORMAL) {
            return price * (1 - discount / 100);
        }
        return price;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets(){
        return this.tickets;
    }

}
