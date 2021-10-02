package model;

import java.io.Serializable;

public class Sells implements Serializable {

    private String barcode;
    private String name;
    private int quantity;
    private DateFormat date;
    private double sellPrice;
    private static int billNr = 0;

    public Sells(String barcode, String name, int quantity, DateFormat date, double sellPrice) {
        this.barcode = barcode;
        this.quantity = quantity;
        this.date = date;
        this.sellPrice = sellPrice;
        this.billNr++;
        this.name = name;
    }

    public static int getBillNr() {
        return billNr;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
