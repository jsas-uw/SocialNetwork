
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class BuildRightPane {

	RoundButton roundButton = new RoundButton();
	List<Button> list = new ArrayList<Button>();
	ScrollPane rightPane = new ScrollPane(null);

	public ScrollPane getPane(List<Button> _allotherusers) {

		// this.list = _allotherusers;

		for (int u = 0 ; u < 100 ; u++){
			roundButton = new RoundButton("u"+u);
			roundButton.setId("u"+u);
			roundButton.textProperty().set(roundButton.getId());
			/*roundButton.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;"
			);*/
			roundButton.setOnAction(Main.ListenerClass.userButtonClickEvent());
			list.add(roundButton);

    		// Create a flow pane for the buttons
			FlowPane flow = new FlowPane();
			flow.setVgap(8);
     		flow.setHgap(4);
			HBox uHBox = new HBox();
			uHBox.getChildren().add(new Label("All Other Users"));
			flow.getChildren().add(uHBox);
			for (Button btn : list){
				flow.getChildren().add(btn);
			}
    		rightPane = new ScrollPane(flow);
			rightPane.setFitToWidth(true);

	
	}
    return rightPane;
}

}
