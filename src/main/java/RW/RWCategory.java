package RW;

import model.Category;
import model.Supplier;

import java.io.*;
import java.util.ArrayList;

public class RWCategory {
    private ArrayList<Category> categories;
    private static final String file="categories.bin";
    private File fi;
    public RWCategory() {
        categories=new ArrayList<>();
        fi=new File(file);
        if(fi.exists()) {
            readCategories();
            //setNr();
        }
        else{
            writeCategories();
        }
    }

    private void writeCategories() {
        try {
            FileOutputStream fos=new FileOutputStream(fi);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(categories);
            oos.close();fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }
    private void readCategories() {
        try {
            FileInputStream fis=new FileInputStream(fi);
            ObjectInputStream ois=new ObjectInputStream(fis);
            categories=(ArrayList<Category>)ois.readObject();
            ois.close();fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }
    public ArrayList<Category> getCategories() {
        return categories;
    }
    public ArrayList<String> getCategoriesNames() {
        ArrayList<String> s = new ArrayList<String>();
        for(Category c:categories)
            s.add(c.getCategory());
        return s;
    }
    public void add(Category x) {
        categories.add(x);
        writeCategories();
    }
    public void delete(Supplier x) {
        categories.remove(x);
        writeCategories();
    }
    public void update() {
        writeCategories();
    }
}
