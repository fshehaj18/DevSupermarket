package RW;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RWUser {

    private ArrayList<User> users;
    private static final String file="users.bin";
    private File fi;
    public RWUser() {
        users=new ArrayList<>();
        fi=new File(file);
        if(fi.exists()) {
            readUsers();
            setNr();

        } else {
            createFile();
        }
    }

    private void setNr() {
        int max=0;
        for(User i:users) if(max<i.getId()) max=i.getId();
        User.setNr(max+1);
    }
    private void createFile() {
        System.out.println("First usage -> Please register Admin Data");
        Scanner in = new Scanner(System.in);
        System.out.print("Username: ");	String user=in.next();
        System.out.print("Password: ");	String pass=in.next();
        System.out.print("Name: ");	String name=in.next();
        users.add(new Admin(user,pass,"Admin",null,name,null,"0673",1000));
        writeUsers();
    }
    private void writeUsers() {
        try {
            FileOutputStream fos=new FileOutputStream(fi);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }
    private void readUsers() {
        try {
            FileInputStream fis=new FileInputStream(fi);
            ObjectInputStream ois=new ObjectInputStream(fis);
            users=(ArrayList<User>)ois.readObject();
            ois.close();fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }
    public ArrayList<User> getUsers() {

        return users;
    }
    public ArrayList<User> getEmployees() {
        ArrayList<User> employees= new ArrayList<>();
        for(User i:users)
            if ((i instanceof Cashier) || (i instanceof Economist))
                employees.add(i);
        return employees;
    }
    public void add(User x) {
        users.add(x);
        writeUsers();
    }
    public void delete(User x) {
        users.remove(x);
        writeUsers();
    }
    /*public Person getPersonById(int id) {
        for(User i:users)
            if(i.getId()==id) return (Person) i;
        return null;
    }*/
    public User checkLogin(String user,String pass) {
        for(User i:users)
            if( ((Checker) i).checkUser(user, pass))
                return i;
        return null;
    }
    public boolean usernameExists(String username){
        for(User i:users)
            if(username == i.getUsername())
                return true;
        return false;
    }
    public User getUserById(int id){
        for(User i:users){
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public void editUser(User user, String name, DateFormat date, String email, String phone,double salary) {
        for(User u:users) {
            if(u.getId() == user.getId()){

            if (u instanceof Economist) {
                ((Economist) u).setName(name);
                ((Economist) u).setBday(date);
                ((Economist) u).setEmail(email);
                ((Economist) u).setPhone(phone);
                ((Economist) u).setSalary(salary);
            } else if (u instanceof Cashier) {
                ((Cashier) u).setName(name);
                ((Cashier) u).setBday(date);
                ((Cashier) u).setEmail(email);
                ((Cashier) u).setPhone(phone);
                ((Cashier) u).setSalary(salary);
            }
            update();
        }}

    }
    public void update() {
        writeUsers();
    }
}
