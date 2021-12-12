package application;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BuildBottomPane {
	/**
	 * Bottom pane is a VBox and has two parts: 
	 * First the status message is displayed to the user 
	 * then the overall network stats are displayed below it 
	 * 
	 * @param String statusMsg
	 * @return Vbox bottomVBox
	 */
	
	//public void updateBottom(int users, int ) {
		

	
	public VBox getBottom(String statusMsg) {
		//adds directly to the vbox
		Label statusLabel = new Label(statusMsg);
		
		//Stats (Ultimately this gets added as an HBox)
		int users = 100; //graph.order
		int connections = 237; //graph.size
		String userMsg = "Total Users: " + users;
		String connectionsMsg = "\tTotal Connections: " + connections;
		Label userLabel = new Label(userMsg);
		Label connectionLabel = new Label(connectionsMsg);
		HBox statsHBox = new HBox();
		statsHBox.getChildren().add(userLabel);
		statsHBox.getChildren().add(connectionLabel);
		
		VBox bottomVBox = new VBox();
		bottomVBox.getChildren().add(statusLabel);
		bottomVBox.getChildren().add(statsHBox);
	
		return bottomVBox;
	}
		
}
	
