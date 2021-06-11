package model.dao;

import model.entity.Course;
import model.entity.user.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseEnrollmentDAO extends AbstractDAO {

    // constructor(s)
    public CourseEnrollmentDAO() {
        super(db);
    }

    // methode om nieuwe combinatie (dus vakkeuze) toe te voegen aan tabel Cursus_Inschrijving


    public boolean addNewCourseEnrollment(String coursename) {
        String sql = "Insert into Cursus_Inschrijving(idStudent, idCursus) values (?,?);";
        UserDAO udao = new UserDAO();
        CourseDAO cdao = new CourseDAO();

        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, udao.getIdUserByName(Main.getCurrentUser().getName()));
            ps.setInt(2, cdao.getIdByName(coursename));
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error CourseEnrollmentDAO addNewCourseEnrollment: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteCourseEnrollment(String coursename) {
        String sql = "Delete from Cursus_Inschrijving where idStudent = ? and idCursus = ?;";
        UserDAO udao = new UserDAO();
        CourseDAO cdao = new CourseDAO();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, udao.getIdUserByName(Main.getCurrentUser().getName()));
            ps.setInt(2, cdao.getIdByName(coursename));
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error CourseEnrollmentDAO deleteCourseEnrollment: " + e.getMessage());
            return false;
        }
        return true;
    }
}
