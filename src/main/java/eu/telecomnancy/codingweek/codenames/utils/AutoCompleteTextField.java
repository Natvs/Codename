package eu.telecomnancy.codingweek.codenames.utils;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteTextField extends TextField {
    private final List<String> entries;
    private ContextMenu contextMenu;

    public AutoCompleteTextField() {
        super();
        entries = new LinkedList<>();
        contextMenu = new ContextMenu();
        
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (getText().length() == 0) {
                contextMenu.hide();
            } else {
                LinkedList<String> searchResult = new LinkedList<>();
                String[] words = getText().split(",");
                String lastWord = words[words.length - 1].trim();
                searchResult.addAll(entries.stream()
                        .filter(e -> e.toLowerCase().contains(lastWord.toLowerCase()))
                        .collect(Collectors.toList()));
                
                if (searchResult.size() > 0) {
                    populatePopup(searchResult);
                    if (!contextMenu.isShowing()) {
                        contextMenu.show(AutoCompleteTextField.this, Side.BOTTOM, 0, 0);
                    }
                } else {
                    contextMenu.hide();
                }
            }
        });
    }

    public void setEntries(List<String> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    public void removeEntries() {
        entries.clear();
    }

    private void populatePopup(List<String> searchResult) {
        List<MenuItem> menuItems = new LinkedList<>();
        for (String result : searchResult) {
            MenuItem item = new MenuItem(result);
            item.setOnAction(e -> {
                String[] words = getText().split(",");
                words[words.length - 1] = result;
                setText(String.join(", ", words));
                positionCaret(getText().length());
                contextMenu.hide();
            });
            menuItems.add(item);
        }
        contextMenu.getItems().clear();
        contextMenu.getItems().addAll(menuItems);
    }
}