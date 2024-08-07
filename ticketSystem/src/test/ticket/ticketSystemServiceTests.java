package ticket;

import org.junit.Test;
import org.junit.Before;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        precoNormal = 10;
        ticketType = TicketType.NORMAL;
        Ticket ticket = new Ticket(ticketType, precoNormal);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals(10.0 - 3000.0, show.getReceitaLiquida(precoNormal), 0.01);
    }

    @Test
    public void testGetStatusFinanceiroPrejuizo() {
        double precoNormal = 10.0;
        Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
    }

    @Test
    public void testGetStatusFinanceiroEstavel() {
        double precoNormal = 3000.0;
        Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals("ESTÁVEL", show.getStatusFinanceiro(precoNormal));
    }

    @Test
    public void testGetStatusFinanceiroLucro() {
        double precoNormal = 5000.0;
        Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals("LUCRO", show.getStatusFinanceiro(precoNormal));
    }

    @Test
    public void testVendaPorTipoDeIngressoNormal(){
        double precoNormal = 10.0;
        Ticket ticket1 = new Ticket(TicketType.NORMAL, precoNormal);
        Ticket ticket2 = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
        Ticket ticket3 = new Ticket(TicketType.VIP, precoNormal);
        Ticket ticket4 = new Ticket(TicketType.NORMAL, precoNormal);
        ticket1.setSold(true);
        ticket2.setSold(true);
        ticket3.setSold(true);
        ticket4.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket1);
        lote.addTicket(ticket2);
        lote.addTicket(ticket3);
        lote.addTicket(ticket4);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals(2, show.getTotalTicketsSoldByType(TicketType.NORMAL));
    }

    @Test
    public void testVendaPorTipoDeIngressoMeia(){
        double precoNormal = 10.0;
        Ticket ticket1 = new Ticket(TicketType.NORMAL, precoNormal);
        Ticket ticket2 = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
        Ticket ticket3 = new Ticket(TicketType.VIP, precoNormal);
        Ticket ticket4 = new Ticket(TicketType.NORMAL, precoNormal);
        ticket1.setSold(true);
        ticket2.setSold(true);
        ticket3.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket1);
        lote.addTicket(ticket2);
        lote.addTicket(ticket3);
        lote.addTicket(ticket4);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals(1, show.getTotalTicketsSoldByType(TicketType.MEIA_ENTRADA));
    }

    @Test
    public void testVendaPorTipoDeIngressoVip(){
        double precoNormal = 10.0;
        Ticket ticket1 = new Ticket(TicketType.NORMAL, precoNormal);
        Ticket ticket2 = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
        Ticket ticket3 = new Ticket(TicketType.VIP, precoNormal);
        Ticket ticket4 = new Ticket(TicketType.NORMAL, precoNormal);
        ticket1.setSold(true);
        ticket2.setSold(true);
        ticket3.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket1);
        lote.addTicket(ticket2);
        lote.addTicket(ticket3);
        lote.addTicket(ticket4);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals(1, show.getTotalTicketsSoldByType(TicketType.VIP));
    }

    @Test
    public void testGetRelatorio(){
        double precoNormal = 10.0;
        Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
        ticket.setSold(true);
        Lote lote = new Lote(0);
        lote.addTicket(ticket);
        Show show = new Show(data, "Artista Famoso", 1000.0, 2000.0, Arrays.asList(lote), false, 10);

        assertEquals("Relatório do Show: Artista: Artista Famoso Data Especial: false Despesas Totais: R$ 3000.0 Receita Líquida: R$ -2990.0 Status Financeiro: PREJUÍZO Ingressos Vendidos (VIP): 0 Ingressos Vendidos (MEIA_ENTRADA): 0 Ingressos Vendidos (NORMAL): 1", show.toString());
    }


    @Test
    public void testTicketCreation() {
        precoNormal = 10.0;
        Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
        assertNotNull(ticket);
        assertEquals(TicketType.VIP, ticket.getType());
        assertFalse(ticket.isSold());
    }

    @Test
    public void testTicketIdIncrement() {
        Ticket ticket1 = new Ticket(TicketType.NORMAL, precoNormal);
        Ticket ticket2 = new Ticket(TicketType.NORMAL, precoNormal);
        assertEquals(ticket1.getId() + 1, ticket2.getId());
    }

    @Test
    public void testTicketPriceCalculation() {
        double normalPrice = 10.0;
        Ticket normalTicket = new Ticket(TicketType.NORMAL, precoNormal);
        Ticket vipTicket = new Ticket(TicketType.VIP, precoNormal);
        Ticket meiaEntradaTicket = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);

        assertEquals(normalPrice, normalTicket.getPrice(normalPrice), 0.01);
        assertEquals(2 * normalPrice, vipTicket.getPrice(normalPrice), 0.01);
        assertEquals(0.5 * normalPrice, meiaEntradaTicket.getPrice(normalPrice), 0.01);
    }

    @Test
    public void testLoteCreation() {
        Lote lote = new Lote(10.0);
        assertNotNull(lote);
        assertEquals(10.0, lote.getDiscount(), 0.01);
        assertTrue(lote.getTickets().isEmpty());
    }

    @Test
    public void testLoteIdIncrement() {
        Lote lote1 = new Lote(10.0);
        Lote lote2 = new Lote(0);
        assertEquals(lote1.getId() + 1, lote2.getId());
    }

    @Test
    public void testAddTicketToLote() {
        Lote lote = new Lote(0);
        Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
        lote.addTicket(ticket);

        List<Ticket> tickets = lote.getTickets();
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        assertEquals(ticket, tickets.get(0));
    }

    @Test
    public void testApplyDiscount() {
        double precoNormal = 10.0;
        Lote lote = new Lote(20.0);
        Ticket vipTicket = new Ticket(TicketType.VIP, precoNormal);

        double discountedPrice = lote.applyDiscount(vipTicket, vipTicket.getPrice(precoNormal));
        assertEquals(16.0, discountedPrice, 0.01); // 20% de desconto
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDiscountMore() {
        new Lote(30.0); // Desconto acima de 25%
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDiscountLess() {
        new Lote(-1.0); // Desconto abaixo de 0%
    }
}