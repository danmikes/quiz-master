package controller;

import javafx.event.ActionEvent;
import model.entity.Group;
import view.Main;
import view.SceneManager;

public class ChangeGroupController {

  public void setup(Group group) {}

  // Terugkeren naar Welcome menu

  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doChangeGroup(ActionEvent event){}
}
