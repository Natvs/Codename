package eu.telecomnancy.codingweek.codenames.controller;

import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

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
    private void initialize(){
        setCurrentTeam();
        if (!session.isAgent()){
            setTimer();
        }
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
