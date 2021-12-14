package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Filename:   BuildCenterPane.java
 * Project:    Social Network - A3 Deliverable
 * Authors:    A-Team 11
 *
 * Builds the center pane in the app layout
 */

public class BuildCenterPane {

	RoundButton roundButton = new RoundButton();
	List<Button> friends = new ArrayList<Button>();
	ScrollPane centerPane = new ScrollPane(null);
	String test = CmdEnum.SHORTESTPATH.toString();

	/**
 	* method to return a scroll pane based on a list, cmd type, and label
	*
 	* @param _friends
	* @param _cmd
	* @param centerlabel
	* @return ScrollPane
 	*/
	public ScrollPane getPane(List<String> _friends, String _cmd, String centerlabel) {
		String toplabel = "Friends";
		if ((centerlabel == null) || (centerlabel == "")) {centerlabel = "Current User";}

		switch(_cmd) {
			case "CONNECTIONADD" :
				toplabel="Current Friends";
			break;
			case "CONNECTIONREMOVE" :
				toplabel="Current Friends";
			  break;
			case "SHAREDFRIENDS" : 
			  	toplabel="Shared Friends";
			  break;
			case "SHORTESTPATH" :
			  toplabel="Shortest Path" + centerlabel;
		  	break;
			default:
			  toplabel="Current Friends";
			break;
		  }


		if (_friends==null){
			Alert alert = new Alert(AlertType.INFORMATION, "The users are not connected.", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			if (_friends.size()>0){
				for (String frnd : _friends){
					friends.add(UserButtonFactory.getRoundButton(frnd));
				}
			}
		}
    	
	if (!_cmd.equals(CmdEnum.SHORTESTPATH.toString())){
		// Create a flow pane for the buttons
		if (friends.size()<=10){
			CircularPane friendsFlow = new CircularPane();
			HBox fHBox = new HBox();
			fHBox.getChildren().add(new Label(toplabel));
			friendsFlow.getChildren().add(fHBox);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
						
			StackPane stackPane = new StackPane(friendsFlow, new Label(centerlabel));
            StackPane.setAlignment(friendsFlow, Pos.CENTER);
			ScrollPane fScrollPane = new ScrollPane(stackPane);
			fScrollPane.setFitToWidth(true);
			Main.root.setCenter(fScrollPane);
		}
		else {
			FlowPane friendsFlow = new FlowPane();
			friendsFlow.setVgap(8);
			friendsFlow.setHgap(4);
			HBox fHBox = new HBox();
			fHBox.getChildren().add(new Label(toplabel));
			friendsFlow.getChildren().add(fHBox);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
			ScrollPane fScrollPane = new ScrollPane(friendsFlow);
			fScrollPane.setFitToWidth(true);
			Main.root.setCenter(fScrollPane);
		}
	}
	else {
		FlowPane friendsFlow = new FlowPane();
			friendsFlow.setVgap(8);
			friendsFlow.setHgap(4);
			
			HBox fHBox = new HBox();
			fHBox.getChildren().add(new Label(toplabel + " "));
			friendsFlow.getChildren().add(fHBox);
			int i = 0;
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
				if (i < friends.size()-1){
					Line line = new Line(0,0,35,0);
					line.setStrokeWidth(6.0);
      				friendsFlow.getChildren().add(line);
					i++;
				}
			}
			ScrollPane fScrollPane = new ScrollPane(friendsFlow);
			fScrollPane.setFitToWidth(true);
			Main.root.setCenter(fScrollPane);

	}
	
    return centerPane;
}

}
