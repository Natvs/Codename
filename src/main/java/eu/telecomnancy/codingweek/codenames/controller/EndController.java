package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class EndController {
    @FXML
    private GridPane gamePreviewGrid;
    @FXML
    private GridPane endView;
    @FXML
    private Label teamLabel;
    @FXML
    private Button nextButton;

    private Session session;

    public EndController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        setLabel();
        setGamePreview();
        initializeEvents();
    }

    private void initializeEvents() {
        endView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case Q -> onContinue();
                default -> {}
            }
        });
    }

    private void setLabel() {
        var builder = new StringBuilder();
        builder.append("L'équipe ").append(switch (session.getCurrentColor()) {
            case BLUE -> "bleue";
            case RED -> "rouge";
            default -> "undefined";
        }).append(" a remportée la partie avec ").append(session.getCurrentColoredTeam().getScore()).append(" mots.");
        teamLabel.setText(builder.toString());
    }

    private void setGamePreview() {
        System.out.println("setGamePreview");
        gamePreviewGrid.getChildren().clear();

        gamePreviewGrid.setHgap(5);
        gamePreviewGrid.setVgap(5);

        int width = session.getBoard().getWidth();
        int height = session.getBoard().getHeigth();

        double percentWidth = 100.0 / width;
        double percentHeigth = 100.0 / height;

        for (int i = 0; i < width; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(percentWidth);
            gamePreviewGrid.getColumnConstraints().add(colConstraints);
        }
        for (int j = 0; j < height; j++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(percentHeigth);
            gamePreviewGrid.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var card = session.getBoard().getCard(j, i);
                Label cardBox = new Label();
                if (card.getRevealed()) {
                    cardBox.setId("card-" + card.getColor().toString().toLowerCase());
                }
                cardBox.setText(card.getName());
                gamePreviewGrid.add(cardBox, i, j);
            }
        }
    }

    @FXML
    private void onContinue() {
        RootController.getInstance().changeView("/views/home.fxml");
    }


}
