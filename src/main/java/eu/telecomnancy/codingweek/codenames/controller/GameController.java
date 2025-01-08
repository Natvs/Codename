package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.scene.control.Label;

import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;

public class GameController {
    @FXML
    private GridPane gameView;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label currentTeam;

    public GameController() {

    }

    @FXML
    private void initialize() {
        setEvents();
        setCardsBoard();
    }
    private void setEvents() {
        gameView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onQuit();
                    break;
                default:
                    break;
            }
        });
    }

    private void setCardsBoard() {
        var session = Session.getInstance();
        var cards = openCardsService.initGridCards(session.getBoard().getHeigth(), session.getBoard().getWidth());
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            gameGrid.getColumnConstraints().add(colConstraints);
            for (int j = 0; j < session.getBoard().getHeigth(); j++) {
                var card = GenerateCardUtil.generateCard(cards[i][j]);
                gameGrid.add(card, i, j);
            }
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gameGrid.getRowConstraints().add(rowConstraints);
        }
    }

    @FXML
    private void onQuit() {
        RootController.getInstance().changeView("/views/home.fxml");
    }
}
