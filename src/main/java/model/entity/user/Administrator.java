package model.entity.user;

import model.entity.Course;
import model.entity.Group;

import java.util.ArrayList;

/*
Een administrator maakt klassen [groepen], deelt cursisten [studenten]
in en wijst een leraar [docent] toe aan die klas. Ook bepaalt hij wie er coördinator is
van die cursus.
Misschien interface(s)?
*/
public class Administrator extends User{

  private static final String ROLE = "Administrator";

  public Administrator(){
    super();
  }

  public Administrator(String naam, String password, String role){
      super(naam, password, ROLE);
  }

  public String getRole() { return ROLE;}

//  public Group createGroup(String nameGroup, Course course, Coordinator coordinator, ArrayList<Student> students){
//    return new Group(nameGroup, course, coordinator, students);
//  }

//  public void assignStudentCursus2Groep(Student s, Course c, Groep g){

//  }

    //  public void assignDocentCursus2Groep(Docent d, Course c, Groep g){

//  }

//  public void assignCoordinator2Cursus(Coordinator co, Course cu){
//
//  }

  @Override
  public String toString(){
      return super.getRole() + ": naam " + super.getName();
  }

}
