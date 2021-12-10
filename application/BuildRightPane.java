
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox; 
//refactored to Vbox
import javafx.scene.layout.VBox;

public class BuildRightPane {

	RoundButton roundButton = new RoundButton();
	List<Button> list = new ArrayList<Button>();
	ScrollPane rightPane = new ScrollPane(null);
	
	/**
	 * Added a String for the calling button name as a parameter 
	 * 
	 * @param List<Button> users
	 * @param String Button
	 * @return ScrollPane
	 */
	public ScrollPane getPane(List<String> _allotherusers, String Button) {
		
		// this.list = _allotherusers;

		for (int i = list.size(); i < _allotherusers.size(); i++) {
			roundButton = new RoundButton("");
			roundButton.setId(""+i);
			roundButton.textProperty().set(roundButton.getId());
			roundButton.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;"
			);
			roundButton.setOnAction(Main.ListenerClass.userButtonClickEvent());
			list.add(roundButton);
		}
		
		int j = 0; //counter to loop through friend buttons
    	for (String u:_allotherusers){
    		list.get(j).setText(u);
    		j++;
		}
    	
		// Create a flow pane for the buttons
		FlowPane flow = new FlowPane();
		flow.setVgap(8);
		flow.setHgap(4);
		VBox uVBox = new VBox();
		uVBox.getChildren().add(new Label("All Other Users"));
		uVBox.getChildren().add(flow);
		for (Button btn : list){
			if (!btn.getText().equals(""))
				flow.getChildren().add(btn);
		}
		rightPane = new ScrollPane(uVBox);
		rightPane.setFitToWidth(true);
			
		return rightPane;
	}

}
