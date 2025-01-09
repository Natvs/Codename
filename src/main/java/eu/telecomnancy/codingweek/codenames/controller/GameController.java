package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.ColorSetObserver;
import eu.telecomnancy.codingweek.codenames.observers.game.RoleSetObserver;
import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GameController {
    private final Session session;

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
        session.setRoleObserver(new RoleSetObserver(this));
        session.setColorObserver(new ColorSetObserver(this));
        setEvents();
        setLabel();
        initCardsBoard();
    }
    private void setEvents() {
        gameView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.Q -> onQuit();
                default -> {}
            }
        });
    }
    private void initCardsBoard() {
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            gameGrid.getColumnConstraints().add(colConstraints);
        }
        for (int j = 0; j < session.getBoard().getHeigth(); j++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gameGrid.getRowConstraints().add(rowConstraints);
        }
        setCardsBoard();
    }

    public void setCardsBoard() {
        Card[][] grid = session.getBoard().getGrid();
        for (int i = 0; i < session.getBoard().getWidth(); i++) {
            for (int j = 0; j < session.getBoard().getHeigth(); j++) {
                var card = grid[i][j];
                var cardBox = GenerateCardUtil.generateCard(card, session);
                cardBox.setOnMouseClicked((mouveEvent) -> 
                { 
                    if (!card.getRevealed() && !session.isAgent()) { 
                        session.guessCard(card); 
                    } 
                });
                gameGrid.add(cardBox, i, j);
            }
        }
    }

    public void setLabel() {
        var builder = new StringBuilder();
        builder.append("Equipe ").append(switch (session.getCurrentColor()) {
            case Color.BLUE -> "bleue";
            case Color.RED -> "rouge";
            default -> "undefined";
        }).append(" - ");
        if (session.isAgent()) builder.append("agents");
        else builder.append("espions");
        builder.append(" : ");

        var coloredTeam = switch (session.getCurrentColor()) {
            case Color.BLUE -> session.getBlueTeam();
            case Color.RED -> session.getRedTeam();
            default -> null;
        };
        Team team = null;
        if (coloredTeam != null) {
            if (session.isAgent()) team = coloredTeam.getAgentTeam();
            else team = coloredTeam.getSpyTeam();
        }
        if (team != null) {
            for (var player : team.getPlayersList()) builder.append(player.getName()).append(" - ");
        }

        currentTeam.setText(builder.toString());
    }
    @FXML
    private void onQuit() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onSubmit() {
        session.addClue(new Clue(0,2));
    }

}
