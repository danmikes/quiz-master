package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.dao.MaintainerDAO;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

/**
 * Doel:
 * verander huidige gegevens van gebruiker
 *
 * author: EvdP - team Fenix
 */

public class ChangeUserController {
  private final static String ROLE1 = "Student";
  private final static String ROLE2 = "Docent";
  private final static String ROLE3 = "Coordinator";
  private final static String ROLE4 = "Onderwijsadministratie";
  private final static String ROLE5 = "Technisch Beheerder";
  private MaintainerDAO mdao = new MaintainerDAO();
  public String role;
  private String currentName;
  private String currentPassword;
  private String currentRole;

  @FXML
  private Label title;
  @FXML
  private TextField nameField;
  @FXML
  private TextField passwordField;
  @FXML
  private MenuButton roleMenuButton;

  public void setup(User user) {
    title.setText("Wijzig gebruiker: " + user.getName() );

    nameField.setText(user.getName());
    passwordField.setText(user.getPassword());
    roleMenuButton.setText(user.getRole());

    currentName = nameField.getText();
    currentPassword = passwordField.getText();
    currentRole = roleMenuButton.getText();

    MenuItem role1 = new MenuItem(ROLE1);
    MenuItem role2 = new MenuItem(ROLE2);
    MenuItem role3 = new MenuItem(ROLE3);
    MenuItem role4 = new MenuItem(ROLE4);
    MenuItem role5 = new MenuItem(ROLE5);
    roleMenuButton.getItems().addAll(role1,role2,role3,role4,role5);

    // als er geen nieuwe keuze wordt gemaakt voor rol,blijft huidige rol staan
      role = currentRole;

      for (MenuItem item : roleMenuButton.getItems()) {
      item.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          role = item.getText();
          roleMenuButton.setText(role);
        }
      });
    }
  }

  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doChangeUser(ActionEvent event){
    String newName = nameField.getText();
    String newPassword = passwordField.getText();
    String newRole = role;

    mdao.updateUserByName(currentName,newName,newPassword,newRole);
    SceneManager.getSceneManager().showSelectUserScene();

  }
}
