package model.entity.user;

public class Teacher extends User {
    private static final String ROLE = "Teacher";

    public Teacher(){
        super();
    }

    public Teacher(String name, String password, String role){
        super(name, password, ROLE);
    }

    public String getRol() { return ROLE;}


    @Override
    public String toString(){
        return super.getRole() + ": naam " + super.getName();
    }

}
