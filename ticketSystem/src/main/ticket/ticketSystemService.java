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
    public Show createShow(Date data, String artista, double cache, double despesasInfraestrutura, Integer lotesDeIngressos, boolean dataEspecial) {
        Show show = new Show(data, artista, cache, despesasInfraestrutura, lotesDeIngressos, dataEspecial);
        shows.add(show);
        return show;
    }

}