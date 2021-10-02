package RW;

import model.Product;
import model.Supplier;

import java.io.*;
import java.util.ArrayList;

public class RWProduct {
    private ArrayList<Product> products;
    private static final String file="products.bin";
    private File fi;
    public RWProduct() {
        products=new ArrayList<>();
        fi=new File(file);
        if(fi.exists()) {
            readProducts();
            //setNr();
        }
        else{
            writeProducts();
        }
    }
    private void writeProducts() {
        try {
            FileOutputStream fos=new FileOutputStream(fi);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(products);
            oos.close();fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }
    private void readProducts() {
        try {
            FileInputStream fis=new FileInputStream(fi);
            ObjectInputStream ois=new ObjectInputStream(fis);
            products=(ArrayList<Product>)ois.readObject();
            ois.close();fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void add(Product x) {
        products.add(x);
        writeProducts();
    }
    public void delete(Product x) {
        products.remove(x);
        writeProducts();
    }
    public Product getProductByBarcode(String barcode){
        for(Product p: products)
            if(p.getBarcode().equals(barcode))
                return p;
            return null;

    }

    public void addQuantity(String barcode,int quantity){
        for(Product p: products)
            if(p.getBarcode().equals(barcode))
                p.setQuantity(quantity + p.getQuantity());
            update();
    }

    public ArrayList<Product> getProductsOfSupplier(Supplier supplier){
        ArrayList<Product> productsOfSupplier = new ArrayList<>();
        for(Product p: getProducts())
            if(p.getSupplier().equals(supplier.getName()))
                productsOfSupplier.add(p);

            return productsOfSupplier;

    }
    public void update() {
        writeProducts();
    }
}
