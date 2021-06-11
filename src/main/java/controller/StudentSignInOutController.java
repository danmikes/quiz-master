package controller;

// Deze applicatie beheert toetsen
// Deze controller levert de functie voor studenten om in en uit te schrijven voor curssussen.
// @author team Fenix, JvdH

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.dao.CourseDAO;
import model.dao.CourseEnrollmentDAO;
import model.dao.MaintainerDAO;
import model.entity.Course;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

import java.util.List;
import java.util.Scanner;

public class StudentSignInOutController {

    private CourseDAO cdao = new CourseDAO();
    private CourseEnrollmentDAO cedao = new CourseEnrollmentDAO();

    //variabelen
    private String course;

    // view velden
    @FXML
    private Button signInButton;

    @FXML
    private Button signOutButton;

    @FXML
    private ListView<String> courseList;

    //toegevoegde declaratie rechterlijst
    @FXML
    private ListView<String> SignedInCourseList;

    //methoden

    // beginsituatue creeren, dus linker scherm met alle vakken vullen
    public void setup() {

        List<Course> fullcourseList = cdao.getCourses();
        ObservableList<String> coursesonleftscreen = FXCollections.observableArrayList();
        for (Course course : fullcourseList) {
            System.out.println(course.getNameCourse());
            String coursename = course.getNameCourse();
            coursesonleftscreen.add(coursename);
        }
        courseList.setItems(coursesonleftscreen);
    }

    // selecteren van een bestaand vak, leidend tot inschrijven daarin. Via daoVakinschrijving insert op aparte tabel Cursusinschrijving,
    // dus die functie vanuit DAO aanroepen.
    public void doSignIn(ActionEvent event) {
        //zet studentvakcombi in database
        cedao.addNewCourseEnrollment(courseList.getSelectionModel().getSelectedItem());
        //zet gekozen vak ook in rechter scherm
        String selectedCourse = courseList.getSelectionModel().getSelectedItem();
        SignedInCourseList.getItems().add(selectedCourse);
        // haal gekozen vak weg uit linker rij
        courseList.getItems().remove(selectedCourse);
    }
    // Uitschrijven van cursus
    public void doSignOut(ActionEvent event) {
        //toevoegen aan linker rij
        String deselectedCourse = SignedInCourseList.getSelectionModel().getSelectedItem();
        courseList.getItems().add(deselectedCourse);
        // uitschrijven uit database -tabel Cursus_Inschrijving
        cedao.deleteCourseEnrollment(SignedInCourseList.getSelectionModel().getSelectedItem());
        //wissen uit rechter rij
        SignedInCourseList.getItems().remove(deselectedCourse);

        //NOG DOEN IN VOLGENDE RELEASE; dan ook uitschrijven uit tabel Groep
    }

    // Terugkeren naar Welcome menu
    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());

    }
}
