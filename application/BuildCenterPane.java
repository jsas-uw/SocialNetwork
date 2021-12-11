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

public class BuildCenterPane {

	RoundButton roundButton = new RoundButton();
	List<Button> friends = new ArrayList<Button>();
	ScrollPane centerPane = new ScrollPane(null);

	public ScrollPane getPane(List<String> _friends) {

		// this.friends = _friends;
		if (_friends!=null){
			
			for (String frnd : _friends){
			friends.add(UserButtonFactory.getRoundButton(frnd));
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION, "oops List<String> was null", ButtonType.OK);
			alert.showAndWait();
		}
    	
		// Create a flow pane for the buttons
		if (friends.size()<=10){
			CircularPane friendsFlow = new CircularPane();
			HBox fHBox = new HBox();
			fHBox.getChildren().add(new Label("Current Friends"));
			friendsFlow.getChildren().add(fHBox);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
						
			StackPane stackPane = new StackPane(friendsFlow, new Label(Main.hiddenLabel.getText()));
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
			fHBox.getChildren().add(new Label("Current Friends"));
			friendsFlow.getChildren().add(fHBox);
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
