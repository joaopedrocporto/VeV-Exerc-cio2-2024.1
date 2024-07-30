package ticket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Show{
    private Date data;
    private String artista;
    private double cache;
    private double totalDespesasInfraestrutura;
    private Integer lotesIngressos;
    private Boolean dataEspecial;

    public Show(Date data, String artista, double cache, double totalDespesasInfraestrutura, Integer lotesIngressos, Boolean dataEspecial){
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.totalDespesasInfraestrutura = totalDespesasInfraestrutura;
        this.lotesIngressos = lotesIngressos;
        this.dataEspecial = dataEspecial;
    }

    public Date getData() {
        return this.data;
    }
}