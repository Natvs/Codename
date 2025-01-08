package eu.telecomnancy.codingweek.codenames.controller;

import java.awt.TextField;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.observers.game.SessionColorObserver;
import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
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
    @FXML
    private TextField word;
    public GameController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        session.setColorObserver(new SessionColorObserver(this));
        setLabel();
        setEvents();
        setCardsBoardInit();
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
    private void setCardsBoardInit() {
        Card[][] grid = session.getBoard().getGrid();
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            gameGrid.getColumnConstraints().add(colConstraints);
            for (int j = 0; j < session.getBoard().getHeigth(); j++) {
                var card = grid[i][j];
                var cardBox = GenerateCardUtil.generateCard(card, session);
                cardBox.setOnMouseClicked((mouveEvent) -> { if (!card.getRevealed()) { session.guessCard(card); } });
                gameGrid.add(cardBox, i, j);
            }
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gameGrid.getRowConstraints().add(rowConstraints);
        }
    }
    private void setCardsBoard() {
        Card[][] grid = session.getBoard().getGrid();
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            for (int j = 0; j < session.getBoard().getHeigth(); j++) {
                var card = GenerateCardUtil.generateCard(grid[i][j],session);
                gameGrid.add(card, i, j);
            }
            
        }
    }
    private void setLabel() {
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
    @FXML
    private void onQuit() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onSubmit() {
        if (session.isAgent()){
            session.changeRole(false);
        } else {
            session.setCurrentColor();
            session.changeRole(true);
        }
        setLabel();
        setCardsBoard();
    }
}
