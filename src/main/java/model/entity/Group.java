package model.entity;

import model.entity.user.Coordinator;
import model.entity.user.Student;
import java.util.ArrayList;

/*Een groep is een aantal studenten die een bepaalde cursus volgt,
daarom heeft iedere groep een cursus. Een student die meerdere cursussen
volgtwordt voor iedere cursus in een groep ingedeeld, het is dus niet zo dat
een student voor het hele stdiejaar in dezelfde groep zit. Iedere groep heeft
een naam en een docent. Een coordinator (van de cursus bij deze groep of
van een andere cursus) kan ook docent van een groep zijn.*/
public class Group {
  private String nameGroup;
  private Course course;
  private Coordinator coordinator;
  private ArrayList<Student> students = new ArrayList<Student>();

  public Group(){
    this("onbekend", new Course(), new Coordinator(), new ArrayList<Student>());
  }

  public Group(String nameGroup, Course course, Coordinator coordinator, ArrayList<Student> students){
      super();
      this.nameGroup = nameGroup;
      this.course = course;
      this.coordinator = coordinator;
      this.students = students;
  }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

@Override
    public String toString(){
      return nameGroup + ", course " + course.toString() + ", coordinator " + coordinator.toString() + students;
    }

}
