package ticket;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.security.auth.login.AccountException;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private Integer lotesIngressos;
    private Boolean dataEspecial;

    @BeforeEach
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
        lotesIngressos = 1;
        dataEspecial = false;

        // Criando o objeto show
        show = ticketSystemService.createShow(data, artista, cache, totalDespesasInfraestrutura, lotesIngressos, dataEspecial);
    }

    @Test
    public void testShowCreation() {
        assertEquals(data, show.getData());
    }
}