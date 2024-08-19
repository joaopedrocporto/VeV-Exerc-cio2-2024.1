package ticket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Show{
    private Date data;
    private String artista;
    private double cache;
    private double totalDespesasInfraestrutura;
    private List<Lote> lotesIngressos;
    private Boolean dataEspecial;
    private double precoNormal;

    public Show(Date data, String artista, double cache, double totalDespesasInfraestrutura, List<Lote> lotesIngressos, Boolean dataEspecial, double precoNormal){
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.totalDespesasInfraestrutura = totalDespesasInfraestrutura;
        this.lotesIngressos = lotesIngressos;
        this.dataEspecial = dataEspecial;
        this.precoNormal = precoNormal;
    }

    public Date getData() {
        return this.data;
    }
    @Override
    public String toString(){
        return "Relatório do Show:" + 
                " Artista: " + artista +
                " Data Especial: " + dataEspecial +
                " Despesas Totais: R$ " + getDespesasTotais() +
                " Receita Líquida: R$ " + getReceitaLiquida(precoNormal) +
                " Status Financeiro: " + getStatusFinanceiro(precoNormal) +
                " Ingressos Vendidos (VIP): " + getTotalTicketsSoldByType(TicketType.VIP) +
                " Ingressos Vendidos (MEIA_ENTRADA): " + getTotalTicketsSoldByType(TicketType.MEIA_ENTRADA) +
                " Ingressos Vendidos (NORMAL): " + getTotalTicketsSoldByType(TicketType.NORMAL);
    }

    public int getTotalTicketsSoldByType(TicketType type) {
        int count = 0;
        for (Lote lote : lotesIngressos) {
            for (Ticket ticket : lote.getTickets()) {
                if (ticket.getType() == type && ticket.isSold()) {
                    count++;
                }
            }
        }
        return count;
    }

    public double getDespesasTotais() {
        double despesasTotais = totalDespesasInfraestrutura + cache;
        if (dataEspecial) {
            double novaDespesaInfraestrutura = totalDespesasInfraestrutura * 1.15;
            despesasTotais = novaDespesaInfraestrutura + cache;
        }
        return despesasTotais;
    }

    public double getReceitaLiquida(double precoNormal) {
        double receita = 0;

        for (Lote lote : lotesIngressos) {
            for (Ticket ticket : lote.getTickets()) {
                if (ticket.isSold()) {
                    double preco = ticket.getPrice(precoNormal);
                    receita += lote.applyDiscount(ticket, preco);
                }
            }
        }
        return receita - getDespesasTotais();
    }

    public double getPrecoTicketPorLote(double precoNormal, Lote lote){
        for (Lote lotes : lotesIngressos) {
            for (Ticket ticket : lote.getTickets()) {
                if(lotes.equals(lote)){
                    return ticket.getPrice(precoNormal) * (1 - lotes.getDiscount() / 100);
                }

            }
        }
        return 0.0;
    }

    public String getStatusFinanceiro(double precoNormal) {
        double receitaLiquida = getReceitaLiquida(precoNormal);
        if (receitaLiquida > 0) {
            return "LUCRO";
        } else if (receitaLiquida == 0) {
            return "ESTÁVEL";
        } else {
            return "PREJUÍZO";
        }
    }
}