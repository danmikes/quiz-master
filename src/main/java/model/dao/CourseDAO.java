package model.dao;

import model.entity.Course;
import model.entity.Quiz;
import model.entity.user.Coordinator;
import model.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends AbstractDAO {

    private Course course;

    //Constructor
    public CourseDAO() {
        super(db);
    }

    // DAO of parent object
    private CoordinatorDAO coDAO = new CoordinatorDAO();

    // methode om nieuwe Cursus toe te voegen aan tabel Cursus
    public boolean addNewCourse(Course course) {
        String sql = "Insert into Cursus(naamCursus, datumCursus, idCoordinator) " +
                "select ?,?, idGebruiker idCoordinator from Gebruiker " +
                "where naamGebruiker = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, course.getNameCourse());
            ps.setString(2, course.getDateCourse());
            ps.setString(3, course.getCoordinator().getName());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error CourseDAO addNewCourse: " + e.getMessage());
            return false;
        }
        return true;
    }


    public Course selectCourseByName(String selectedName) {
        String sql = "Select naamCursus, datumCursus, g.naamGebruiker naamCoordinator " +
                "from Cursus c join Gebruiker g on c.idCoordinator = g.idGebruiker " +
                "where naamCursus = ?;";
        Course result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, selectedName);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String nameCourse = rs.getString("naamCursus");
                String dateCourse = rs.getString("datumCursus");
                String nameCoordinator = rs.getString("naamCoordinator");
                Coordinator coordinator = coDAO.findCoordinatorByName(nameCoordinator);
                result = new Course(nameCourse, dateCourse, coordinator);
            }
        } catch (SQLException e) {
            System.out.println("SQL error CourseDAO selectCourseByName: " + e.getMessage());
        }
        return result;
    }

    public boolean deleteCourseByName(String name) {
        String sql = "Delete from Cursus where naamCursus = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error CourseDAO deleteCourseByName: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateCourseByName(String currentNameCourse, String nameCourse, String dateCourse, String nameCoordinator) {
        String sql = "Update Cursus " +
                "SET naamCursus = ?, datumCursus = ?, idCoordinator = (select idGebruiker from Gebruiker where naamGebruiker = ?) " +
                "WHERE naamCursus = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, nameCourse);
            ps.setString(2, dateCourse);
            ps.setString(3, nameCoordinator);
            ps.setString(4, currentNameCourse);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error CourseDAO updateCourseByName: " + e.getMessage());
            return false;
        }

        return true;

    }

    //Toon alle courses
    public List<Course> getCourses() {
        String sql = "Select naamCursus, datumCursus, naamGebruiker naamCoordinator " +
                "from Cursus c join Gebruiker g on c.idCoordinator = g.idGebruiker;";
        List<Course> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String nameCourse = rs.getString("naamCursus");
                String dateCourse = rs.getString("datumCursus");
                String nameCoordinator = rs.getString("naamCoordinator");
                Coordinator coordinator = coDAO.findCoordinatorByName(nameCoordinator);
                Course course = new Course(nameCourse, dateCourse, coordinator);
                result.add(course);
            }
        } catch (SQLException e) {
            System.out.println("SQL error QuizDAO.getCourses select" + e.getMessage());
        }
        return result;
    }

    // Haal course id op basis van naam
    public int getIdByName(String name) {
        String sql = "Select idCursus from Cursus where naamCursus = ?;";
        int result = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                result = rs.getInt("idCursus");
            }
        } catch (SQLException e) {
            System.out.println("SQL error CourseDAO.getIdByName select" + e.getMessage());
        }
        return result;
    }
}

