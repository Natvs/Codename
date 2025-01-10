package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.observers.game.ColorSetObserver;
import eu.telecomnancy.codingweek.codenames.observers.game.RoleSetObserver;
import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
import eu.telecomnancy.codingweek.codenames.utils.GenerateFooterUtil;
import eu.telecomnancy.codingweek.codenames.utils.GenerateHeaderUtil;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GameController {
    private final Session session;
    private String hint;
    private int number;

    @FXML
    private GridPane gameView;
    @FXML
    private GridPane gameGrid;
    @FXML 
    private HBox header;
    @FXML
    private HBox footer;

    public GameController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        session.setRoleObserver(new RoleSetObserver(this));
        session.setColorObserver(new ColorSetObserver(this));
        initializeEvents();
        setHeader();
        initCardsBoard();
        setFooter();
    }

    private void initializeEvents() {
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
                var card = grid[j][i];
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

    public void setHeader() {
        var HeaderHBox = GenerateHeaderUtil.generateHeader(session);
        header.getChildren().clear();
        header.getChildren().add(HeaderHBox);
    }
    
    public void setFooter() {
        var FooterHBox = GenerateFooterUtil.generateFooter(this,session);
        footer.getChildren().clear();
        footer.getChildren().add(FooterHBox);
    }
    
    

    public void onQuit() {
        session.quitGame();
    }

    public void onSubmit() {
        setHeader();
        setCardsBoard();
    }
    
    public String getHint(){
        return this.hint;
    }
    
    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getNumber() {
        return this.number;
    }   
    
    public void setNumber(int number) {
        this.number = number;
    }

}
