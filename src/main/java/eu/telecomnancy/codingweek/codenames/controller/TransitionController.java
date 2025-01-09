package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import javafx.concurrent.ScheduledService;
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
        ScheduledService<Void> service = session.getTimer();
        if (service != null && service.isRunning()){
            service.cancel();
        }
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

}
