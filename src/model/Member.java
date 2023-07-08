package model;

public class Member {

    protected String name;
    protected String city;
    protected String username;
    protected String password;
    protected String role;
    protected int point;

    public Member(String name, String city, String username, String password) {
        this.name = name;
        this.city = city;
        this.username = username;
        this.password = password;
        role = "member";
        point = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getROLE() {
        return role;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
