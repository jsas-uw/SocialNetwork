
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class BuildCenterPane {

	RoundButton roundButton = new RoundButton();
	List<Button> friends = new ArrayList<Button>();
	ScrollPane centerPane = new ScrollPane(null);

	/*
	 * refactored to use a VBox,
	 * Label is on top 
	 * scroll pane is under
	 * (repeat for flow pane)
	 */
	
	//could add option as a parameter
	//so USERSHOWALL would call it with 
	//Label = "All Users"
	
	//public ScrollPane getPane(List<Button> _friends, String Label) {
	//then replace the hard coded strings with the label variable.
	
	public ScrollPane getPane(List<Button> _friends) {

		// this.friends = _friends;

    	for (int f = 0 ; f < 10 ; f++){
			roundButton = new RoundButton("friend"+f);
			roundButton.setId("friend"+f);
			roundButton.textProperty().set(roundButton.getId());
			roundButton.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;"
			);
			roundButton.setOnAction(Main.ListenerClass.userButtonClickEvent());
			friends.add(roundButton);
			}

		// Create a flow pane for the buttons
		if (friends.size()<=10){
			CircularPane friendsFlow = new CircularPane();
			VBox fVBox = new VBox();
			fVBox.getChildren().add(new Label("Current Friends"));
			
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
			StackPane stackPane = new StackPane(friendsFlow, new Label(Main.hiddenLabel.getText()));
            StackPane.setAlignment(friendsFlow, Pos.CENTER);
			ScrollPane fScrollPane = new ScrollPane(stackPane);
			fScrollPane.setFitToWidth(true);
			
			fVBox.getChildren().add(fScrollPane);
			Main.root.setCenter(fVBox);
		}
		else {
			FlowPane friendsFlow = new FlowPane();
			friendsFlow.setVgap(8);
			friendsFlow.setHgap(4);
			VBox fVBox = new VBox();
			fVBox.getChildren().add(new Label("Current Friends"));
			friendsFlow.getChildren().add(fVBox);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
			ScrollPane fScrollPane = new ScrollPane(friendsFlow);
			fScrollPane.setFitToWidth(true);
			Main.root.setCenter(fScrollPane);
		}

	    return centerPane;
	}

}
