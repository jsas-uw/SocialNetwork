package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;


public class RoundButton extends Button {
    public RoundButton() {
        super();
        Tooltip tool=new Tooltip();   
        tool.setText("tbd");  
        this.setTooltip(tool);
        this.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;" +
                    "-fx-background-insets: 0,5,5;" +
                    "-fx-font-weight: bold;"
			);  
    }
    public RoundButton(String s) {
        super(s);  
        Tooltip tool=new Tooltip();   
        tool.setText(s);  
        this.setTooltip(tool);
        
        this.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;" +
                    "-fx-background-insets: 0,5,5;" +
                    "-fx-font-weight: bold;" 
			);  
    }
}
