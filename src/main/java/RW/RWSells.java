package RW;

import model.Product;
import model.Sells;

import java.io.*;
import java.util.ArrayList;

public class RWSells {
    private ArrayList<Sells> sells;
    private static final String file = "sells.bin";

    private File fi;

    public RWSells() {
        sells = new ArrayList<>();
        fi = new File(file);
        if (fi.exists()) {
            readSells();
            //setNr();
        } else {
            writeSells();
        }
    }

    private void writeSells() {
        try {
            FileOutputStream fos = new FileOutputStream(fi);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sells);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    private void readSells() {
        try {
            FileInputStream fis = new FileInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sells = (ArrayList<Sells>) ois.readObject();
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

    public ArrayList<Sells> getSells() {
        return sells;
    }

    public void add(Sells x) {
        sells.add(x);
        writeSells();
    }

    public void delete(Sells x) {
        sells.remove(x);
        writeSells();
    }

}