package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.AbstractDAO;
import model.dao.QuizDAO;
import model.dao.UserDAO;
import model.entity.Course;
import model.entity.Quiz;
import view.Main;
import view.SceneManager;

public class NewQuizController {

    QuizDAO qdao = new QuizDAO();
    UserDAO udao = new UserDAO();

    @FXML
    private Label newQuizLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField nrOfQuestionsField;
    @FXML
    private TextField thresholdField;

    public void setup(Course course) {
        // wat moet hier komen???
        newQuizLabel.setText(course.getNameCourse() + " - Nieuwe Quiz");
    }

    public void doNewQuiz(ActionEvent event) {
//        String selectedCourse = "Programming"; // test
        // toon scherm voor aanmaken van nieuwe quiz
        int nrQuiz; // nodig?
        String nameQuiz = nameField.getText();
        int nrQuestions = Integer.parseInt(nrOfQuestionsField.getText());
        int threshold = Integer.parseInt(thresholdField.getText());

        // Quiz aanmaken met ingevulde informatie en versturen naar database.
        // Toon melding over succes/falen van actie.
        Quiz quiz = new Quiz(nameQuiz, nrQuestions, threshold);
        if (nameQuiz.isEmpty() || nrQuestions == 0 || threshold == 0) {
            newQuizLabel.setText("Maak nieuwe quiz ----- Quiz niet aangemaakt, vul alle velden in");
        } else {
            qdao.newQuiz(quiz);
            newQuizLabel.setText("Maak nieuwe quiz ----- Quiz aangemaakt");
            AbstractDAO.db.closeConnection();
        }
    }

    // Terugkeren naar Welcome menu
    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
    }
}
