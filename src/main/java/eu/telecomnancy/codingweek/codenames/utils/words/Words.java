package eu.telecomnancy.codingweek.codenames.utils.words;

import eu.telecomnancy.codingweek.codenames.model.Color;

public class Words {
    private String name;
    private Color color;
    public Words(String name){
        this.name = name;
        this.color = null;
    }
    public String getName(){
        return name;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public Color getColor(){
        return color;
    }
}
