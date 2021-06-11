package model.entity.user;

/**
 * author EvdP - Team Fenix
 */

public class Maintainer extends User {
    // variabelen
//    private String name;
//    private String password;
//    private String role;
    private static final String ROLE = "Beheerder";

    //constructor(s)
    public Maintainer() {
        super();
    }

    public Maintainer(String name, String password, String role) {
        super(name,password,ROLE);
    }

// getters en setters
/*
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
*/
    public String getRole() {
        return ROLE;
    }
//    public void setRole(String role) {
//        this.role = role;
//    }


    // methoden
    @Override
    public String toString() {
        return "Maintainer{" +
                "name='" + super.getName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", role='" + ROLE + '\'' +
                '}';
    }


}
