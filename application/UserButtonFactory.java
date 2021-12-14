package application;

import java.util.HashMap;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Filename:   UserButtonFactory.java
 * Project:    Social Network - A3 Deliverable
 * Authors:    A-Team 11
 * 
 * Factory for creating a cache of buttons, as they are needed.
 * Buttons are maintained for later use in a Global HashMap.
 * Key methods used from the HashMap are .getOrDefault(s, newRoundButton(s)) and .putIfAbsent(s, roundButton) 
 * Saves on boiler plate
 */
public class UserButtonFactory {

    public UserButtonFactory(){
        
    }

    public static RoundButton getRoundButton(String s){
        RoundButton roundButton = Main.ButtonHashMap.getOrDefault(s, newRoundButton(s));
        return roundButton;
    }

    public static RoundButton newRoundButton(String s){
        RoundButton roundButton = new RoundButton(s);
		roundButton.setId(s);
		roundButton.textProperty().set(roundButton.getId());
        int r = (int) Math.round(Math.random()*255.0);
        int g = (int) Math.round(Math.random()*255.0);
        int b = (int) Math.round(Math.random()*255.0);
        String hex = String.format("#%02x%02x%02x;", r, g, b);
        roundButton.setStyle(
                    "-fx-background-color: " + hex + " " +
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;"
			);
		roundButton.setOnAction(Main.ListenerClass.userButtonClickEvent());
        Main.ButtonHashMap.putIfAbsent(s, roundButton);
        return roundButton;
    }
    
    
}
