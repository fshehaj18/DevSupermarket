package model;

public class Admin extends User implements Checker{


    public Admin(String username, String password, String level, DateFormat bday, String name, String email, String phone, double salary) {
        super(username, password, level, bday, name, email, phone, salary);
    }

    @Override
    public boolean checkUser(String username, String password) {
        return this.getUsername().equals(username) && this.getPassword().equals(password);
    }
}
