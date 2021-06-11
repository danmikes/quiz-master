package model.entity.user;

public class Coordinator extends Teacher {
    private static final String ROLE = "Coordinator";

    public Coordinator(){
        super();
    }

    public Coordinator(String name, String password, String role){
        super(name, password, ROLE);
    }

    public String getRole() { return ROLE;}

    @Override
    public String toString(){
        return super.getRole() + ": naam " + super.getName();
    }
}
