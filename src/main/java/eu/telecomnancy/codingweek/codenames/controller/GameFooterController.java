package eu.telecomnancy.codingweek.codenames.controller;

import java.io.File;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.AutoCompleteTextField;
import eu.telecomnancy.codingweek.codenames.utils.SaveFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class GameFooterController {
    @FXML
    private HBox footerComp;
    @FXML
    private AutoCompleteTextField word;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Label indication;

    private final Session session;
    private final GameController controller;

    public GameFooterController(GameController controller, Session session){
        this.session = session;
        this.controller = controller;
    }

    @FXML
    private void initialize(){
        if (session.isAgent()) {
            word.setEntries(session.getBoard().getFullWordList());
        }
        else {
            var clue = session.getCurrentColoredTeam().getCluesList().getLast();
            indication.setText(clue.getWord() + " en " + clue.getCount());
        }
        initializeEvents();
    }
    private void initializeEvents() {
        footerComp.setOnKeyPressed((keyevent) ->  {
            switch (keyevent.getCode()) {
                case KeyCode.P -> onSubmit();
                case KeyCode.S -> onSave();
                default -> {}
            }
        });
        if (session.isAgent()) {
            word.textProperty().addListener(( (value) -> {
                onWordChanged();
            } ));
        }
    }

    @FXML
    private void onQuit(){
        controller.onQuit();
    }
    @FXML
    private void onSubmit() {
        controller.onSubmit();
        if (session.isAgent()){
            if (!session.getBoard().getFullWordList().contains(word.getText())) {
                indication.setText("Mon introuvable dans le dictionnaire");
            }
            else if (session.getBoard().getWordList().contains(word.getText())) {
                indication.setText("Mot dans la grille");
            }
            else {
                session.addClue(new Clue(word.getText(), spinner.getValue()));  
            }
        }
        else {
            session.guessCard(null);
        }
    }

    private void onWordChanged() {
        if (session.isAgent()) {
            indication.setText("");
        }
    }

    @FXML
    private void onSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder la partie");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier JSON", "*.json"));
        fileChooser.setInitialFileName("save.json");
        File file = fileChooser.showSaveDialog(footerComp.getScene().getWindow());
        if (file != null) {
            SaveFile.saveData(file.getAbsolutePath(), session);
        }
    }
}
