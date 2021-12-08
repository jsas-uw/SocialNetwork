package application;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

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
					"-fx-max-height: 75px;"
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
					"-fx-max-height: 75px;"
			);  
    }
}
