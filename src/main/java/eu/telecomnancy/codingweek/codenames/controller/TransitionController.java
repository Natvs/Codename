package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class TransitionController {
    
    @FXML 
    private GridPane transitionView;
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
        setEvents();
    }
    private void setEvents() {
        transitionView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.Q -> onQuit();
                case KeyCode.C -> onContinue();
                default -> {}
            }
        });
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
        if (session.isAgent()) roleBuilder.append("Agents : ");
        else roleBuilder.append("Espions : ");
        Team team = session.getCurrentTeam();
        if (team != null) {
            boolean first = true;
            for (var player : team.getPlayersList()) {
                if (!first) roleBuilder.append(" - "); else first = false;
                roleBuilder.append(player.getName());
            }
        }
        roleLabel.setText(roleBuilder.toString());
    }

    @FXML
    private void onContinue() {
        RootController.getInstance().changeView("/views/game.fxml");
    }

    public void onQuit() {
        session.quitGame();
    }

}
