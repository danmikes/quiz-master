package model.entity.user;
/**
 * Applicatie om toetsen te beheren.
 * Klasse om studenten te beheren.
 * @author team Fenix.
 * 13 juni 2019
 */
public class Student extends User {
    private static final String ROLE = "Student";

    public Student(){
        super();
    }

    public Student(String name, String password, String role){
        super(name, password, ROLE);
   }

    public String getRole() { return ROLE;}

    @Override
    public String toString(){
        return super.getRole() + ": naam " + super.getName();
    }
}
