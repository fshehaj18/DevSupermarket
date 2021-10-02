package RW;

import model.DateFormat;
import model.Product;
import model.Purchases;

import java.io.*;
import java.util.ArrayList;

public class RWPurchases {
    private ArrayList<Purchases> purchases;
    private static final String file="purchases.bin";
    private File fi;
    public RWPurchases() {
        purchases=new ArrayList<>();
        fi=new File(file);
        if(fi.exists()) {
            readPurchases();
            //setNr();
        }
        else{
            writePurchases();
        }
    }
    private void writePurchases() {
        try {
            FileOutputStream fos=new FileOutputStream(fi);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(purchases);
            oos.close();fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }
    private void readPurchases() {
        try {
            FileInputStream fis = new FileInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(fis);
            purchases = (ArrayList<Purchases>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }
    public void getPurchasedProducts(DateFormat startDate, DateFormat endDate){

    }

    public Purchases getPurchase(String barcode,int quantity, DateFormat date, DateFormat expiryDate ){

        RWProduct rwProduct = new RWProduct();
        Product p = rwProduct.getProductByBarcode(barcode);
        Purchases purchasedItems = new Purchases(barcode,quantity,date,p.getSellPrice(),expiryDate);
        return purchasedItems;
    }
    public ArrayList<Purchases> getPurchases() {
        return purchases;
    }
    public void add(Purchases x) {
        purchases.add(x);
        writePurchases();
    }
    public void delete(Purchases x) {
        purchases.remove(x);
        writePurchases();
    }
}
