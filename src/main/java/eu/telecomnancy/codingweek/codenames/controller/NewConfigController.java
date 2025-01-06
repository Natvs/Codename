package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;

public class NewConfigController {
    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }
}
