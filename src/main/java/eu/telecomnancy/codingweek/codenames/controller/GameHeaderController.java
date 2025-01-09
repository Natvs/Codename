package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.TimeObserver;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameHeaderController {
    private Session session;
    @FXML
    private Label currentTeam; 
    @FXML
    private Label timer;

    public GameHeaderController(Session session) {
        this.session = session;
    }

    @FXML
    public void initialize(){
        setCurrentTeam();
        session.setTimeObserver(new TimeObserver(this));
        session.resetTime();
        var service = session.getService();
        if (service.getState() == State.READY) {
            service.start();
        }
        else if (service.getState() == State.CANCELLED){
            service.restart();
        }
    }

    private void setCurrentTeam(){
        var builder = new StringBuilder();
        builder.append("Equipe ").append(switch (session.getCurrentColor()) {
            case Color.BLUE -> "bleue";
            case Color.RED -> "rouge";
            default -> "undefined";
        }).append(" - ");
        if (session.isAgent()) builder.append("agents");
        else builder.append("espions");
        builder.append(" : ");

        Team team = session.getCurrentTeam();
        if (team != null) {
            boolean first = true;
            for (var player : team.getPlayersList()) {
                if (!first) builder.append(" - "); else first = false;
                builder.append(player.getName());
            }
        }
        currentTeam.setText(builder.toString());
        timer.setText("");
    }

    public void setTimeLabel() {
        timer.setText(String.valueOf(session.getTime()));
    }
}
