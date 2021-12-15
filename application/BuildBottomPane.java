package application;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BuildBottomPane {
	
	
	private int users;
	private int connections;
	
	/**
	 * Sets size and order from the graph
	 */
	public void updateStats(int size, int order) {
		users = order;
		connections = size;
	}
	
	/**
	 * Bottom pane is a VBox and has two parts: 
	 * First the status message is displayed to the user 
	 * then the overall network stats are displayed below it 
	 * 
	 * @param String statusMsg
	 * @return Vbox bottomVBox
	 */
	public VBox getBottom(String statusMsg) {
		//adds directly to the vbox
		Label statusLabel = new Label(statusMsg);
		
		//Stats (Ultimately this gets added as an HBox)
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
	

