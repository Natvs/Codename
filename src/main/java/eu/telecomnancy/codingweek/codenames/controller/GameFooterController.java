package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class GameFooterController {
    private GameController gameController;
    private boolean isAgent;
    @FXML
    private TextField word;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Label indication;

    public GameFooterController(GameController gameController,boolean isAgent){
        this.gameController = gameController;
        this.isAgent = isAgent;
    }

    @FXML
    private void initialize(){
        if (!isAgent) {
            indication.setText(gameController.getHint() + " en " + gameController.getNumber());
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
            gameController.setHint(hintValue);
            Integer number = spinner.getValue();
            gameController.setNumber(number);
        }
        gameController.onSubmit();
    }
}
