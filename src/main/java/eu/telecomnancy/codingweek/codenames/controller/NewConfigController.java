package eu.telecomnancy.codingweek.codenames.controller;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.player.Player;
import eu.telecomnancy.codingweek.codenames.utils.GeneratePlayerField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    private GridPane blueAgentGrid;
    @FXML
    private TextField blueAgent1;
    @FXML
    private Button addBlueAgent;
    @FXML
    private GridPane blueSpyGrid;
    @FXML
    private TextField blueSpy1;
    @FXML
    private Button addBlueSpy;
    @FXML
    private GridPane redAgentGrid;
    @FXML
    private TextField redAgent1;
    @FXML
    private Button addRedAgent;
    @FXML
    private GridPane redSpyGrid;
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
        List<Player> blueAgents = session.getBlueTeam().getAgentTeam().getPlayersList();
        List<Player> blueSpys = session.getBlueTeam().getSpyTeam().getPlayersList();
        List<Player> redAgents = session.getRedTeam().getAgentTeam().getPlayersList();
        List<Player> redSpys = session.getRedTeam().getSpyTeam().getPlayersList();

        blueAgents.clear();
        blueSpys.clear();
        redAgents.clear();
        redSpys.clear();

        blueAgents.add(new Player(blueAgent1.getText()));
        blueSpys.add(new Player(blueSpy1.getText()));
        redAgents.add(new Player(redAgent1.getText()));
        redSpys.add(new Player(redSpy1.getText()));

        for (int i = 1; i <= nbBlueAgents; i++) {
            if (blueAgentGrid.getChildren().get(i) == null) {
                continue;
            } else if (blueAgentGrid.getChildren().get(i) instanceof HBox) {
                if ((TextField)((HBox) blueAgentGrid.getChildren().get(i)).getChildren().get(0) == null) {
                    continue;
                }
                blueAgents.add(new Player(((TextField)((HBox) blueAgentGrid.getChildren().get(i)).getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbBlueSpy; i++) {
            if (blueSpyGrid.getChildren().get(i) == null) {
                continue;
            } else if (blueSpyGrid.getChildren().get(i) instanceof HBox) {
                if ((TextField)((HBox) blueSpyGrid.getChildren().get(i)).getChildren().get(0) == null) {
                    continue;
                }
                blueSpys.add(new Player(((TextField)((HBox) blueSpyGrid.getChildren().get(i)).getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbRedAgents; i++) {
            if (redAgentGrid.getChildren().get(i) == null) {
                continue;
            } else if (redAgentGrid.getChildren().get(i) instanceof HBox) {
                if ((TextField)((HBox) redAgentGrid.getChildren().get(i)).getChildren().get(0) == null) {
                    continue;
                }
                redAgents.add(new Player(((TextField)((HBox) redAgentGrid.getChildren().get(i)).getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbRedSpy; i++) {
            if (redSpyGrid.getChildren().get(i) == null) {
                continue;
            } else if (redSpyGrid.getChildren().get(i) instanceof HBox) {
                if ((TextField)((HBox) redSpyGrid.getChildren().get(i)).getChildren().get(0) == null) {
                    continue;
                }
                redSpys.add(new Player(((TextField)((HBox) redSpyGrid.getChildren().get(i)).getChildren().get(0)).getText()));
            }
        }

        var config = session.getConfig();
        session.getBoard().setSize(config.width, config.heigth);

        session.startNewGame();
    }

    @FXML
    private void onAddBlueAgent() {
        nbBlueAgents++;

        blueAgentGrid.add(GeneratePlayerField.generateField("Blue Agent " + nbBlueAgents, this, 0), 0, nbBlueAgents-1);

        GridPane.setRowIndex(addBlueAgent, nbBlueAgents);

        if (nbBlueAgents >= 2) {
            addBlueAgent.setVisible(false);
            addBlueAgent.setVisible(false);
        }
    }

    @FXML
    private void onAddBlueSpy() {
        nbBlueSpy++;

        blueSpyGrid.add(GeneratePlayerField.generateField("Blue Spy " + nbBlueSpy, this, 1), 0, nbBlueSpy-1);

        GridPane.setRowIndex(addBlueSpy, nbBlueSpy);

        if (nbBlueSpy >= 3) {
            addBlueSpy.setVisible(false);
            addBlueSpy.setVisible(false);
        }
    }

    @FXML
    private void onAddRedAgent() {
        nbRedAgents++;

        redAgentGrid.add(GeneratePlayerField.generateField("Red Agent " + nbRedAgents, this, 2), 0, nbRedAgents-1);

        GridPane.setRowIndex(addRedAgent, nbRedAgents);

        if (nbRedAgents >= 2) {
            addRedAgent.setVisible(false);
            addRedAgent.setVisible(false);
        }
    }

    @FXML
    private void onAddRedSpy() {
        nbRedSpy++;

        redSpyGrid.add(GeneratePlayerField.generateField("Red Spy " + nbRedSpy, this, 3), 0, nbRedSpy-1);

        GridPane.setRowIndex(addRedSpy, nbRedSpy);

        if (nbRedSpy >= 3) {
            addRedSpy.setVisible(false);
        }
    }
    
    public void removePlayerField(Node playerField, int playerType) {
        switch (playerType) {
            case 0:
                blueAgentGrid.getChildren().remove(playerField);
                nbBlueAgents--;
                addBlueAgent.setVisible(true);
                GridPane.setRowIndex(addBlueAgent, nbBlueAgents);
                break;
            case 1:
                blueSpyGrid.getChildren().remove(playerField);
                nbBlueSpy--;
                addBlueSpy.setVisible(true);
                if (blueSpyGrid.getChildren().size() > 2) {
                    GridPane.setRowIndex(blueSpyGrid.getChildren().get(2), nbBlueSpy-1);
                }
                GridPane.setRowIndex(addBlueSpy, nbBlueSpy);
                break;
            case 2:
                redAgentGrid.getChildren().remove(playerField);
                nbRedAgents--;
                addRedAgent.setVisible(true);
                GridPane.setRowIndex(addRedAgent, nbRedAgents);
                break;
            case 3:
                redSpyGrid.getChildren().remove(playerField);
                nbRedSpy--;
                addRedSpy.setVisible(true);
                if (redSpyGrid.getChildren().size() > 2) {
                    GridPane.setRowIndex(redSpyGrid.getChildren().get(2), nbRedSpy-1);
                }
                GridPane.setRowIndex(addRedSpy, nbRedSpy);
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
