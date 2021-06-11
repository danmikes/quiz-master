package model.entity;

/**
 * Een quiz heeft een naam en vragen
 * Een quiz hoort bij een cursus
 *
 * author Daniel Mikes - Team Fenix
 */
public class Quiz {
    private Course course;
    private int nrQuiz;
    private String nameQuiz;
    private int nrQuestions;
    private int threshold;

    public Quiz(Course course, int nrQuiz, String nameQuiz, int nrQuestions, int threshold){
        super();
        this.course = course;
        this.nrQuiz = nrQuiz;
        this.nameQuiz = nameQuiz;
        this.nrQuestions = nrQuestions;
        this.threshold = threshold;
    }

    public Quiz(int nrQuiz, String nameQuiz, int nrQuestions, int threshold) {
        this(new Course(), nrQuiz, nameQuiz, nrQuestions, threshold);
    }

    public Quiz(String nameQuiz, int nrQuestions, int threshold) {
        this(0, nameQuiz, nrQuestions, threshold);
    }

    public Quiz(){
        this("Q0", 0, 0);
    }

    public String getNameQuiz() {
        return nameQuiz;
    }
    public void setNameQuiz(String nameQuiz) {
        this.nameQuiz = nameQuiz;
    }
    public int getNrQuiz() { return nrQuiz; }
    public void setNrQuiz(int nrQuiz) {
        this.nrQuiz = nrQuiz;
    }
    public int getNrQuestions() {
        return nrQuestions;
    }
    public void setNrQuestions(int nrQuestions) {
        this.nrQuestions = nrQuestions;
    }
    public int getThreshold() {
        return threshold;
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString(){
        return String.format("%s - %s - %d - %d", course, nameQuiz, nrQuestions, threshold);
    }
}