package model.entity.user;

public class User {

    // variabelen declareren
    private String name;
    private String password;
    private String role;

    // constructor(s)
    public User(){
        this("onbekend","onbekend","onbekend");
    }

    public User(String name, String password, String role) {
        super();
        this.name = name;
        this.password = password;
        this.role = role;
    }
// getters en setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){ this.role = role;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
