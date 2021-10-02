package model;

import java.io.Serializable;

public class Purchases implements Serializable {

    private String barcode;
    private int quantity;
    private DateFormat purchaseDate;
    private double sellPrice;
    private DateFormat expiryDate;

    public Purchases(String barcode, int quantity, DateFormat purhcaseDate,double sellPrice, DateFormat expiryDate) {
        this.barcode = barcode;
        this.quantity = quantity;
        this.purchaseDate = purhcaseDate;
        this.sellPrice = sellPrice;
        this.expiryDate = expiryDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DateFormat getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(DateFormat purhcaseDate) {
        this.purchaseDate = purhcaseDate;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public DateFormat getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(DateFormat expiryDate) {
        this.expiryDate = expiryDate;
    }
}
