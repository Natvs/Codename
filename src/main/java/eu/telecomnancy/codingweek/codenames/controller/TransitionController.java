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
import javafx.scene.layout.GridPane;

public class TransitionController {
    
    @FXML 
    private GridPane transitionView;
    @FXML
    private Label teamLabel;
    @FXML
    private Label roleLabel;

    private final Session session;

    public TransitionController(Session session) {
        this.session = session;
    }

    public void initialize() {
        setLabel();
        setEvents();
        if (session.getConfig().discreetMode && session.isAgent()) {
            showQRCode();
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
        teamLabel.setText(teamBuilder.toString());

        var roleBuilder = new StringBuilder();
        if (session.isAgent()) roleBuilder.append("Agents : ");
        else roleBuilder.append("Espions : ");
        Team team = session.getCurrentTeam();
        if (team != null) {
            boolean first = true;
            for (var player : team.getPlayersList()) {
                if (!first) roleBuilder.append(" - "); else first = false;
                roleBuilder.append(player.getName());
            }
        }
        roleLabel.setText(roleBuilder.toString());
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
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
            transitionView.add(imageView, 0, 2);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onContinue() {
        if (session.isAgent()) {
            ClueGuesser clueGuesser = new ClueGuesser(session);
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
        //RootController.getInstance().changeView("/views/game.fxml");
    }

    public void onQuit() {
        session.quitGame();
    }

}
