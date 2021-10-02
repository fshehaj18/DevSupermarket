package RW;

import model.Product;
import model.Supplier;

import java.io.*;
import java.util.ArrayList;

public class RWSuppliers {
    private ArrayList<Supplier> suppliers;
    private static final String file="suppliers.bin";
    private File fi;
    public RWSuppliers() {
        suppliers=new ArrayList<>();
        fi=new File(file);
        if(fi.exists()) {
            readSuppliers();
            //setNr();
        }
        else{
            writeSuppliers();
        }
    }

    private void writeSuppliers() {
        try {
            FileOutputStream fos=new FileOutputStream(fi);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(suppliers);
            oos.close();fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }
    private void readSuppliers() {
        try {
            FileInputStream fis=new FileInputStream(fi);
            ObjectInputStream ois=new ObjectInputStream(fis);
            suppliers=(ArrayList<Supplier>)ois.readObject();
            ois.close();fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    public ArrayList<String> getSupplierNames() {

        ArrayList<String> supplierNames = new ArrayList<>(0);
        for(Supplier s:suppliers)
            supplierNames.add(s.getName());
        return supplierNames;
    }
    public void add(Supplier x) {
        suppliers.add(x);
        writeSuppliers();
    }
    public void delete(Supplier x) {
        suppliers.remove(x);
        writeSuppliers();
    }
    public void update() {
        writeSuppliers();
    }
}
