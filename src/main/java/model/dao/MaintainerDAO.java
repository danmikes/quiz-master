package model.dao;

/**
 * author: EvdP - Team Fenix
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.user.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MaintainerDAO extends AbstractDAO {

    // constructor(s)
    public MaintainerDAO() {
        super(db);
    }

    // methode om nieuwe User toe te voegen aan tabel Gebruiker
    public boolean addNewUser (User user) {
        String sql = "Insert into Gebruiker(naamGebruiker, wachtwoord, rol) values (?,?,?);";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3,user.getRole());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error MaintainerDAO addNewUser: " + e.getMessage());
            return false;
        }
        return true;
    }

    // methode om naam op te vragen van alle Users
    public List<User> selectAllUsers () {
        String sql = "Select naamGebruiker, wachtwoord, rol from Gebruiker;";
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("naamGebruiker");
                String password = rs.getString("wachtwoord");
                String role = rs.getString("rol");
                User user = new User(name, password, role);
                result.add(user);
            }
        } catch (SQLException e){
            System.out.println("SQL error MaintainerDAO selectAllUsers: "+ e.getMessage());
        }
        return result;
    }

    public User selectUserByName (String selectedName) {
        String sql = "Select naamGebruiker, wachtwoord, rol from Gebruiker where naamGebruiker = ?;";
        User result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1,selectedName);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("naamGebruiker");
                String password = rs.getString("wachtwoord");
                String role = rs.getString("rol");
                result = new User(name, password, role);
            }
        } catch (SQLException e){
            System.out.println("SQL error MaintainerDAO selectUserByName: " + e.getMessage());
        }
        return result;
    }

    public boolean deleteUserByName (String name){
        String sql = "Delete from Gebruiker where naamGebruiker = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1,name);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e){
            System.out.println("SQL error MaintainerDAO deleteUserByName: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void updateUserByName (String currentName,String name,String password,String role) {
        String sql = "Update Gebruiker SET naamGebruiker = ?, wachtwoord = ?, rol = ? WHERE naamGebruiker = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ps.setString(2,password);
            ps.setString(3,role);
            ps.setString(4,currentName);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error MaintainerDAO updateUserByName: " + e.getMessage());
        }

    }
}
