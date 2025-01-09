package eu.telecomnancy.codingweek.codenames.controller;

import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.TimeObserver;
import javafx.concurrent.Worker.State;

public class GameHeaderController {
    private Session session;
    private GameController controller;
    @FXML
    private Label currentTeam; 
    @FXML
    private Label timer;

    public GameHeaderController(Session session, GameController gameController) {
        this.session = session;
        this.controller = gameController;
    }

    @FXML
    public void initialize(){
        setCurrentTeam();
        ScheduledService<Void> service = session.getTimer();
        session.setTimeObserver(new TimeObserver(this));
        if (service == null) {
            session.setTimer();
        } else if (session.isAgent() && service.isRunning()){
            service.cancel();
        } else if (!session.isAgent()) {
            if (service.getState() == State.READY) {
                service.start();
            }
            if (service.getState() == State.CANCELLED){
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
        timer.setText("");
    }

    public void setTimeLabel() {
        System.out.println("test timer");
        timer.setText(String.valueOf(session.getTime()));
    }
    

    private void setTimer() {
        // Create a ScheduledService to run periodically
        ScheduledService<Void> service = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        // Update the UI from the background task
                        Platform.runLater(() -> {
                            System.out.println("Timer: ");
                        });
                        return null;
                    }
                };
            }
        };
        
        service.setPeriod(Duration.seconds(10));
        service.start();
        controller.setService(service);
    }

}
