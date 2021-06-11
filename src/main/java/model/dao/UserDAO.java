package model.dao;

import model.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {

    public UserDAO() {
        super(db);
    }

    public User findUserByName (String nameUser) {
        String sql = "Select naamGebruiker, Wachtwoord, Rol from Gebruiker where naamGebruiker = ?;";
        User result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1,nameUser);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()){
                String name = rs.getString("naamGebruiker");
                String password = rs.getString("Wachtwoord");
                String role = rs.getString("Rol");
                result = new User (name,password, role);
            }
        } catch (SQLException e) {
            System.out.println("SQL error method findUserByName: " + e.getMessage());
        }
        return result;
    }

// added to enable to get a list of a specific role, f.e. Coordinator, Teacher
    public List<User> findUserByRole(String nameRole) {
        String sql = "Select naamGebruiker, wachtwoord, Rol from Gebruiker where Rol = ?;";
        ArrayList<User> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1,nameRole);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()){
                String name = rs.getString("naamGebruiker");
                String password = rs.getString("Wachtwoord");
                String role = rs.getString("Rol");
                result.add(new User (name,password, role));
            }
        } catch (SQLException e) {
            System.out.println("SQL error method findUserByName: " + e.getMessage());
        }
        return result;
    }

    /// Haal userid op op basis van name user
    public int getIdUserByName(String name) {
        String sql = "Select idGebruiker from Gebruiker where naamGebruiker = ?;";
        int result = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                result = rs.getInt("idGebruiker");
            }
        } catch (SQLException e) {
            System.out.println("SQL error UserDAO.getIdByName select" + e.getMessage());
        }
        return result;
    }
}
