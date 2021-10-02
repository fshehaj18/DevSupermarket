package model;

public class Bill {
    private static int billNo = 0;
    private int cashierID;
    private DateFormat date;

    public static int getBillNo() {
        return billNo;
    }


    public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }
}
