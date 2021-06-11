package model.dao;


import model.entity.user.Coordinator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

    public class CoordinatorDAO extends AbstractDAO {

        private static String ROLE = "Coordinator";

        public CoordinatorDAO() {
            super(db);
        }

        public Coordinator findCoordinatorByName (String nameCoordinator) {
            String sql = "Select naamGebruiker, Wachtwoord from Gebruiker where naamGebruiker = ? and Rol = ?;";
            Coordinator result = null;
            try {
                PreparedStatement ps = getStatement(sql);
                ps.setString(1,nameCoordinator);
                ps.setString(2,ROLE);
                ResultSet rs = executeSelectPreparedStatement(ps);
                while (rs.next()){
//                    String name = rs.getString("naamGebruiker");
                    String password = rs.getString("Wachtwoord");
//                    String role = rs.getString("Rol");
                    result = new Coordinator (nameCoordinator,password, ROLE);
                }
            } catch (SQLException e) {
                System.out.println("SQL error method findCoordinatorByName: " + e.getMessage());
            }
            return result;
        }

        // get a list of all Coordinators
        public ArrayList<Coordinator> getAllCoordinators() {
            String sql = "Select naamGebruiker, wachtwoord from Gebruiker where Rol = ?;";
            ArrayList<Coordinator> result = new ArrayList<>();
            try {
                PreparedStatement ps = getStatement(sql);
                ps.setString(1,ROLE);
                ResultSet rs = executeSelectPreparedStatement(ps);
                while (rs.next()){
                    String name = rs.getString("naamGebruiker");
                    String password = rs.getString("Wachtwoord");
//                String role = rs.getString("Rol");
                    result.add(new Coordinator(name,password, ROLE));
                }
            } catch (SQLException e) {
                System.out.println("SQL error method getAllCoordinators: " + e.getMessage());
            }
            return result;
        }

    }

