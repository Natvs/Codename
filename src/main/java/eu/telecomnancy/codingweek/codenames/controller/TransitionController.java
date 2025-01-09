package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransitionController {
    
    @FXML
    private Label teamLabel;

    @FXML
    private Label roleLabel;

    private final Session session;

    public TransitionController(Session session) {
        this.session = session;
    }

    public void initialize() {
        setLabel();
    }

    public void setLabel() {
        var teamBuilder = new StringBuilder();
        teamBuilder.append("Equipe ");
        teamBuilder.append( switch (session.getCurrentColor()) {
            case Color.BLUE -> "bleue";
            case Color.RED -> "rouge";
            default -> "undefined";
        });
        teamLabel.setText(teamBuilder.toString());

        var roleBuilder = new StringBuilder();
        roleBuilder.append("Rôle : ");
        if (session.isAgent()) roleBuilder.append("agent");
        else roleBuilder.append("espion");
        roleLabel.setText(roleBuilder.toString());
    }

    @FXML
    private void onContinue() {
        RootController.getInstance().changeView("/views/game.fxml");
    }

}
