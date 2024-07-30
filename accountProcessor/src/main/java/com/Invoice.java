package accountProcessor.src.main.java.com;

import java.util.Date;

public class Invoice {
    public String status;
    public double value;
    public String name;
    public Date date;

    public Invoice(double totalValue, Date date, String clientName) {
        this.date = date;
        this.name = clientName;
        this.value = totalValue;
        this.status = "Pendente";
    }
}
