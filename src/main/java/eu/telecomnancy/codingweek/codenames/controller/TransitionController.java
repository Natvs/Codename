package eu.telecomnancy.codingweek.codenames.controller;

import java.io.IOException;

import com.google.zxing.WriterException;

import eu.telecomnancy.codingweek.codenames.ia.algo.CardGuesser;
import eu.telecomnancy.codingweek.codenames.ia.algo.ClueGuesser;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.utils.QRCodeGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class TransitionController {
    @FXML
    private Label turnLabel;
    @FXML 
    private GridPane transitionView;
    @FXML
    private GridPane gamePreviewGrid;
    @FXML
    private Label roleLabel;

    private final Session session;

    public TransitionController(Session session) {
        this.session = session;
    }

    public void initialize() {
        setTurn();
        setLabel();
        setEvents();
        setGamePreview();
        if (session.getConfig().discreetMode && session.isAgent()) {
            showQRCode();
        }
    }

    private void setTurn() {
        if (session.isIA()) {
            turnLabel.setText("Au tour de l'IA");
        } else {
            turnLabel.setText("A vous de jouer !");
        }
    }

    private void setEvents() {
        transitionView.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.Q -> onQuit();
                case KeyCode.C -> onContinue();
                default -> {}
            }
        });
    }

    public void setLabel() {
        var teamBuilder = new StringBuilder();
        teamBuilder.append("Equipe ");
        teamBuilder.append( switch (session.getCurrentColor()) {
            case Color.BLUE -> "bleue";
            case Color.RED -> "rouge";
            default -> "undefined";
        });
        teamBuilder.append(" -- ");
        if (session.isAgent()) teamBuilder.append("Agents : ");
        else teamBuilder.append("Espions : ");
        Team team = session.getCurrentTeam();
        if (team != null) {
            boolean first = true;
            for (var player : team.getPlayersList()) {
                if (!first) teamBuilder.append(" - "); else first = false;
                teamBuilder.append(player.getName());
            }
        }
        roleLabel.setText(teamBuilder.toString());
    }

    private void setGamePreview() {
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
                Pane cardBox = new Pane();
                if (card.getRevealed()) {
                    cardBox.setId("card-" + card.getColor().toString().toLowerCase());
                }
                gamePreviewGrid.add(cardBox, i, j);
            }
        }
    }

        

    private void showQRCode() {
        int width = session.getBoard().getWidth();
        int height = session.getBoard().getHeigth();

        String builder ="";
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (session.getBoard().getCard(i, j).getColor()) {
                    case WHITE :
                        builder += "0";
                        if ((i + 1) * (j + 1) != width * height)
                            builder += ",";
                        break;
                    case BLACK :
                        builder += "1";
                        if ((i + 1) * (j + 1) != width * height)
                            builder += ",";
                        break;
                    case BLUE :
                        builder += "2";
                        if ((i + 1) * (j + 1) != width * height)
                            builder += ",";
                        break;
                    case RED :
                         builder += "3";
                         if ((i + 1) * (j + 1) != width * height)
                             builder += ",";
                        break;
                    default:
                        break;
                }
            }
        }



        String urlToEncode = "https://lmandrelli.github.io/AnthropicPotato" + "?width=" + width + "&height="+ height +"&colors=" + builder;
        System.out.println(urlToEncode);
        
        try {
            Image image = QRCodeGenerator.generateQRCodeImage(urlToEncode);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(170);
            imageView.setFitWidth(170);
            imageView.setPreserveRatio(true);
            GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
            transitionView.add(imageView, 0, 3);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onContinue() {
        if (session.isIA()) {
            if (session.isAgent()) {
                ClueGuesser clueGuesser = new ClueGuesser(session.getCurrentColor(), session);
                Clue clue = clueGuesser.getClue();
                session.getExecuter().cancelCommands();
                session.addClue(clue);
            } else {
                System.out.print("count = " + session.getCurrentColoredTeam().getCluesList().isEmpty());
                if (!session.getCurrentColoredTeam().getCluesList().isEmpty()) {
                    var cardGuesser = new CardGuesser(session);
                    var result = cardGuesser.getResult();
                    var i = 0;
                    var error = false;
                    while (!error && i < result.length) {
                        var card = result[i];
                        var role = session.isAgent();
                        System.out.println(String.valueOf(i));
                        session.getExecuter().cancelCommands();
                        session.guessCard(card);
                        i++;
                        if (role != session.isAgent()) {
                            error = true;
                        }
                    }
                }
            }
        }
        else {
            RootController.getInstance().changeView("/views/game.fxml");
        }
    }

    public void onQuit() {
        session.quitGame();
    }

}
