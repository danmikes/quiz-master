package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.dao.CoordinatorDAO;
import model.dao.CourseDAO;
import model.dao.UserDAO;
import model.entity.Course;
import model.entity.user.Coordinator;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class NewCourseController {

  // variabelen
  private final static String ROLE3 = "Coordinator"
  //uit User Stories: 'Ook bepaalt hij [Onderwijsadministratie] wie er coördinator is van die cursus.'
  , COURSEDATE = "2019-04-15";//start date of MIW C16 als dummy date

  public static String role = ROLE3
          , nameSelectedCoordinator;
  CourseDAO cdao = new CourseDAO();
  private static CoordinatorDAO codao = new CoordinatorDAO();
  //lijst van alle coordinatoren
  private static List<Coordinator> coordinatoren = codao.getAllCoordinators();

  // view velden
  @FXML
  private TextField nameField;

  @FXML
  public MenuButton coordinatorMenuButton;

  @FXML
  private Label title;

  // methoden

  public void setup() {
    // keuze opties in Coordinator drop down list
    ArrayList<MenuItem> coordinatorenMI = new ArrayList<>();
    // toevoegen opties aan dropdown-menu
    for ( Coordinator coordinator : coordinatoren) {
      coordinatorenMI.add(new MenuItem(coordinator.getName()));
    }
    coordinatorMenuButton.getItems().addAll(coordinatorenMI);
    // loop door menuItems en selecteer coordinator
    for (MenuItem coordinator : coordinatorMenuButton.getItems()) {
      coordinator.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          nameSelectedCoordinator = coordinator.getText();
          coordinatorMenuButton.setText(nameSelectedCoordinator);
        }
      });
    }
  }

  // Terugkeren naar Welcome menu
  public void doMenu(ActionEvent event) {
    SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
  }

  public void doNewCourse(ActionEvent event){
    // wat is ingevuld door administrator?
    String name = nameField.getText();
    // van welke coordinator (user) is de naam geselecteerd? Indien 'gehit' (naam = unique identier) maak nieuwe cursus
    for ( Coordinator coordinator : coordinatoren) {
      if (coordinator.getName().equals(nameSelectedCoordinator)) {

        if (name.isEmpty()){
          title.setText("Maak nieuwe cursus.     Cursusnaam niet ingevuld. Cursus niet aangemaakt. Vul alle velden in");
        }
        else if (coordinator == null) {
          title.setText("Maak nieuwe cursus.     Coordinator niet ingevuld. Cursus niet aangemaakt. Vul alle velden in");
        }
        else {
          // Cursus aanmaken met ingevulde informatie en versturen naar database. Toont melding over (niet) geslaagde actie
          Course course = new Course(name, COURSEDATE, coordinator);
          if (cdao.addNewCourse(course)){
            SceneManager.getSceneManager().showNewCourseScene();
          }
          else {
            title.setText("Maak nieuwe cursus.     Cursus niet aangemaakt wegens onverwachte fout.");
          }
        }
      }

    }


    // toegevoegde gebruiker zit nu in lokale database
    // als anderen dit ook willen doen, moet er een insertScript worden gegenereerd en deze delen via GIT

  }

}
