package model.dao;

import model.dao.AbstractDAO;
import model.entity.Course;
import model.entity.Quiz;
import model.entity.user.Coordinator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Voegt nieuwe quiz toe
 * Wijzigt bestaande quiz
 *
 * author Team Fenix
 */
public class QuizDAO extends AbstractDAO {

    // constructor(s)
    public QuizDAO() {
        super(db);
    }

    // toon alle quizes --> not used
    public List<Quiz> showQuizes() {
        String sql = "Select naamQuiz, aantalVragen, cesuur from Quiz;";
        List<Quiz> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String nameQuiz = rs.getString("naamQuiz");
                int nrQuestions = rs.getInt("aantalVragen");
                int threshold = rs.getInt("cesuur");
                Quiz quiz = new Quiz(nameQuiz, nrQuestions, threshold);
                result.add(quiz);
                System.out.println(quiz); // test
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.showQuizes select" + e.getMessage());
        }
        return result;
    }

    // selecteer quizzes per cursus
    public List<Quiz> showQuizesByCourse(Course course) {
        String sql = "Select nrQuiz, naamQuiz, aantalVragen, cesuur from Quiz " +
                "where idCursus = (select idCursus from Cursus where naamCursus = ?);";
//        String sql = "Select nrQuiz, naamQuiz, aantalVragen, cesuur from Quiz q join Cursus c on q.idCursus = c.idCursus where c.naamCursus = ?;";
        List<Quiz> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            System.out.println(course.getNameCourse());
            ps.setString(1,course.getNameCourse()); // test
//            ps.setString(1, selectedCourse);// gebruiken zodra selectedCourse toegekend aan ?
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int nrQuiz = rs.getInt("nrQuiz");
                String nameQuiz = rs.getString("naamQuiz");
                int nrQuestions = rs.getInt("aantalVragen");
                int threshold = rs.getInt("cesuur");
                Quiz quiz = new Quiz(course, nrQuiz, nameQuiz, nrQuestions, threshold);
                result.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO showQuizesByCourse: " + e.getMessage());
        }
        return result;
    }

    // bewerk quiz toe
    public void editQuiz(Quiz activeQuiz, String newName, int newNrQuestions, int newThreshold) {
        String sql = "Update ignore Quiz set (naamQuiz = ?, aantalVragen = ?, cesuur = ?)" +
                "where naamQuiz = ? and idCursus = (select idCursus from Cursus where naamCursus = ?);";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, newName);
            ps.setInt(2, newNrQuestions);
            ps.setInt(3, newThreshold);
            ps.setString(4, activeQuiz.getNameQuiz());
            ps.setString(5, activeQuiz.getCourse().getNameCourse());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.editQuiz: " + e.getMessage());
        }
    }

    // voeg quiz toe
    public void newQuiz(Quiz quiz) {
        String sql = "Insert ignore into Quiz(idCursus, nrQuiz, naamQuiz, aantalVragen, cesuur)" +
                "select idCursus, ?, ?, ?,? from Cursus where naamCursus = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, quiz.getNrQuiz());
            ps.setString(2, quiz.getNameQuiz());
            ps.setInt(3, quiz.getNrQuestions());
            ps.setInt(4, quiz.getThreshold());
            ps.setString(5, quiz.getCourse().getNameCourse());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.newQuiz: " + e.getMessage());
        }
    }

    public Quiz getQuizByNameQuiz(String selectedQuiz) {
        String sql = "Select nrQuiz, naamQuiz, aantalVragen, cesuur from Quiz where naamQuiz = ?;";
        Quiz result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, selectedQuiz);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int nrQuiz = rs.getInt("nrQuiz");
                String nameQuiz = rs.getString("naamQuiz");
                int nrQuestions = rs.getInt("aantalVragen");
                int threshold = rs.getInt("cesuur");
                result = new Quiz(nrQuiz, nameQuiz, nrQuestions, threshold);
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO getQuizByNameQuiz: " + e.getMessage());
        }
        return result;
    }

    // Haal quiz id op basis van naam
    public int getIdByQuizName(String nameQuiz) {
        String sql = "Select nrQuiz from Quiz where naamQuiz = ?;";
        int result = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, nameQuiz);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                result = rs.getInt("nrQuiz");
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.getIdByNameQuiz select" + e.getMessage());
        }
        return result;
    }

    // Haal quiz op basis van quiz nr
    public Quiz getQuizByNrQuiz(int nrQuiz) {
        String sql = "Select naamQuiz from Quiz where nrQuiz = ?;";
        Quiz result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, nrQuiz);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String nameQuiz = rs.getString("naamQuiz");
                result = getQuizByNameQuiz(nameQuiz);
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.getIdByNameQuiz select" + e.getMessage());
        }
        return result;
    }

}
