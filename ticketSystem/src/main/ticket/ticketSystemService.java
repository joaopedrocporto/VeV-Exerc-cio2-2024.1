package ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ticketSystemService {
    private List<Show> shows;

    public ticketSystemService(){
        this.shows = new ArrayList<>();
    }

    // Método para criar um novo show
    public Show createShow(Date data, String artista, double cache, double despesasInfraestrutura, List<Lote> lotesDeIngressos, boolean dataEspecial, double precoNormal) {
        Show show = new Show(data, artista, cache, despesasInfraestrutura, lotesDeIngressos, dataEspecial, precoNormal);
        shows.add(show);
        return show;
    }

    public Lote createLote(double desconto){
        Lote lote = new Lote(desconto);
        return lote;
    }

    public Ticket createTicket(TicketType tipo){
        Ticket ticket = new Ticket(tipo);
        return ticket;
    }

}