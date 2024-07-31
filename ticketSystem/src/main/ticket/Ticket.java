package ticket;

public class Ticket {
    private static int idCounter = 0;
    private Integer id;
    private TicketType tipo;
    private Boolean status;
    private double precoNormal;

    public Ticket(TicketType tipo, double precoNormal){
        this.id = idCounter++;
        this.tipo = tipo;
        this.status = false;
        this.precoNormal = precoNormal;
    }

    public double getPrice(double precoNormal){
        switch (this.tipo){
            case VIP:
                return 2 * precoNormal;
            case MEIA_ENTRADA:
                return 0.5 * precoNormal;
            case NORMAL:
            default:
                return precoNormal;
        }
    }

    public boolean isSold() {
        return status;
    }

    public void setStatus(boolean novoStatus){
        this.status = novoStatus;
    }

    public void setSold(boolean isSold) {
        this.status = isSold;
    }

    public TicketType getType() {
        return this.tipo;
    }
}
