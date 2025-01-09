package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class GameFooterController {
    @FXML
    private TextField word;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Label indication;

    private Session session;
    private GameController controller;

    public GameFooterController(GameController controller, Session session){
        this.session = session;
        this.controller = controller;
    }

    @FXML
    private void initialize(){
        if (!session.isAgent()) {
            var clue = session.getCurrentColoredTeam().getCluesList().getLast();
            indication.setText(clue.getWord() + " en " + clue.getCount());
        }
    }

    @FXML
    private void onQuit(){
        controller.onQuit();
    }
    @FXML
    private void onSubmit() {
        controller.onSubmit();
        if (session.isAgent()){
            session.addClue(new Clue(word.getText(), spinner.getValue()));  
        }
        else {
            session.guessCard(null);
        }
    }
}
