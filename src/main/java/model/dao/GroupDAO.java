package model.dao;

import model.entity.Course;
import model.entity.Group;
import model.entity.Quiz;
import model.entity.user.Coordinator;
import model.entity.user.Student;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends AbstractDAO {


    //Variabelen
    private Group group;
    private Course course;
    private Coordinator coordinator;
    private ArrayList<Student> students = new ArrayList();

    //Connectie Database
    public GroupDAO() {
        super(db);
    }

    //Toon alle Groepen

    public List<Group> getGroups() {
        String sql = "Select * from Groep;";
        List<Group> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String nameGroup = rs.getString("naamGroep");
                int nrGroup = rs.getInt("idGroep");
                Group group = new Group(nameGroup,course, coordinator, students);

                result.add(group);
            }
        } catch (SQLException e) {
            System.out.println("SQL error GroepDAO.getGroups select" + e.getMessage());
        }
        return result;

    }

}
