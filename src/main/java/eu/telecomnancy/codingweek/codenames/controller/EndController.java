package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class EndController {
    

    @FXML
    private GridPane endView;
    @FXML
    private Label teamLabel;
    @FXML
    private Button nextButton;

    private Session session;

    public EndController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        setLabel();
        initializeEvents();
    }
    private void initializeEvents() {
        endView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case Q -> onContinue();
                default -> {}
            }
        });
    }

    private void setLabel() {
        var builder = new StringBuilder();
        builder.append("L'équipe ").append(switch (session.getCurrentColor()) {
            case BLUE -> "bleue";
            case RED -> "rouge";
            default -> "undefined";
        }).append(" a remportée la partie avec ").append(session.getCurrentColoredTeam().getScore()).append(" mots.");
        teamLabel.setText(builder.toString());
    }

    @FXML
    private void onContinue() {
        RootController.getInstance().changeView("/views/home.fxml");
    }


}
