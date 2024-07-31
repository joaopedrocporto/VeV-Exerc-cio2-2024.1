package ticket;

import org.junit.Test;
import org.junit.Before;
import javax.security.auth.login.AccountException;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;

public class ticketSystemServiceTests {

    private SimpleDateFormat dateFormatedDate;
    private ticketSystemService ticketSystemService;
    private Date data;
    private Show show;
    private String artista;
    private double cache;
    private double totalDespesasInfraestrutura;
    private Lote lotesIngressos;
    private Boolean dataEspecial;
    private TicketType ticketType;
    private double precoNormal;

    @Before
    public void setUp() {
        dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2023-12-12";
        data = null;
        try {
            data = dateFormatedDate.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ticketSystemService = new ticketSystemService();
        artista = "Artista Famoso";
        cache = 50000.0;
        totalDespesasInfraestrutura = 10000.0;
        dataEspecial = false;
        lotesIngressos = new Lote(0);

        // Criando o objeto show
        show = ticketSystemService.createShow(data, artista, cache, totalDespesasInfraestrutura, Arrays.asList(lotesIngressos), dataEspecial, precoNormal);
    }

    @Test
    public void testShowCreation() {
        Lote lote = new Lote(0);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 100);
        assertNotNull(show);
    }

    @Test
    public void testGetDespesasTotais() {
        Lote lote = new Lote(0);
        Show show = new Show(data, "Artista Famoso", 1450.0, 2000.0, Arrays.asList(lote), false, 100);
        assertEquals(3450.0, show.getDespesasTotais(), 0.01); // 15% adicional para data especial
    }

    @Test
    public void testGetDespesasTotaisDiaEspecial() {
        Lote lote = new Lote(0);
        Show show = new Show(data, "Artista Famoso", 1450.0, 2000.0, Arrays.asList(lote), true, 100);
        assertEquals(3750.0, show.getDespesasTotais(), 0.01); // 15% adicional para data especial
    }

    @Test
    public void testGetReceitaLiquida() {
        Ticket ticket = new Ticket(TicketType.NORMAL);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals(10.0 - 3000.0, show.getReceitaLiquida(precoNormal), 0.01);
    }

    @Test
    public void testTicketCreation(){
        Ticket ticket = new Ticket(ticketType);
        assertEquals(this.ticketType, ticket.getType());
    }
}