package eu.telecomnancy.codingweek.codenames.controller;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.dictionaryManager.DicoManager;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.player.Player;
import eu.telecomnancy.codingweek.codenames.observers.newconfig.ImageModeObserver;
import eu.telecomnancy.codingweek.codenames.observers.newconfig.TimerObserver;
import eu.telecomnancy.codingweek.codenames.theme.Utility;
import eu.telecomnancy.codingweek.codenames.utils.AutoCompleteTextField;
import eu.telecomnancy.codingweek.codenames.utils.GeneratePlayerField;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NewConfigController {
    private final Session session;

    private int nbBlueAgents = 1;
    private int nbRedAgents = 1;
    private int nbBlueSpy = 1;
    private int nbRedSpy = 1;

    private ArrayList<String> wordsList = null;

    @FXML
    private GridPane newConfigView;
    @FXML
    private Button startButton;
    @FXML
    private Spinner<Integer> nbRows;
    @FXML
    private Spinner<Integer> nbCols;
    @FXML
    private CheckBox imageModeCheck;
    @FXML
    private CheckBox discreetModeCheck;

    //Teams fields
    @FXML
    private GridPane blueAgentGrid;
    @FXML
    private TextField blueAgent1;
    @FXML
    private Button addBlueAgent;
    @FXML
    private Button setBlueAgentIA;
    @FXML
    private GridPane blueSpyGrid;
    @FXML
    private TextField blueSpy1;
    @FXML
    private Button addBlueSpy;
    @FXML
    private Button setBlueSpyIA;
    @FXML
    private GridPane redAgentGrid;
    @FXML
    private TextField redAgent1;
    @FXML
    private Button addRedAgent;
    @FXML
    private Button setRedAgentIA;
    @FXML
    private GridPane redSpyGrid;
    @FXML
    private TextField redSpy1;
    @FXML
    private Button addRedSpy;
    @FXML
    private Button setRedSpyIA;

    //Timers
    @FXML
    private CheckBox timerCheck;
    @FXML
    private Label agentTimerDesc;
    @FXML
    private Slider agentTimer;
    @FXML
    private Slider spyTimer;
    @FXML
    private Label spyTimerDesc;
    @FXML
    private Label agentTimerLabel;
    @FXML
    private Label spyTimerLabel;

    //Theme
    @FXML
    private AutoCompleteTextField themeField;
    @FXML
    private Label nbWordsLabel;

    // Words
    @FXML
    private TextField addWord;
    @FXML
    private Button submitWord;

    private Boolean startEnable = false;

    public NewConfigController(Session session) {
        this.session = session;
    }

    @FXML
    private void initialize() {
        imageModeCheck.selectedProperty().set(session.getConfig().getImageMode());
        nbRows.getValueFactory().setValue(session.getConfig().heigth);
        nbCols.getValueFactory().setValue(session.getConfig().width);
        initializeEvents();
        initializePlayers();

        timerCheck.selectedProperty().set(session.getConfig().getTimer());
        setMaxBoardSize();
        checkTimers();

        agentTimerLabel.textProperty().bind(
                Bindings.format("%.0f s", agentTimer.valueProperty()));

        spyTimerLabel.textProperty().bind(
                Bindings.format("%.0f s", spyTimer.valueProperty()));
        
        themeField.setEntries(session.getBoard().getFullWordList());

        String os = System.getProperty("os.name").toLowerCase();
        if (!os.contains("win")) {
            addWord.setManaged(false);
            submitWord.setManaged(false);
        }

        addBlueSpy.setVisible(!session.getConfig().blueSpyIA);
        addRedSpy.setVisible(!session.getConfig().redSpyIA);
        addBlueAgent.setVisible(!session.getConfig().blueAgentIA);
        addRedAgent.setVisible(!session.getConfig().redAgentIA);
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
                case Q -> onBack();
                case N -> { if (startEnable) onStart(); }
                default -> {}
            }
        });
        nbRows.valueProperty().addListener(((observable, oldValue, newValue) -> {
            session.getConfig().heigth = nbRows.getValue();
        }));
        nbCols.valueProperty().addListener(((observable, oldValue, newValue) -> {
            session.getConfig().width = nbCols.getValue();
        }));

        session.getConfig().setImageModeObserver(new ImageModeObserver(this));
        imageModeCheck.selectedProperty().addListener(((value) -> {
             session.getConfig().setImageMode(imageModeCheck.selectedProperty().get()); 
        }));
        session.getConfig().setTimerObserver(new TimerObserver(this));
        timerCheck.selectedProperty().addListener(((value) -> {
            session.getConfig().setTimer(timerCheck.selectedProperty().get());
        }));
    }

    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onStart() {
        List<Player> blueAgents = session.getBlueTeam().getAgentTeam().getPlayersList();
        List<Player> blueSpies = session.getBlueTeam().getSpyTeam().getPlayersList();
        List<Player> redAgents = session.getRedTeam().getAgentTeam().getPlayersList();
        List<Player> redSpies = session.getRedTeam().getSpyTeam().getPlayersList();

        blueAgents.clear();
        blueSpies.clear();
        redAgents.clear();
        redSpies.clear();

        blueAgents.add(new Player(blueAgent1.getText()));
        blueSpies.add(new Player(blueSpy1.getText()));
        redAgents.add(new Player(redAgent1.getText()));
        redSpies.add(new Player(redSpy1.getText()));

        for (int i = 1; i <= nbBlueAgents; i++) {
            if (blueAgentGrid.getChildren().get(i) == null) {
                
            } else if (blueAgentGrid.getChildren().get(i) instanceof HBox hBox) {
                if ((TextField)hBox.getChildren().get(0) == null) {
                    continue;
                }
                blueAgents.add(new Player(((TextField)hBox.getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbBlueSpy; i++) {
            if (blueSpyGrid.getChildren().get(i) == null) {
                
            } else if (blueSpyGrid.getChildren().get(i) instanceof HBox hBox) {
                if ((TextField)hBox.getChildren().get(0) == null) {
                    continue;
                }
                blueSpies.add(new Player(((TextField)hBox.getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbRedAgents; i++) {
            if (redAgentGrid.getChildren().get(i) == null) {
                
            } else if (redAgentGrid.getChildren().get(i) instanceof HBox hBox) {
                if ((TextField)hBox.getChildren().get(0) == null) {
                    continue;
                }
                redAgents.add(new Player(((TextField)hBox.getChildren().get(0)).getText()));
            }
        }
        for (int i = 1; i <= nbRedSpy; i++) {
            if (redSpyGrid.getChildren().get(i) == null) {
                
            } else if (redSpyGrid.getChildren().get(i) instanceof HBox hBox) {
                if ((TextField)hBox.getChildren().get(0) == null) {
                    continue;
                }
                redSpies.add(new Player(((TextField)hBox.getChildren().get(0)).getText()));
            }
        }

        session.getConfig().agentTime = (int) agentTimer.getValue();
        session.getConfig().spyTime = (int) spyTimer.getValue();
        session.getBoard().setSize(session.getConfig().width, session.getConfig().heigth);

        if (wordsList != null) {
            session.getBoard().setWordList(wordsList);
        }

        session.getConfig().discreetMode = discreetModeCheck.isSelected();

        session.startNewGame();
    }

    @FXML
    private void onAddBlueAgent() {
        nbBlueAgents++;

        blueAgentGrid.add(GeneratePlayerField.generateField("Blue Agent " + nbBlueAgents, this, 0), 0, nbBlueAgents-1);

        GridPane.setRowIndex(addBlueAgent, nbBlueAgents);
        GridPane.setRowIndex(setBlueAgentIA, nbBlueAgents);
        setBlueAgentIA.setVisible(false);

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
        GridPane.setRowIndex(setBlueSpyIA, nbBlueSpy);
        setBlueSpyIA.setVisible(false);

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
        GridPane.setRowIndex(setRedAgentIA, nbRedAgents);
        setRedAgentIA.setVisible(true);

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
        GridPane.setRowIndex(setRedSpyIA, nbRedSpy);
        setRedSpyIA.setVisible(false);

        if (nbRedSpy >= 3) {
            addRedSpy.setVisible(false);
        }
    }

    @FXML
    private void onSetBlueSpyIA() {
        session.getConfig().blueSpyIA = !session.getConfig().blueSpyIA;
        addBlueSpy.setVisible(!session.getConfig().blueSpyIA);
    }

    @FXML
    private void onSetRedSpyIA() {
        session.getConfig().redSpyIA = !session.getConfig().redSpyIA;
        addRedSpy.setVisible(!session.getConfig().redSpyIA);
    }

    @FXML
    private void onSetBlueAgentIA() {
        session.getConfig().blueAgentIA = !session.getConfig().blueAgentIA;
        addBlueAgent.setVisible(!session.getConfig().blueAgentIA);
    }

    @FXML
    private void onSetRedAgentIA() {
        session.getConfig().redAgentIA = !session.getConfig().redAgentIA;
        addRedAgent.setVisible(!session.getConfig().redAgentIA);
    }
    
    public void removePlayerField(Node playerField, int playerType) {
        switch (playerType) {
            case 0 -> {   
                blueAgentGrid.getChildren().remove(playerField);
                nbBlueAgents--;
                addBlueAgent.setVisible(true);
                if (nbBlueAgents == 1) setBlueAgentIA.setVisible(true);
                GridPane.setRowIndex(addBlueAgent, nbBlueAgents);
                GridPane.setRowIndex(setBlueAgentIA, nbBlueAgents);
            }
            case 1 -> {
                blueSpyGrid.getChildren().remove(playerField);
                nbBlueSpy--;
                addBlueSpy.setVisible(true);
                if (nbBlueSpy == 1) setBlueSpyIA.setVisible(true);
                if (blueSpyGrid.getChildren().size() > 2) {
                    GridPane.setRowIndex(blueSpyGrid.getChildren().get(2), nbBlueSpy-1);
                }
                GridPane.setRowIndex(addBlueSpy, nbBlueSpy);
                GridPane.setRowIndex(setBlueSpyIA, nbBlueSpy);
            }
            case 2 -> {
                redAgentGrid.getChildren().remove(playerField);
                nbRedAgents--;
                addRedAgent.setVisible(true);
                if (nbRedAgents == 1) setRedAgentIA.setVisible(true);
                GridPane.setRowIndex(addRedAgent, nbRedAgents);
                GridPane.setRowIndex(setRedAgentIA, nbRedAgents);
            }
            case 3 -> {
                redSpyGrid.getChildren().remove(playerField);
                nbRedSpy--;
                addRedSpy.setVisible(true);
                if (nbRedSpy == 1) setRedSpyIA.setVisible(true);
                if (redSpyGrid.getChildren().size() > 2) {
                    GridPane.setRowIndex(redSpyGrid.getChildren().get(2), nbRedSpy-1);
                }
                GridPane.setRowIndex(addRedSpy, nbRedSpy);
                GridPane.setRowIndex(setRedSpyIA, nbRedSpy);
            }
            default -> {}
        }
    }

    public void onGenTheme() {
        ArrayList<String> words = themeField.getWords();
        if (words.isEmpty()) {
            return;
        }
        ArrayList<String> dico = Utility.getDicoTheme(words);
        wordsList = dico;

        nbWordsLabel.setText("" + dico.size() + " mots, il en faut " + session.getConfig().heigth * session.getConfig().width);
        if (dico.size() >= session.getConfig().heigth * session.getConfig().width) {
            enableStart();
        } else {
            disableStart();
        }
    }
    public void onWord(){
        // Paramètres :
        String project_file_path = "src/main/resources/script/PythonDictionaryManager/";
        String word = addWord.getText();
        String image_save_path = project_file_path + "../../images";
        String codenamesWords_path = project_file_path + "../../words/codenames_words.txt";
        String lexicalField_path = project_file_path + "../../words/lexical_field.txt";
        String[] args = {word, image_save_path, codenamesWords_path, lexicalField_path};
        // Test DicoManager.main
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("Running on Windows");
            try {
                DicoManager.manage(args);
            } catch (Exception e){
                e.getStackTrace();
            }
        }
        /*
        } else if (os.contains("mac")) {
            executablePath = project_file_path + "dist/Script_DictionaryManagerMac";
            System.out.println("Running on macOS");

        } else if (os.contains("nix") || os.contains("nux")) {
            executablePath = project_file_path + "dist/Script_DictionaryManagerLinux";
            System.out.println("Running on Linux/Unix");

        } else {
            throw new Exception("Unknown OS");
            */
    }
        
    
    private void disableStart() {
        startButton.setDisable(true);
        startEnable = false;
    }
    private void enableStart() {
        startButton.setDisable(false);
        startEnable = true;
    }

    public void setMaxBoardSize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory rowFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) nbRows.getValueFactory();
        SpinnerValueFactory.IntegerSpinnerValueFactory colFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) nbCols.getValueFactory();
        if (session.getConfig().getImageMode()) {
            rowFactory.setMax(5);
            colFactory.setMax(5);
        }
        else {
            rowFactory.setMax(10);
            colFactory.setMax(10);
        }
    }

    public void checkTimers() {
        if (session.getConfig().getTimer()) {
            agentTimerDesc.setVisible(true);
            agentTimer.setVisible(true);
            spyTimer.setVisible(true);
            agentTimerLabel.setVisible(true);
            spyTimerLabel.setVisible(true);
            spyTimerDesc.setVisible(true);
        } else {
            agentTimerDesc.setVisible(false);
            agentTimer.setVisible(false);
            spyTimer.setVisible(false);
            agentTimerLabel.setVisible(false);
            spyTimerLabel.setVisible(false);
            spyTimerDesc.setVisible(false);
        }
    }

}
