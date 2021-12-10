
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
	
	/**
	 * Adds more buttons to friends if needed,
	 * otherwise it just updates the label text
	 * 
	 * @param _friendsString (adjacency list of friends)
	 * @return ScrollPane
	 */
	public ScrollPane getPane(List<String> _friendsString) {

		// this.friends = _friends;
		
		//check size of button array
		//if less than friends initialize more buttons
		for (int i = friends.size(); i < _friendsString.size(); i++) {
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
			friends.add(roundButton);
		}
		
		int j = 0; //counter to loop through friend buttons
    	for (String f:_friendsString){
    		friends.get(j).setText(f);
    		j++;
		}

    	/*
    	 * circle pane needs to be fixed
    	 * for some reason the fourth friend gets cutoff
    	 * its working correctly because you can set 
    	 * friend size to 1 and the flow pane shows all 
    	 * four friends...
    	 */
    	
		// Create a flow pane for the buttons
		if (friends.size()<=10){
			CircularPane friendsFlow = new CircularPane();
			VBox fVBox = new VBox();
			fVBox.getChildren().add(new Label("Current Friends"));
			System.out.println("\nButton Labels:");//debug
			for (Button f : friends){
				if (!f.getText().equals("")) {
					friendsFlow.getChildren().add(f);
					System.out.print(f.getText() + ", "); //debug
				}
			}
			StackPane stackPane = new StackPane(friendsFlow);
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
			fVBox.getChildren().add(friendsFlow);
			for (Button f : friends){
				if (!f.getText().equals(""))
					friendsFlow.getChildren().add(f);
			}
			ScrollPane fScrollPane = new ScrollPane(friendsFlow);
			fScrollPane.setFitToWidth(true);
			Main.root.setCenter(fScrollPane);
		}

	    return centerPane;
	}

}
