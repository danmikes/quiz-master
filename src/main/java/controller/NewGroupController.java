package controller;

import javafx.event.ActionEvent;
import view.Main;
import view.SceneManager;

public class NewGroupController {

  public void setup() {}

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doCreateGroup(ActionEvent event){}
}
