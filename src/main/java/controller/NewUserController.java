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
import java.util.ArrayList;

/**
 * Doel:
 * maak nieuwe gebruiker aan
 *
 * author: EvdP - Team Fenix
 */

public class NewUserController {

  private final static String ROLE1 = "Student";
  private final static String ROLE2 = "Docent";
  private final static String ROLE3 = "Coordinator";
  private final static String ROLE4 = "Onderwijsadministratie";
  private final static String ROLE5 = "Technisch Beheerder";

  public String role;
  private MaintainerDAO mdao = new MaintainerDAO();

  @FXML
  private TextField nameField;
  @FXML
  private TextField passwordField;
  @FXML
  public MenuButton roleMenuButton;
  @FXML
  private Label title;

  public void setup() {
    ArrayList<MenuItem> rollen = new ArrayList<>();
    rollen.add(new MenuItem(ROLE1));
    rollen.add(new MenuItem(ROLE2));
    rollen.add(new MenuItem(ROLE3));
    rollen.add(new MenuItem(ROLE4));
    rollen.add(new MenuItem(ROLE5));
    roleMenuButton.getItems().addAll(rollen);// .addAll(role1,role2,role3,role4,role5);

    for (MenuItem rol : roleMenuButton.getItems()) {
      rol.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          role = rol.getText();
          roleMenuButton.setText(role);
        }
      });
    }
  }

  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doCreateUser(ActionEvent event){
    String name = nameField.getText();
    String password = passwordField.getText();
    String roleSelected = role;

    User user = new User(name, password, roleSelected);
    if (name.isEmpty()){
      title.setText("Maak nieuwe gebruiker.     Naam niet correct ingevuld. Gebruiker niet aangemaakt. Vul alle velden in");
    } else if (password.isEmpty()){
      title.setText("Maak nieuwe gebruiker.     Wachtwoord niet correct ingevuld. Gebruiker niet aangemaakt. Vul alle velden in");
    } else if (role == null) {
      title.setText("Maak nieuwe gebruiker.     Rol niet correct ingevuld. Gebruiker niet aangemaakt. Vul alle velden in");
    } else {
      if (mdao.addNewUser(user)){
        SceneManager.getSceneManager().showNewUserScene();
      } else {
        title.setText("Maak nieuwe gebruiker.     Gebruiker niet aangemaakt. Vul alle velden in");
      }

    }
  }
}
