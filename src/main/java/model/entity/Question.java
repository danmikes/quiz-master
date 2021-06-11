package model.entity;

/**
 * Een vraag hoort bij een quiz / cursus
 * Een vraag heeft een nummer, een vraag en vier antwoorden (A..B)
 * antwoorD is correct
 * <p>
 * author Team Fenix
 */
public class Question {

    // parameters
    private Quiz quiz;
    private int nrQuestion;
    private String textQuestion;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

//    private int idCursus;
//    private int idQuiz;


    // constructors

    public Question(Quiz quiz, int nrQuestion, String textQuestion, String answerA, String answerB, String answerC, String answerD) {
        super();
        this.quiz = quiz;
        this.nrQuestion = nrQuestion;
        this.textQuestion = textQuestion;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public Question(int nrQuestion, String textQuestion, String answerA, String answerB, String answerC, String answerD) {
        this( new Quiz(),nrQuestion, textQuestion, answerA, answerB, answerC, answerD);
    }

    public Question(String textQuestion, String answerA, String answerB, String answerC, String answerD) {
        this( 0, textQuestion, answerA, answerB, answerC, answerD);
    }

    public Question() {
        this( 0, "", "", "", "", "");
    }

    // getters & setters

    public int getNrQuestion() {
        return nrQuestion;
    }

    public void setNrQuestion(int nrQuestion) {
        this.nrQuestion = nrQuestion;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    @Override
    public String toString() {
        return "Question{" +
                ", quiz=" + quiz.toString() +
                "nrQuestion=" + nrQuestion +
                '}';
    }


}