package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class NewConfigController {
    private Session session;

    private int nbBlueAgents = 1;
    private int nbRedAgents = 1;
    private int nbBlueSpy = 1;
    private int nbRedSpy = 1;


    @FXML
    private GridPane newConfigView;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox<String> thematicSelection;
    @FXML
    private Spinner<Integer> nbRows;
    @FXML
    private Spinner<Integer> nbCols;
    @FXML
    private GridPane playersGrid;

    //Teams fields
    @FXML
    private TextField blueAgent1;
    @FXML
    private Button addBlueAgent;
    @FXML
    private TextField blueSpy1;
    @FXML
    private Button addBlueSpy;
    @FXML
    private TextField redAgent1;
    @FXML
    private Button addRedAgent;
    @FXML
    private TextField redSpy1;
    @FXML
    private Button addRedSpy;


    private Boolean startEnable = false;

    public NewConfigController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        nbRows.getValueFactory().setValue(session.getConfig().width);
        nbCols.getValueFactory().setValue(session.getConfig().heigth);
        initializeEvents();
        disableStart();
        initializePlayers();
        thematicSelection.getItems().addAll("Tout", "Patate", "Entropie"); //FIXME: get from db
    }

    private void initializePlayers() {
        blueAgent1.setText("Blue Agent 1");
        blueSpy1.setText("Blue Spy 1");
        redAgent1.setText("Red Agent 1");
        redSpy1.setText("Red Spy 1");
    }

    private void initializeEvents() {
        newConfigView.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onBack();
                    break;
                case KeyCode.S:
                    if (startEnable) { onStart(); }
                    break;
                default:
                    break;
            }
        });
        nbRows.valueProperty().addListener(((observable, oldValue, newValue) -> {
            session.getConfig().heigth = nbRows.getValue();
        }));
        nbCols.valueProperty().addListener(((observable, oldValue, newValue) -> {
            session.getConfig().width = nbRows.getValue();
        }));
    }

    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onStart() {
        var config = session.getConfig();
        session.getBoard().setSize(config.width, config.heigth);
        RootController.getInstance().changeView("/views/game.fxml");
    }

    @FXML
    private void onAddBlueAgent() {
        nbBlueAgents++;

        TextField agentField = new TextField();
        agentField.setText("Blue Agent " + nbBlueAgents);
        agentField.setId("blueAgent " + nbBlueAgents);
        playersGrid.add(agentField, 0, nbBlueAgents+2);

        GridPane.setRowIndex(addBlueAgent, nbBlueAgents + 3);

        if (nbBlueAgents >= 2) {
            playersGrid.getChildren().remove(addBlueAgent);
        }
    }

    @FXML
    private void onAddBlueSpy() {
        nbBlueSpy++;

        TextField spyField = new TextField();
        spyField.setText("Blue Spy " + nbBlueSpy);
        spyField.setId("blueSpy " + nbBlueSpy);
        playersGrid.add(spyField, 1, nbBlueSpy+2);

        GridPane.setRowIndex(addBlueSpy, nbBlueSpy + 3);

        if (nbBlueSpy >= 3) {
            playersGrid.getChildren().remove(addBlueSpy);
        }
    }

    @FXML
    private void onAddRedAgent() {
        nbRedAgents++;

        TextField agentField = new TextField();
        agentField.setText("Red Agent " + nbRedAgents);
        agentField.setId("redAgent " + nbRedAgents);
        playersGrid.add(agentField, 2, nbRedAgents+2);

        GridPane.setRowIndex(addRedAgent, nbRedAgents + 3);

        if (nbRedAgents >= 2) {
            playersGrid.getChildren().remove(addRedAgent);
        }
    }

    @FXML
    private void onAddRedSpy() {
        nbRedSpy++;

        TextField spyField = new TextField();
        spyField.setText("Red Spy " + nbRedSpy);
        spyField.setId("redSpy " + nbRedSpy);
        playersGrid.add(spyField, 3, nbRedSpy+2);

        GridPane.setRowIndex(addRedSpy, nbRedSpy + 3);

        if (nbRedSpy >= 3) {
            playersGrid.getChildren().remove(addRedSpy);
        }
    }

    @FXML
    private void onThematicSelection() {
        enableStart();
    }

    private void disableStart() {
        startButton.setDisable(true);
        startEnable = false;
    }
    private void enableStart() {
        startButton.setDisable(false);
        startEnable = true;
    }

}
