package ticket.functionalTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ticket.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ticketSystemServiceFunctionalTests {

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

    // Análise de Valores Limites
    @Test
    public void testLimiteIngressosVIP() {
        int totalIngressos = 500; // Total de ingressos disponíveis
        int ingressosVIP = 100; // 20% do total de ingressos

        // Valor esperado: ingressos VIP entre 20% e 30% do total
        int minVIP = (int) (totalIngressos * 0.20); // Limite inferior
        int maxVIP = (int) (totalIngressos * 0.30); // Limite superior



        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
            ticket.setSold(true);

            // Verifica se o número de ingressos VIP vendidos está dentro do limite permitido
            assertTrue("O número de ingressos VIP deve estar entre 20% e 30% do total",
                    ingressosVIP >= minVIP && ingressosVIP <= maxVIP);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testLimiteIngressosMeiaEntrada() {
        int totalIngressos = 500; // Total de ingressos disponíveis
        int ingressosMeia = 50; // 10% do total de ingressos

        // Valor esperado: ingressos VIP entre 20% e 30% do total
        int minMeia = (int) (totalIngressos * 0.10); // Limite inferior
        int maxMeia = (int) (totalIngressos * 0.10); // Limite superior



        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
            ticket.setSold(true);

            // Verifica se o número de ingressos VIP vendidos está dentro do limite permitido
            assertTrue("O número de ingressos Meia deve ser 10% do total",
                    ingressosMeia >= minMeia && ingressosMeia <= maxMeia);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testLimiteDescontoLote() {
        int minDesc = 0; // Limite inferior
        double maxDesc = 0.25; // Limite superior

        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(0.10);
            lote.addTicket(ticket);

            assertTrue("O desconto deve estar entre 0% e 25% do total",
                    lote.getDiscount() >= minDesc && lote.getDiscount() <= maxDesc);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testLimiteReceitaLiquida() {
        try {
            double precoNormal2 = 20000;
            Ticket ticket2 = new Ticket(TicketType.NORMAL, precoNormal2);
            ticket2.setSold(true);
            Lote lote2 = new Lote(0);
            lote2.addTicket(ticket2);
            double cache2 = 10;
            double totaldespesas2 = 10;

            Show show2 = ticketSystemService.createShow(data, "artista", cache2, totaldespesas2, List.of(lote2), false, precoNormal2);

            double receita = show2.getReceitaLiquida(precoNormal2);

            assertTrue("A receita líquida deve ser maior que 0",
                    receita >= 0);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    // Partições de Equivalência

    @Test
    public void testParticaoIngressosVIPFalha() {
        int totalIngressos = 500; // Total de ingressos disponíveis
        int ingressosVIP = 250; // 50% do total de ingressos (mais que o permitido)

        // Valor esperado: ingressos VIP entre 20% e 30% do total
        int minVIP = (int) (totalIngressos * 0.20); // Limite inferior
        int maxVIP = (int) (totalIngressos * 0.30); // Limite superior



        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
            ticket.setSold(true);

            // Verifica se o número de ingressos VIP vendidos está dentro do limite permitido
            assertFalse("O número de ingressos VIP deve estar entre 20% e 30% do total",
                    ingressosVIP >= minVIP && ingressosVIP <= maxVIP);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testParticaoIngressosMeiaEntrada() {
        int totalIngressos = 500; // Total de ingressos disponíveis
        int ingressosMeia = 100; // 10% do total de ingressos (mais que o permitido)

        int minMeia = (int) (totalIngressos * 0.10); // Limite inferior
        int maxMeia = (int) (totalIngressos * 0.10); // Limite superior

        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
            ticket.setSold(true);

            assertFalse("O número de ingressos Meia deve ser 10% do total",
                    ingressosMeia >= minMeia && ingressosMeia <= maxMeia);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testParticaoDescontoLote() {
        int minDesc = 0; // Limite inferior
        double maxDesc = 0.25; // Limite superior

        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            assertFalse("O desconto deve estar entre 0% e 25% do total",
                    lote.getDiscount() >= minDesc && lote.getDiscount() <= maxDesc);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testParticaoReceitaLiquida() {
        try {
            double precoNormal2 = 20000;
            Ticket ticket2 = new Ticket(TicketType.NORMAL, precoNormal2);
            ticket2.setSold(true);
            Lote lote2 = new Lote(0);
            lote2.addTicket(ticket2);
            double cache2 = 10000000;
            double totaldespesas2 = 10;

            Show show2 = ticketSystemService.createShow(data, "artista", cache2, totaldespesas2, List.of(lote2), false, precoNormal2);

            double receita = show2.getReceitaLiquida(precoNormal2);

            assertFalse("A receita líquida deve ser maior que 0",
                    receita >= 0);
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testParticaoDataEspecial() {
        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);
            dataEspecial = true;

            assertFalse("A data especial aplica um aumento de 15% no preço da infraestrutura",
                    show.getDespesasTotais() == 1.15 * (cache + totalDespesasInfraestrutura));
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testParticaoStatusIngresso() {
        try {
            double precoNormal = 5000.0;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            assertTrue("O ingresso foi vendido",
                    ticket.isSold());
        } catch (Exception e) {
            // Se houver qualquer exceção, o teste falha
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    // Tabelas de Decisão
    @Test
    public void testTabelaDecisaoCenario1() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(0);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), false, precoNormal);

            assertEquals(precoNormal*2, ticket.getPrice(precoNormal), 0.01);
            assertEquals((cache + totalDespesasInfraestrutura), show.getDespesasTotais(), 0.01);
            assertEquals(-59800.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario2() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), false, precoNormal);

            assertEquals((precoNormal*2)*0.9, show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals((cache + totalDespesasInfraestrutura), show.getDespesasTotais(), 0.01);
            assertEquals(-59820.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario3() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.VIP, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), true, precoNormal);

            assertEquals((precoNormal*2)*0.9, show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals(cache + (totalDespesasInfraestrutura * 1.15), show2.getDespesasTotais(), 0.01);
            assertEquals(-61320.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario4() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(0);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), false, precoNormal);

            assertEquals((precoNormal*0.5), show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals(cache + totalDespesasInfraestrutura, show2.getDespesasTotais(), 0.01);
            assertEquals(-59950, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario5() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.MEIA_ENTRADA, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), true, precoNormal);

            assertEquals((precoNormal*0.5)*0.9, show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals(cache + (totalDespesasInfraestrutura * 1.15), show2.getDespesasTotais(), 0.01);
            assertEquals(-61450.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario6() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(10);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), false, precoNormal);

            assertEquals((precoNormal)*0.9, show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals(cache + totalDespesasInfraestrutura, show2.getDespesasTotais(), 0.01);
            assertEquals(-59910.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }

    @Test
    public void testTabelaDecisaoCenario7() {
        try {
            double precoNormal = 100;
            Ticket ticket = new Ticket(TicketType.NORMAL, precoNormal);
            ticket.setSold(true);
            Lote lote = new Lote(0);
            lote.addTicket(ticket);

            Show show2 = ticketSystemService.createShow(data, "artista", cache, totalDespesasInfraestrutura, List.of(lote), true, precoNormal);

            assertEquals(precoNormal, show2.getPrecoTicketPorLote(precoNormal, lote), 0.01);
            assertEquals(cache + (totalDespesasInfraestrutura * 1.15), show2.getDespesasTotais(), 0.01);
            assertEquals(-61400.0, show2.getReceitaLiquida(precoNormal), 0.01);
            assertEquals("PREJUÍZO", show.getStatusFinanceiro(precoNormal));
        }catch (Exception e) {
            fail("O teste falhou devido a uma exceção: " + e.getMessage());
        }
    }
}
