package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.entity.Course;
import model.entity.Group;
import model.entity.Quiz;
import model.entity.user.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;

/**
 * author: Jolanthe Machgeels - Team Fenix
 */

public class WelcomeController {

    public String task;

    private final static String ROLE1 = "Student";
    private final static String ROLE2 = "Docent";
    private final static String ROLE3 = "Coordinator";
    private final static String ROLE4 = "Onderwijsadministratie";
    private final static String ROLE5 = "Technisch Beheerder";

    private final static String TASKSTUDENT1 = "In en Uitschrijven";
    private final static String TASKSTUDENT2 = "Quiz selecteren";
    private final static String TASKSTUDENT3 = "Quiz invullen";
    private final static String TASKMAINTAINER1 = "Gebruiker aanmaken";
    private final static String TASKMAINTAINER2 = "Gebruiker wijzigen/verwijderen";
    private final static String TASKADMINISTRATOR1 = "Manage cursus";
    private final static String TASKADMINISTRATOR2 = "Nieuwe cursus";
    private final static String TASKADMINISTRATOR3 = "Manage groep";
    private final static String TASKADMINISTRATOR4 = "Nieuwe groep";
    private final static String TASKCOORDINATOR1 = "Dashboard";
    private final static String TASKCOORDINATOR2 = "Quizen maken en bewerken";
    private final static String TASKCOORDINATOR3 = "Vragen maken en bewerken";


    @FXML
    private Label welcomeLabel;

    @FXML
    private MenuButton taskMenuButton;

    @FXML
    private TextField nameField;

    public void setup(User user) {

        welcomeLabel.setText("Welkom " + Main.getCurrentUser().getName() + ", u bent ingelogd met rol " + Main.getCurrentUser().getRole());

        if (Main.getCurrentUser().getRole().equals(ROLE1)) {
            MenuItem taskStudent1 = new MenuItem(TASKSTUDENT1);
            MenuItem taskStudent2 = new MenuItem(TASKSTUDENT2);
            MenuItem taskStudent3 = new MenuItem(TASKSTUDENT3);
            taskMenuButton.getItems().addAll(taskStudent1, taskStudent2, taskStudent3);

            for (MenuItem task : taskMenuButton.getItems()) {
                task.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (task.getText().equals(TASKSTUDENT1)) {
                            taskMenuButton.setText(TASKSTUDENT1);
                            SceneManager.getSceneManager().showStudentSignInOutScene();
                        } else if (task.getText().equals(TASKSTUDENT2)) {
                            taskMenuButton.setText(TASKSTUDENT2);
                            SceneManager.getSceneManager().showSelectQuizForStudent();
                        } else if (task.getText().equals(TASKSTUDENT3)) {
                            taskMenuButton.setText(TASKSTUDENT3);
                            SceneManager.getSceneManager().showFillOutQuiz();
                        }

                    }
                });
            }

        }

        if (Main.getCurrentUser().getRole().equals(ROLE5)) {
            MenuItem taskMaintainer1 = new MenuItem(TASKMAINTAINER1);
            MenuItem taskMaintainer2 = new MenuItem(TASKMAINTAINER2);
            taskMenuButton.getItems().addAll(taskMaintainer1, taskMaintainer2);

            for (MenuItem task : taskMenuButton.getItems()) {
                task.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (task.getText().equals(TASKMAINTAINER1)) {
                            taskMenuButton.setText(TASKMAINTAINER1);
                            SceneManager.getSceneManager().showNewUserScene();
                        } else if (task.getText().equals(TASKMAINTAINER2)) {
                            taskMenuButton.setText(TASKMAINTAINER2);
                            SceneManager.getSceneManager().showSelectUserScene();

                            //Main.getCurrentUser() --> is het de bedoeling dat de huidige gebruiker wordt meegenomen?
                            // Zie aantekeningen Elleke in ChangeUserController.
                        }

                    }
                });
            }

        }

        if (Main.getCurrentUser().getRole().equals(ROLE4)) {
            MenuItem taskAdministrator1 = new MenuItem(TASKADMINISTRATOR1);
            MenuItem taskAdministrator2 = new MenuItem(TASKADMINISTRATOR2);
            MenuItem taskAdministrator3 = new MenuItem(TASKADMINISTRATOR3);
            MenuItem taskAdministrator4 = new MenuItem(TASKADMINISTRATOR4);
            taskMenuButton.getItems().addAll(taskAdministrator1, taskAdministrator2, taskAdministrator3, taskAdministrator4);

            for (MenuItem task : taskMenuButton.getItems()) {

                task.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (task.getText().equals(TASKADMINISTRATOR1)) {
                            taskMenuButton.setText(TASKADMINISTRATOR1);
                            SceneManager.getSceneManager().showManageCoursesScene();
                        } else if (task.getText().equals(TASKADMINISTRATOR2)) {
                            taskMenuButton.setText(TASKADMINISTRATOR2);
                            SceneManager.getSceneManager().showNewCourseScene();
                        } else if (task.getText().equals(TASKADMINISTRATOR3)) {
                            taskMenuButton.setText(TASKADMINISTRATOR3);
                            SceneManager.getSceneManager().showManageGroupsScene();
                        } else if (task.getText().equals(TASKADMINISTRATOR4)) {
                            taskMenuButton.setText(TASKADMINISTRATOR4);
                            SceneManager.getSceneManager().showNewGroupScene();
                        }

                    }
                });
            }

        }

        if (Main.getCurrentUser().getRole().equals(ROLE3)) {
            ArrayList<MenuItem> tasks = new ArrayList<>();
            tasks.add(new MenuItem(TASKCOORDINATOR1));
            tasks.add(new MenuItem(TASKCOORDINATOR2));
            tasks.add(new MenuItem(TASKCOORDINATOR3));
            taskMenuButton.getItems().addAll(tasks);

            for (MenuItem task : taskMenuButton.getItems()) {
                task.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (task.getText().equals(TASKCOORDINATOR1)) {
                            taskMenuButton.setText(TASKCOORDINATOR1);
                            SceneManager.getSceneManager().showCoordinatorDashboard();
//                        } else if (task.getText().equals(TASKCOORDINATOR2)) {
//                            taskMenuButton.setText(TASKCOORDINATOR2);
//                            SceneManager.getSceneManager().showNewQuizScene();
                            //show newQuestionScene. Waar is deze?
//                        } else if (task.getText().equals(TASKCOORDINATOR3)) {
//                            SceneManager.getSceneManager().showEditQuizScene(); // test
                        }
                    }
                });
            }
        }

    }


    public void doLogout(ActionEvent event) {

        Main.setCurrentUser(null);
        SceneManager.getSceneManager().setWindowTool();
    }
}

