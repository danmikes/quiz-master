package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.dao.UserDAO;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

public class LoginController {


    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordField;

    public void doLogin(ActionEvent event) {
        String name = nameTextField.getText();
        String password = passwordField.getText();
        UserDAO dao = new UserDAO();
        User currentUser = dao.findUserByName(name);
        if (currentUser != null) {
            if (currentUser.getPassword().equals(password)) {
                System.out.println("JA. gebruiker komt voor en heeft het goede password.");
                Main.setCurrentUser(currentUser);
                SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());

            } else {
                System.out.println("NEE, gebruiker komt wel voor, maar met een ander password.");
                SceneManager.getSceneManager().showLoginFailedScene();
            }
        } else {
            System.out.println("HELAAS, gebruiker komt niet voor.");
            SceneManager.getSceneManager().showLoginFailedScene();

        }
    }

    public void doQuit(ActionEvent event) {
        System.exit(0);
    }
}
