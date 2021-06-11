package controller;

import javafx.event.ActionEvent;
import model.entity.Course;
import view.Main;
import view.SceneManager;

public class ChangeCourseController {

  public void setup(Course course) {}
  

  // Terugkeren naar Welcome menu

  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doChangeCourse(ActionEvent event){}
}
