package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;


public class HomeController {

    @FXML
    private GridPane homeView;

    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

    @FXML
    public void initialize() {
        homeView.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onQuit();
                    break;
                case KeyCode.N:
                    onNewGame();
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    private void onNewGame() {
        Session session = new Session();
        RootController.getInstance().changeView("/views/newConfig.fxml",session);
    }   

    @FXML
    private void onQuit() {
        System.exit(0);
    }
}
