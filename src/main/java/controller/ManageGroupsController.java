package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.entity.Course;
import view.Main;
import view.SceneManager;

public class ManageGroupsController {

  public void setup() {}

  @FXML
  private TextField nameField;

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doCreateGroup(ActionEvent event){

  }

  public void doChangeGroup(ActionEvent event){}

  public void doDeleteGroup(ActionEvent event){}
}
