package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.dao.CourseDAO;
import model.entity.Course;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

import java.util.List;

public class ManageCoursesController {

  private CourseDAO cdao = new CourseDAO();

  @FXML
  private ListView<String> courseList = new ListView<String>();

  public void setup() {
      List<Course> nameList = cdao.getCourses();
      ObservableList<String> names = FXCollections.observableArrayList();

      for (Course course : nameList) {
        String name = course.getNameCourse();
        names.add(name);
      }
      courseList.setItems(names);
  }

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doCreateCourse(ActionEvent event){}

  public void doChangeCourse(ActionEvent event){}

  public void doDeleteCourse(ActionEvent event){}
}
