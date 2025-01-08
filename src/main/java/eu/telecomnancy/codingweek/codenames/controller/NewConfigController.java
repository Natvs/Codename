package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class NewConfigController {

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

    private Boolean startEnable = false;

    @FXML
    private void initialize() {
        nbRows.getValueFactory().setValue(Session.getInstance().getConfig().width);
        nbCols.getValueFactory().setValue(Session.getInstance().getConfig().heigth);
        initializeEvents();
        disableStart();
        thematicSelection.getItems().addAll("Tout", "Patate", "Entropie");
    }

    private void initializeEvents(Session session) {
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
            Session.getInstance().getConfig().heigth = nbRows.getValue();
        }));
        nbCols.valueProperty().addListener(((observable, oldValue, newValue) -> {
            Session.getInstance().getConfig().width = nbRows.getValue();
        }));
    }

    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onStart() {
        var session = Session.getInstance();
        var config = session.getConfig();
        Session.getInstance().getBoard().setSize(config.width, config.heigth);
        RootController.getInstance().changeView("/views/game.fxml");
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
