package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import model.dao.QuestionDAO;
import model.dao.QuizDAO;

import model.dao.CourseDAO;
import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.user.Coordinator;
import view.Main;
import view.SceneManager;

import java.util.List;

/**
 * Lijst van cursussen: maak cursus, wijzig cursus
 * Lijst van quizen: maak quiz, wijzig quiz
 * Lijst van vragen: maak vraag, wijzig vraag
 * <p>
 * author: Team Fenix
 */
public class CoordinatorDashboardController {

    private CourseDAO cdao;
    private QuizDAO qdao;
    private QuestionDAO vdao;
    private String selectedCourse;
    private String selectedQuiz;
    private String selectedQuestion;
    private Course activeCourse;
    private Quiz activeQuiz;
    private Question activeQuestion;

    public Course getActiveCourse() {
        return activeCourse;
    }

    public void setActiveCourse(Course activeCourse) {
        this.activeCourse = activeCourse;
    }

    public Quiz getActiveQuiz() {
        return activeQuiz;
    }

    public void setActiveQuiz(Quiz activeQuiz) {
        this.activeQuiz = activeQuiz;
    }

    public Question getActiveQuestion() {
        return activeQuestion;
    }

    public void setActiveQuestion(Question activeQuestion) {
        this.activeQuestion = activeQuestion;
    }

    // views
    @FXML
    private Button menuButton;
    @FXML
    private Button newCourseButton;
    @FXML
    private Button editCourseButton;
    @FXML
    private Button newQuizButton;
    @FXML
    private Button editQuizButton;
    @FXML
    private Button newQuestionButton;
    @FXML
    private Button editQuestionButton;
    @FXML
    private ListView<String> courseList = new ListView<>();
    @FXML
    private ListView<String> quizList = new ListView<>();
    @FXML
    private ListView<String> groupList = new ListView<>();
    @FXML
    private ListView<String> questionList = new ListView<>();

    public void setup() {
        CourseDAO cdao = new CourseDAO();
        List<Course> courses = cdao.getCourses();
        ObservableList<String> nameCourses = FXCollections.observableArrayList();
        for (Course course : courses) {
            String nameCourse = course.getNameCourse();
            nameCourses.add(nameCourse);
        }
        courseList.setItems(nameCourses);

        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String courseName) {
                for (Course course : courses) {
                    if (course.getNameCourse().equals(courseName)) {
                        getQuizesByCourse(course);
                        activeCourse = course;
                    }
                }
            }
        });
    }

    public void getQuizesByCourse(Course course) {
        QuizDAO qdao = new QuizDAO();
        List<Quiz> quizes = qdao.showQuizesByCourse(course);
        ObservableList<String> nameQuizes = FXCollections.observableArrayList();
        for (Quiz quiz : quizes) {
            String nameQuiz = quiz.getNameQuiz();
            nameQuizes.add(nameQuiz);
        }
        quizList.setItems(nameQuizes);

        quizList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String quizName) {
                for (Quiz quiz : quizes) {
                    if (quiz.getNameQuiz().equals(quizName)) {
                        getQuestionsByQuiz(quiz);
                        activeQuiz = quiz;
                    }
                }
            }
        });
    }

    public void getQuestionsByQuiz(Quiz quiz) {
        QuestionDAO qdao = new QuestionDAO();
//        Course course = cdao.selectCourseByName(selectedCourse);
        List<Question> questions = qdao.selectQuestionsByQuiz(quiz);
        ObservableList<String> nameQuestions = FXCollections.observableArrayList();
        for (Question question : questions) {
            String nameQuestion = question.getTextQuestion();
            nameQuestions.add(nameQuestion);
        }
        questionList.setItems(nameQuestions);

        questionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String questionName) {
                for (Question question : questions) {
                    if (question.getTextQuestion().equals(questionName)) {
                        getActiveQuestion();
                        activeQuiz = quiz;
                        activeQuestion = question;
                    }
                }
            }
        });
    }

    public void doNewCourse() {
        SceneManager.getSceneManager().showNewCourseScene();
    }

    public void doEditCourse() {
        SceneManager.getSceneManager().showManageCoursesScene();
    }

    public void doNewQuiz() {
        SceneManager.getSceneManager().showNewQuizScene(activeCourse);
    }

    public void doEditQuiz() { // hoe
        SceneManager.getSceneManager().showEditQuizScene(activeCourse, activeQuiz);
    }

//    public void doEditQuestion() {
//        SceneManager.getSceneManager().showEditQuestionScene(activeQuiz);
//    }

    public void doNewQuestion() {
        SceneManager.getSceneManager().showNewQuestionScene(activeCourse, activeQuiz);
    }

    public void doEditQuestion() {
//        SceneManager.getSceneManager().showEditQuestion(activeCourse, activeQuiz, activeQuestion);
//        activeQuiz = qdao.getQuizByNameQuiz(selectedQuiz);
//        activeQuestion = vdao.selectQuestionByName(selectedQuestion);
    }

    // Terugkeren naar Welcome menu
    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
    }
}