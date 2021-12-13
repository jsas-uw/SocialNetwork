

package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class BuildRightPane {

	RoundButton roundButton = new RoundButton();
	List<Button> list = new ArrayList<Button>();
	ScrollPane rightPane = new ScrollPane(null);

	public ScrollPane getPane(List<String> _allotherusers) {

		if (_allotherusers!=null){
			
			for (String usr : _allotherusers){
			list.add(UserButtonFactory.getRoundButton(usr));
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION, "oops List<String> was null", ButtonType.OK);
			alert.showAndWait();
		}


    		// Create a flow pane for the buttons
			FlowPane flow = new FlowPane();
			flow.setVgap(8);
     		flow.setHgap(4);
			HBox uHBox = new HBox();
			uHBox.getChildren().add(new Label("All Users"));
			flow.getChildren().add(uHBox);
			for (Button btn : list){
				flow.getChildren().add(btn);
			}
    		rightPane = new ScrollPane(flow);
			rightPane.setFitToWidth(true);

	
	
    return rightPane;
}

}
