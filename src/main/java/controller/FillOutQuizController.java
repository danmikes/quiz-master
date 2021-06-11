package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.dao.AnswerDAO;
import model.dao.CouchDBAccess;
import model.dao.QuestionDAO;
import model.entity.Answer;
import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.user.Coordinator;
import view.Main;
import view.SceneManager;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Doel:
 * tonen vragen van gekozen quiz en deze kunnen beantwoorden
 *
 * author: EvdP - team Fenix
 */

public class FillOutQuizController {

  private CouchDBAccess cdb;

  private QuestionDAO qdao = new QuestionDAO();
  private AnswerDAO adao = new AnswerDAO(cdb);

  private int idCursus;
  private int idQuiz;
  private String textQuestion;
  private String answerA;
  private String answerB;
  private String answerC;
  private String answerD;
  private int nrQuestion = 0;

  private int nrOfGoodAnswers;
  private int nrOfWrongAnswers;

  private List<Question> questionsWithAnswers = new ArrayList<>();
  public List<Answer> answers = new ArrayList<>();

  // hulpvariabele (verwijderen zodra gekozen Quiz wordt meegenoemn vanuit SelectQuizForStudentController)
  private int nrQuiz = 1;

  // hulpvariabele: wat wordt meegeven aan StudentFeedback
  private Quiz quizKlaar;

  // hulpvariabele: indien antwoorden in willekeurige volgorde staan, moet worde vastgelegd wat het goede antwoord is
  // nu is altijd Antwoord A het goede antwoord
  private String rightAnswer;


  @FXML
  private Label title;
  @FXML
  private TextArea questionArea;
  @FXML
  private Button answerAButton;
  @FXML
  private Button answerBButton;
  @FXML
  private Button answerCButton;
  @FXML
  private Button answerDButton;
  @FXML
  private Button nextQuestionButton;
  @FXML
  private Button previousQuestionButton;
  @FXML
  private Button menuButton;

  public void setup() {
    // controleren of laatste vraag is geweest, indien ja, resultaten versturen naar CouchDB
    cdb = new CouchDBAccess();
    if (nrQuestion == qdao.selectAllQuestionsByNrQuiz(nrQuiz).size()) {
      SceneManager.getSceneManager().showStudentFeedback(quizKlaar);
      run();

    } else {
      // ophalen info op geselecteerde quiz (in vorig scherm)
//      questionsWithAnswers = qdao.selectAllQuestionsByNrQuiz(nrQuiz); // nrQuiz vastgezet in variabelen, kan nog niet quizSelecteren in vorig scherm
      Coordinator chosenCoordinator = new Coordinator("Corrie", "corrie", "coordinator");
      Course chosenCourse = new Course("Programming", "2019-04-15",chosenCoordinator);
      Quiz chosenQuiz = new Quiz(chosenCourse,1,"Q1",4,2);

      questionsWithAnswers = qdao.viewQuestions(chosenQuiz); // was tijdelijk lege quiz, want gekozen quiz nodig - BvB, handmaitg quiz gekozen om scherm te kunnen tonen (EvdP)

      // verander title (nu "Vraag 1") naar het juiste vraagnummer "nrVraag"
      title.setText(String.format("Vraag: %d",nrQuestion+1));

      // questionArea bevat de vraag en de 4 antwoorden (antwoorden nu nog niet in willekeurige volgorde)
      textQuestion = questionsWithAnswers.get(nrQuestion).getTextQuestion();
      answerA = questionsWithAnswers.get(nrQuestion).getAnswerA();
      answerB = questionsWithAnswers.get(nrQuestion).getAnswerB();
      answerC = questionsWithAnswers.get(nrQuestion).getAnswerC();
      answerD = questionsWithAnswers.get(nrQuestion).getAnswerD();

      // additionele informatie mbt de gestelde vraag --> commented out BvB
//      idCursus = questionsWithAnswers.get(nrQuestion).getIdCursus();
//      idQuiz = questionsWithAnswers.get(nrQuestion).getIdQuiz();

      rightAnswer = answerA; // anders weergeven als wel wordt gehusseld

      questionArea.setText(String.format("%s\n\nA: %s\nB: %s\nC: %s\nD: %s" ,textQuestion,answerA,answerB,answerC,answerD));
    }


  }

  public void registerA() {
    // registreer antwoord in ArrayList
    if (answerA == rightAnswer) {
      nrOfGoodAnswers++;
//      Answer answer = new Answer(idCursus,idQuiz,nrQuestion,answerA,rightAnswer,nrOfGoodAnswers);
      Answer answer = new Answer(new Question(),answerA,rightAnswer,nrOfGoodAnswers);//lege vraag als improvisatie BvB
      answers.add(answer);
    } else{
      nrOfWrongAnswers++;
    }

    // door naar volgende vraag
    nrQuestion ++;
//    setup();
  }

  public void registerB() {
    // registreer antwoord
    if (answerB == rightAnswer) {
      nrOfGoodAnswers++;
//      Answer answer = new Answer(idCursus,idQuiz,nrQuestion,answerA,rightAnswer,nrOfGoodAnswers);
      Answer answer = new Answer(new Question(),answerA,rightAnswer,nrOfGoodAnswers);//lege vraag als improvisatie BvB
      answers.add(answer);
    } else{
      nrOfWrongAnswers++;
    }

    // door naar volgende vraag
    nrQuestion ++;
//    setup();
  }

  public void registerC() {
    // registreer antwoord
    if (answerC == rightAnswer) {
      nrOfGoodAnswers++;
//      Answer answer = new Answer(idCursus,idQuiz,nrQuestion,answerA,rightAnswer,nrOfGoodAnswers);
      Answer answer = new Answer(new Question(),answerA,rightAnswer,nrOfGoodAnswers);//lege vraag als improvisatie BvB
      answers.add(answer);
    } else{
      nrOfWrongAnswers++;
    }

    // door naar volgende vraag
    nrQuestion ++;
//    setup();
  }

  public void registerD() {
    // registreer antwoord
    if (answerD == rightAnswer) {
      nrOfGoodAnswers++;
//      Answer answer = new Answer(idCursus,idQuiz,nrQuestion,answerA,rightAnswer,nrOfGoodAnswers);
      Answer answer = new Answer(new Question(),answerA,rightAnswer,nrOfGoodAnswers);//lege vraag als improvisatie BvB
      answers.add(answer);
    } else{
      nrOfWrongAnswers++;
    }

    // door naar volgende vraag
    nrQuestion ++;
//    setup();
  }

  public void doNextQuestion() {
    // als laatste vraag, door naar studentFeedBackController
    if (nrQuestion == qdao.selectAllQuestionsByNrQuiz(nrQuiz).size()) {
      SceneManager.getSceneManager().showStudentFeedback(quizKlaar);
    } else {
      nrQuestion ++;
//      setup();
    }
  }

  public void doPreviousQuestion() {
    // toon vorige vraag zonder antwoord te registreren
    nrQuestion --;
//    setup();
  }

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void run() {
    try {
      cdb.setupConnection();
      System.out.println("Connection open");
/*
      for (Answer answer : answers) {
        adao.saveAnswer(answer);
      }
*/

    } catch (Exception e) {
      System.out.println("\nEr is iets fout gegaan\n");
      e.printStackTrace();
    }
  }
}
