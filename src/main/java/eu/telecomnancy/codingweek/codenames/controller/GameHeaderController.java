package eu.telecomnancy.codingweek.codenames.controller;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.concurrent.ScheduledService;
import javafx.fxml.FXML;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.TimeObserver;
import javafx.concurrent.Worker.State;

public class GameHeaderController {
    private Session session;
    @FXML
    private Label currentTeam; 
    @FXML
    private ProgressBar timer;

    public GameHeaderController(Session session) {
        this.session = session;
    }

    @FXML
    public void initialize(){
        setCurrentTeam();
        ScheduledService<Void> service = session.getTimer();
        session.setTimeObserver(new TimeObserver(this));
        if (service == null) {
            session.setTimer();
            service = session.getTimer();
            service.start();
        } else {
            if (service.getState() == State.READY) {
                service.start();
            } else if (service.getState() == State.RUNNING){
                service.restart();
                session.resetTime();
            } else if (service.getState() == State.CANCELLED){
                service.restart();
                session.resetTime();
            }
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
        timer.setProgress(0);
    }

    public void setTimeLabel() {
        int timeMax = session.isAgent() ? session.getConfig().timerAgent*10 : session.getConfig().timerSpy*10;
        timer.setProgress(((double)session.getTime())/timeMax);
    }
}
