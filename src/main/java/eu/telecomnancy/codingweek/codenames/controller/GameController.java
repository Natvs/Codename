package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.GameConfig;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.utils.GenerateCardUtil;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;

public class GameController {
    //FIXME: these should be loaded from GameConf
    private List<String> tempWords = new ArrayList<>();
    private int tempRows = 5;
    private int tempCols = 5;

    @FXML
    private GridPane mainPane;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label currentTeam;

    public GameController(Session session) {
        setConfig(session);
        tempWords.add("Patate");
        tempWords.add("Froide");
        tempWords.add("Entropie");
        tempWords.add("Pomme");
        tempWords.add("Poire");
        tempWords.add("Voiture");
        tempWords.add("Moto");
        tempWords.add("Bateau");
        tempWords.add("Avion");
        tempWords.add("Chaude");
        tempWords.add("Train");
        tempWords.add("Bus");
        tempWords.add("Vélo");
        tempWords.add("Appareil");
        tempWords.add("Téléphone");
        tempWords.add("Ordinateur");
        tempWords.add("Tablette");
        tempWords.add("Télévision");
        tempWords.add("Radio");
        tempWords.add("Livre");
        tempWords.add("Magazine");
        tempWords.add("Marmite");
        tempWords.add("Arbre");
        tempWords.add("Plante");
        tempWords.add("Fleur");
        tempWords.add("Herbe");
    }

    public void setConfig(Session session) {
        this.tempRows = session.getBoard().getWidth();
        this.tempCols = session.getBoard().getHeight();
    }

    @FXML
    private void initialize() {
        mainPane.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onQuit();
                    break;
            }
        });
        for (int i = 0; i < tempRows; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            gameGrid.getColumnConstraints().add(colConstraints);
            for (int j = 0; j < tempCols; j++) {
                var card = GenerateCardUtil.generateCard(tempWords.get(i*5+j));
                gameGrid.add(card, i, j);
            }
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gameGrid.getRowConstraints().add(rowConstraints);
        }
    }

    private void onQuit() {
        RootController.getInstance().changeView("/views/home.fxml");
    }
}
