package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.dao.AbstractDAO;
import model.dao.QuestionDAO;
import model.dao.QuizDAO;
import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import view.Main;
import view.SceneManager;

public class NewQuestionController {

    QuestionDAO qdao = new QuestionDAO();

    // view velden
    @FXML
    private TextField nameField;
    @FXML
    private TextField nameField1;
    @FXML
    private TextField nameField2;
    @FXML
    private TextField nameField3;
    @FXML
    private TextField nameField4;
    @FXML
    private Label title;

    public void setup(Course course, Quiz quiz) {
    }

    // Terugkeren naar Welcome menu
    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
    }

    //    public Question(int nrQuestion, String textQuestion, String answerA, String answerB, String answerC, String answerD) {
    public void doNewQuestion(ActionEvent event) {
        // parameters
//        int nrQuestion;
        int nrQuestion = 1;//default value BvB
        String textQuestion = nameField.getText();
        String answerA = nameField1.getText();
        String answerB = nameField2.getText();
        String answerC = nameField3.getText();
        String answerD = nameField4.getText();

        // User aanmaken met ingevulde informatie en versturen naar database. Toont melding over (niet) geslaagde actie
//        Question question = new Question(textQuestion, answerA, answerB, answerC, answerD); BvB
        Question question = new Question(new Quiz(), nrQuestion, textQuestion, answerA, answerB, answerC, answerD);
        if (textQuestion.isEmpty() || answerA.isEmpty() || answerB.isEmpty() || answerC.isEmpty() || answerD.isEmpty()) {
            title.setText("Maak nieuwe vraag.     Vraag niet aangemaakt. Vul alle velden in");
        } else {
            qdao.newQuestion(question);
            title.setText("Maak nieuwe vraag.     Vraag aangemaakt.");
            AbstractDAO.db.closeConnection();
        }
    }

}
