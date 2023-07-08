package model;

public class Admin extends Member {

    public Admin(String name, String city, String username, String password) {
        super(name, city, username, password);
        role = "admin";
    }

}
