package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameFooterController {
    private GameController gameController;
    private boolean isAgent;
    @FXML
    private TextField word;
    @FXML
    private Label indication;

    public GameFooterController(GameController gameController,boolean isAgent){
        this.gameController = gameController;
        this.isAgent = isAgent;
    }

    @FXML
    private void initialize(){
        if (!isAgent) {
            indication.setText(gameController.getHint());
        }
    }

    @FXML
    private void onQuit(){
        gameController.onQuit();
    }
    @FXML
    private void onSubmit() {
        if (isAgent){
            String hintValue = word.getText();
            System.out.println(hintValue);
            gameController.setHint(hintValue);
        }
        gameController.onSubmit();
    }
}
