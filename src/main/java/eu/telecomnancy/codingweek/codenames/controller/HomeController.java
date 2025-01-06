package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class HomeController {
    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

    @FXML
    private void onNewGame() {
        RootController.getInstance().changeView("/views/newConfig.fxml");
    }   

    @FXML
    private void onQuit() {
        System.exit(0);
    }
}
