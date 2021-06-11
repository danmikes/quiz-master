package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.Main;
import view.SceneManager;

public class SelectQuizForStudentController {

  @FXML
  private Button doQuizButton;

  public void doQuiz() {}

  public void setup() {
  }

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }
}
