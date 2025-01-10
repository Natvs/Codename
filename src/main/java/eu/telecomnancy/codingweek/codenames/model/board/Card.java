package eu.telecomnancy.codingweek.codenames.model.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.observers.board.CardColorObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardNameObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardRevealedObserver;


public class Card {
    
    private String name;
    private Color color;
    private boolean revealed;

    private CardColorObserver colorObserver = null;
    private CardNameObserver nameObserver = null;
    private CardRevealedObserver revealedObserver = null;

    private String imageURL;

    public Card(String name, Color color) {
        setName(name);
        setColor(color);
        setURL();
        hide();
    }
    public Card(@JsonProperty("name") String name,@JsonProperty("color") Color color, @JsonProperty("revelead") boolean revealed){
        this.name = name;
        this.color = color;
        this.revealed = revealed;
    }

    private void setURL() {
        String imagePath = "/images/" + this.name;
        try {
            String imagePathPNG = imagePath + ".png";
            this.imageURL = getClass().getResource(imagePathPNG).toString();
        } catch (Exception e) {
            try {
            String imagePathJPEG = imagePath + ".jpeg";
            this.imageURL = getClass().getResource(imagePathJPEG).toString();
            } catch (Exception e2) {
            try {
                String imagePathJPG = imagePath + ".jpg";
                this.imageURL = getClass().getResource(imagePathJPG).toString();
            } catch (Exception e3) {
                try {
                String imagePathGIF = imagePath + ".gif";
                this.imageURL = getClass().getResource(imagePathGIF).toString();
                } catch (Exception e4) {
                try {
                    String imagePathBMP = imagePath + ".bmp";
                    this.imageURL = getClass().getResource(imagePathBMP).toString();
                } catch (Exception e5) {
                    throw new RuntimeException("Could not find image for card " + this.name);
                }
                }
            }
            }
        }
    }

    public String getName() {
        return this.name;
    }
    public Color getColor() {
        return this.color;
    }
    public Boolean getRevealed() {
        return this.revealed;
    }

    public void setName(String name) {
        this.name = name;
        if (nameObserver != null) {
            nameObserver.handle();
        }
    }
    public void setColor(Color color) {
        this.color = color;
        if (colorObserver != null) {
            colorObserver.handle();
        }
    }
    public void reveal() {
        if (!this.revealed) {
            this.revealed = true;
            if (revealedObserver != null) {
                revealedObserver.handle();
            }
        }
    }
    public void hide() {
        if (this.revealed) {
            this.revealed = false;
        }
    }

    public void setColorObserver(CardColorObserver observer) {
        this.colorObserver = observer;
    }
    public void setNameObserver(CardNameObserver observer) {
        this.nameObserver = observer;
    }
    public void setRevealedObserver(CardRevealedObserver observer) {
        this.revealedObserver = observer;
    }
    public String getImageURL() {
        return this.imageURL;
    }

}
