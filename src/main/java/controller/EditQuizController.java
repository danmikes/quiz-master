package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.AbstractDAO;
import model.dao.CourseDAO;
import model.dao.QuizDAO;
import model.dao.UserDAO;
import model.entity.Course;
import model.entity.Quiz;
import model.entity.user.Coordinator;
import view.Main;
import view.SceneManager;

public class EditQuizController {

    QuizDAO qdao = new QuizDAO();
    UserDAO udao = new UserDAO();

    @FXML
    private Label editQuizLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField nrOfQuestionsField;
    @FXML
    private TextField thresholdField;

    public void setup(Course activeCourse, Quiz activeQuiz) {
        // wat moet hier komen???
//        String selectedCourse = "Programming"; // test
//        Coordinator coordinator = (Coordinator)udao.findUserByName("Corrie");
//        Course course = new Course("Programming", "2019-10-10", coordinator);
//        hoe bestaande waarden van quiz(nameQuiz, nrQuestions, threshold) tonen ?
        editQuizLabel.setText(activeCourse.getNameCourse() + " - " + activeQuiz.getNameQuiz());
        nameField.setText(activeQuiz.getNameQuiz()); // test
        nrOfQuestionsField.setText(Integer.toString(activeQuiz.getNrQuestions())); // test
        thresholdField.setText(Integer.toString((activeQuiz.getThreshold()))); // test
    }

    public void doEditQuiz(ActionEvent event) {
        // toon scherm voor wijzigen van bestaande quiz
        int nrQuiz; // nodig?
        String nameQuiz = nameField.getText();
        int nrQuestions = Integer.parseInt(nrOfQuestionsField.getText());
        int threshold = Integer.parseInt(thresholdField.getText());

        // Quiz aanmaken met ingevulde informatie en versturen naar database.
        // Toon melding over succes/falen van actie.
        Quiz quiz = new Quiz(nameQuiz, nrQuestions, threshold);
        if (nameQuiz.isEmpty() || nrQuestions == 0 || threshold == 0) {
            editQuizLabel.setText("Wijzig quiz ----- Quiz niet gewijzigd, vul alle velden in");
        } else {
            qdao.editQuiz(quiz,"newName",1,1);//wat waren ook al weer de nieuwe waarden? BvB
            editQuizLabel.setText("Wijzig quiz ----- Quiz gewijzigd");
            AbstractDAO.db.closeConnection();
        }
    }

    // Terugkeren naar Welcome menu
    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
    }
}
