package bill;

import java.util.Date;

public class Bill {
    public int billCode;
    public double value;
    public Date date;

    public Bill(double billValue, int billCode, Date date) {
        this.billCode = billCode;
        this.date = date;
        this.value = billValue;

    }

    public int getId(){
        return this.billCode;
    }
}
