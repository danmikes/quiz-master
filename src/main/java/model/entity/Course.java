package model.entity;

import model.entity.user.Coordinator;

/*Een cursus heeft een naam en een coördinator. Een cursus heeft
nul of meer groepen die deze cursus volgen. Bij een cursus horen nul of
meer quizes.*/
public class Course {

    private String nameCourse;
    private String dateCourse;
    private Coordinator coordinator;

    public Course() {
        this("onbekend", "onbekend");
    }

    public Course(String nameCourse, String dateCourse) {
        this(nameCourse, dateCourse, new Coordinator());
    }

    public Course(String nameCourse, String dateCourse, Coordinator coordinator) {
        this.nameCourse = nameCourse;
        this.dateCourse = dateCourse;
        this.coordinator = coordinator;
    }


    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(String dateCourse) {
        this.dateCourse = dateCourse;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public String toString() {
        return nameCourse + ": date = " + dateCourse + " coordinator = " + coordinator.toString();
    }

}
