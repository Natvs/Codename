package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.observers.game.SessionColorObserver;
import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GameController {
    private Session session;

    @FXML
    private GridPane gameView;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label currentTeam;

    public GameController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        session.setColorObserver(new SessionColorObserver(this));
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
        var cards = openCardsService.initGridCards(session.getBoard().getHeigth(), session.getBoard().getWidth());
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            gameGrid.getColumnConstraints().add(colConstraints);
            for (int j = 0; j < session.getBoard().getHeigth(); j++) {
                var card = cards[i][j];
                var cardBox = GenerateCardUtil.generateCard(card);
                cardBox.setOnMouseClicked((mouveEvent) -> { if (!card.getRevealed()) { session.guessCard(card); } });
                gameGrid.add(cardBox, i, j);
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

    public void setTeamColor() {
        switch (session.getCurrentColor()) {
            case Color.BLUE:
                gameGrid.setStyle("-fx-background-color: #0084ff");
                break;
            case Color.RED:
                gameGrid.setStyle("-fx-background-color: #c80000");
                break;
            default:
                break;
        }
    }
}
