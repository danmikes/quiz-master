package model.entity;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Answer {
//    private int idCursus;
//    private int idQuiz;
//    private int nrVraag;
    private Question question;
    private String givenAnswer;
    private String rightAnswer;
    private int nrOfAnswersRight;

    public Answer() {
        this(new Question(), "", "", 0);
    }

    public Answer(Question question, String givenAnswer, String rightAnswer, int nrOfAnswersRight) {
        super();
        this.question = question;
        this.givenAnswer = givenAnswer;
        this.rightAnswer = rightAnswer;
        this.nrOfAnswersRight = nrOfAnswersRight;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getNrOfAnswersRight() {
        return nrOfAnswersRight;
    }

    public void setNrOfAnswersRight(int nrOfAnswersRight) {
        this.nrOfAnswersRight = nrOfAnswersRight;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "question=" + question.toString() +
                ", givenAnswer='" + givenAnswer + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", nrOfAnswersRight=" + nrOfAnswersRight +
                '}';
    }

}
