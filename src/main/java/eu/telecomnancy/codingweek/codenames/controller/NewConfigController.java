package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.GeneratePlayerField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

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

        playersGrid.add(GeneratePlayerField.generateField("Blue Agent " + nbBlueAgents, this, 0), 0, nbBlueAgents+2);

        GridPane.setRowIndex(addBlueAgent, nbBlueAgents + 3);

        if (nbBlueAgents >= 2) {
            addBlueAgent.setVisible(false);
        }
    }

    @FXML
    private void onAddBlueSpy() {
        nbBlueSpy++;

        playersGrid.add(GeneratePlayerField.generateField("Blue Spy " + nbBlueSpy, this, 1), 1, nbBlueSpy+2);

        GridPane.setRowIndex(addBlueSpy, nbBlueSpy + 3);

        if (nbBlueSpy >= 3) {
            addBlueSpy.setVisible(false);
        }
    }

    @FXML
    private void onAddRedAgent() {
        nbRedAgents++;

        playersGrid.add(GeneratePlayerField.generateField("Red Agent " + nbRedAgents, this, 2), 2, nbRedAgents+2);

        GridPane.setRowIndex(addRedAgent, nbRedAgents + 3);

        if (nbRedAgents >= 2) {
            addRedAgent.setVisible(false);
        }
    }

    @FXML
    private void onAddRedSpy() {
        nbRedSpy++;

        playersGrid.add(GeneratePlayerField.generateField("Red Spy " + nbRedSpy, this, 3), 3, nbRedSpy+2);

        GridPane.setRowIndex(addRedSpy, nbRedSpy + 3);

        if (nbRedSpy >= 3) {
            addRedSpy.setVisible(false);
        }
    }
    
    public void removePlayerField(Node playerField, int column) {
        playersGrid.getChildren().remove(playerField);
        switch (column) {
            case 0:
                nbBlueAgents--;
                addBlueAgent.setVisible(true);
                GridPane.setRowIndex(addBlueAgent, nbBlueAgents + 3);
                break;
            case 1:
                nbBlueSpy--;
                addBlueSpy.setVisible(true);
                break;
            case 2:
                nbRedAgents--;
                addRedAgent.setVisible(true);
                GridPane.setRowIndex(addRedAgent, nbRedAgents + 3);
                break;
            case 3:
                nbRedSpy--;
                addRedSpy.setVisible(true);
                break;
            default:
                break;
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
