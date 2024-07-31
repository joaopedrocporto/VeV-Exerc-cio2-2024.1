package accountProcessor.src.main.java.com;

import java.util.Date;

public class Account {
    public long accountCode;
    public double value;
    public Date date;

    public Account(double accountValue, long accountCode, Date date) {
        this.accountCode = accountCode;
        this.date = date;
        this.value = accountValue;

    }
}
