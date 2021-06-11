package controller;

import javafx.event.ActionEvent;
import view.SceneManager;

public class LoginFailedController {

  private SceneManager manager = SceneManager.getSceneManager();

  public void doTryAgain(ActionEvent event) {
    SceneManager.getSceneManager().showLoginScene();

  }

  public void doQuit(ActionEvent event) {
    System.exit(0);


  }
}
