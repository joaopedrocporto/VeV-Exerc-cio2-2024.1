package accountProcessor.src.main.java.com;

import java.util.Date;

public class Invoice {
    private Integer idInvoice;
    public String status;
    public Double value;
    public String name;
    public Date date;

    public Invoice(Double totalValue, Date date, String clientName) {
        this.date = date;
        this.name = clientName;
        this.value = totalValue;
        this.status = "PENDENTE";
    }

    public Integer getId(){
        return this.idInvoice;
    }

    public void confirmPayment(){
        this.status = "PAGA";
    }
}
