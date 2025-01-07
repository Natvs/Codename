package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;


public class HomeController {

    @FXML
    private GridPane mainPane;

    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

    @FXML
    public void initialize() {
        mainPane.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onQuit();
                    break;
                case KeyCode.N:
                    onNewGame();
                    break;
            }
        });
    }

    @FXML
    private void onNewGame() {
        RootController.getInstance().changeView("/views/newConfig.fxml");
    }   

    @FXML
    private void onQuit() {
        System.exit(0);
    }
}
