package accountProcessor.src.main.java.com;

import java.util.Date;

public class Account {
    public int accountCode;
    public double value;
    public Date date;

    public Account(double accountValue, int accountCode, Date date) {
        this.accountCode = accountCode;
        this.date = date;
        this.value = accountValue;

    }

    public int getId(){
        return this.accountCode;
    }
}
