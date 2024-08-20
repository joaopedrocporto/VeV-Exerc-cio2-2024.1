package bill;

import java.util.Date;

public class Invoice {
    private Integer idInvoice;
    private String status;
    private Double value;
    private String name;
    private Date date;


    public Invoice(Integer code, Double totalValue, Date date, String clientName) {
        this.idInvoice = code;
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

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public String getStatus() {
        return status;
    }

    public Double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

}
