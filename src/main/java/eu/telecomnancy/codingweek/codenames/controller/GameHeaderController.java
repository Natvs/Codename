package eu.telecomnancy.codingweek.codenames.controller;

import javafx.scene.control.Label;
import javafx.concurrent.ScheduledService;
import javafx.fxml.FXML;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
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
        //timer.setText("Timer: ");
    }

    private void setCurrentTeam(){
        String role = new String();
        String colorName = new String();
        Color color = session.getCurrentColor();
        if (session.isAgent()){
            role = "agent";
        } else {
            role = "spy";
        }
        if (color == Color.BLUE){
            colorName = "Blue";
        } else if (color == Color.RED) {
            colorName = "Red";
        }
        currentTeam.setText(colorName + " " + role);
        timer.setText("");
    }

    public void setTimeLabel() {
        System.out.println("test timer");
        timer.setText(String.valueOf(session.getTime()));
    }

}
