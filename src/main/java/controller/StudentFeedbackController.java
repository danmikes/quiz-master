package controller;

/**
 *  Doel:
 *  tonen behaalde resultaten van een quiz
 *
 *  author: Evdp - team Fenix
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.entity.Quiz;
import view.Main;
import view.SceneManager;

public class StudentFeedbackController {

  @FXML
  private Label feedbackLabel;

  @FXML
  private ListView feedbackList;

  public void setup(Quiz quiz) {
    // tonen feedback: overzicht alle keren dat quiz is gedaan met behaalde resultaat
    // wijzig titel zodat deze naam quiz laat zien
    // voldoende = # vragen goed is >= dan benodigd (cesuur)
    // onvoldoende = # vragen goed < cesuur

  }







  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }
}


