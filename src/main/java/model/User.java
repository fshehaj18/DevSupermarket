package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID=156646846846846842L;

    private static int nrUsers = 0;
    private String username;
    private String password;
    private String level;
    private String name;
    private DateFormat bday;
    private String email;
    private String phone;
    private double salary;
    private final int id;



    public User(String username, String password, String level,DateFormat bday, String name, String email, String phone, double salary) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.bday = bday;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.id = nrUsers++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateFormat getBday() {
        return bday;
    }

    public void setBday(DateFormat bday) {
        this.bday = bday;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static int getNrUsers() {
        return nrUsers;
    }

    public static void setNr(int x) {
        nrUsers = x;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return
                String.valueOf(salary) ;

    }
}
