package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndController {
    
    @FXML
    private Label teamLabel;

    @FXML
    private Button nextButton;

    private Session session;

    public EndController(Session session) {
        this.session = session;
    }

    public void initialize() {
        setLabel();
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
