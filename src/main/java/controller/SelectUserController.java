package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.dao.MaintainerDAO;
import model.entity.user.User;
import view.Main;
import view.SceneManager;
import java.util.List;

/**
 * Doel file:
 * selecteer Gebruiker op naam
 * Delete gebruiker of wijzig gebruiker (in ChangeUserController)
 *
 * author - EvdP - Team Fenix
 */

public class SelectUserController {
    private MaintainerDAO mdao = new MaintainerDAO();

    @FXML
    private Button menuButton;
    @FXML
    private ListView<String> userList = new ListView<String>();
    @FXML
    private Button changeUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Label title;

    public void setup() {
        List<User> nameList = mdao.selectAllUsers();
        ObservableList<String> names = FXCollections.observableArrayList();

        for (User user : nameList) {
            String name = user.getName();
            names.add(name);
        }
        userList.setItems(names);
    }


    public void doChangeUser(ActionEvent event) {
        String selectedName = userList.getSelectionModel().getSelectedItem();
        User user = mdao.selectUserByName(selectedName);
        SceneManager.getSceneManager().showChangeUserScene(user);

    }

    public void doDeleteUser(ActionEvent event) {
        String selectedName = userList.getSelectionModel().getSelectedItem();
        if (mdao.deleteUserByName(selectedName)) {
            SceneManager.getSceneManager().showSelectUserScene();

        } else {
            title.setText("Selecteer gebruiker      Verwijderen van gebruiker niet gelukt.");
        }
    }

    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene(Main.getCurrentUser());
    }


}
