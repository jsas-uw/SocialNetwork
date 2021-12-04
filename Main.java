/**
 *
 */
package application;

import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 800;
	private static final String APP_TITLE = "The Social Network";
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		List<Button> list = new ArrayList<Button>();
		List<Button> friends = new ArrayList<Button>();
		Button roundButton = new Button();
		Button initButton = new Button("BS");
		Button secondaryButton = new Button("B2");

        initButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 75px; " +
                "-fx-min-height: 75px; " +
                "-fx-max-width: 75px; " +
                "-fx-max-height: 75px;"
        );
        
        
        /*
         * quick and dirty
         * I just copied all the code 
         * from init button to make 
         * secondary button look the same
         * should probably be refactored into
         * a method....
         */
        secondaryButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 75px; " +
                "-fx-min-height: 75px; " +
                "-fx-max-width: 75px; " +
                "-fx-max-height: 75px;"
        );


		for (int u = 0 ; u < 100 ; u++){
		roundButton = new Button();
		roundButton.textProperty().set("u"+u);
        roundButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 75px; " +
                "-fx-min-height: 75px; " +
                "-fx-max-width: 75px; " +
                "-fx-max-height: 75px;"
        );
		list.add(roundButton);
		}

		for (int f = 0 ; f < 10 ; f++){
			roundButton = new Button();
			roundButton.textProperty().set("f"+f);
			roundButton.setStyle(
					"-fx-background-radius: 5em; " +
					"-fx-min-width: 75px; " +
					"-fx-min-height: 75px; " +
					"-fx-max-width: 75px; " +
					"-fx-max-height: 75px;"
			);
			friends.add(roundButton);
		}

		/*
		 * We need to support multiselect
		 * I'm thinking the left pane should 
		 * have a secondary user
		 * then left click to select primary,
		 * right click to select secondary
		 * search user should display a message in the status bar
		 * Add user shouldn't clear the field
		 * The select user/make secondary user probably should
		 * clear the text field  
		 */
		
		//Left pane
			// vertical box to display the selected and secondary users
        	VBox leftvbox = new VBox();
        	Label primaryLabel = new Label("Selected User");
        	leftvbox.getChildren().add(primaryLabel);
        	leftvbox.getChildren().add(initButton);
        	Label secondaryLabel = new Label("Secondary User");
        	leftvbox.getChildren().add(secondaryLabel);
        	leftvbox.getChildren().add(secondaryButton);
        	

		// Right: Create a flow pane of buttons for all users
			FlowPane flow = new FlowPane();
			flow.setVgap(8);
     		flow.setHgap(4);
     		for (Button btn : list){
				flow.getChildren().add(btn);
			}
     		
        	//Labels were throwing off the button alignment
        	//Instead of adding the label to the flow pane
        	//I made a V box where level 1: label
        	//and level 2: flow pane
			VBox uVBox = new VBox();
			uVBox.getChildren().add(new Label("All Users"));
			uVBox.getChildren().add(flow);

		// Center: Create a flow pane of buttons for the selected users friends
			FlowPane friendsFlow = new FlowPane();
			friendsFlow.setVgap(8);
			friendsFlow.setHgap(4);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
			
			VBox fVBox = new VBox();
			fVBox.getChildren().add(new Label("Current Friends"));
			fVBox.getChildren().add(friendsFlow);
			
		// Top:
		// Loosely speaking this is going to be an array
		// Base is a vbox with an hbox for each rows.
		// I resized the window, Should be big enough to fit
		// the friend search and actions on a single line
			VBox topVBox = new VBox();
			HBox top1HBox = new HBox();
			HBox top2HBox = new HBox();
			HBox top3HBox = new HBox();
			HBox top3HBoxLabel = new HBox();
			topVBox.getChildren().add(top1HBox);
			topVBox.getChildren().add(top2HBox);
			topVBox.getChildren().add(top3HBoxLabel);
			topVBox.getChildren().add(top3HBox);
			
		//top line 1: Load, Save and Setup
			//import
			top1HBox.getChildren().add(new Button("Import file"));
			TextField importField = new TextField();
			top1HBox.getChildren().add(importField);
			
			//save
			top1HBox.getChildren().add(new Button("Save file"));
			TextField saveField = new TextField();
			top1HBox.getChildren().add(saveField);
			//clear
			//needs to remove all vertices and connections
			top1HBox.getChildren().add(new Button("Clear Current Nework"));
			top1HBox.getChildren().add(new Button("Show All Users"));
			
		//top line 2: Friend Search and related functions
			top2HBox.getChildren().add(new Button("Search User"));
			TextField friendField = new TextField();
			top2HBox.getChildren().add(friendField);
			top2HBox.getChildren().add(new Button("Add User"));
			top2HBox.getChildren().add(new Button("Select User"));
			top2HBox.getChildren().add(new Button("Make Secondary User"));
			
		//top line 3 label: help text for line three
			Label line3Helptext = new Label("The following buttons act on the seclected and secondary users: ");
			top3HBoxLabel.getChildren().add(line3Helptext);
			
		//top line 3: help text for line three
			top3HBox.getChildren().add(new Button("Add Friend"));
			top3HBox.getChildren().add(new Button("Remove Friend"));
			top3HBox.getChildren().add(new Button("Find Shared Friends"));
			top3HBox.getChildren().add(new Button("Find Shortest Path Between Users"));
			
						
		//Bottom	
			VBox bottomVBox = new VBox();
			HBox statusHBox = new HBox();
			
			//Status message
			String statusMsg = "Status message: \nLeft click to set main user, \nRight click to set secondary";
			/*
			 * ^eventually this should be a method
			 * so everytime we refresh the scene
			 * we call getStatusMsg to get the string
			 * to set the label text
			 */
			
			Label statusLabel = new Label(statusMsg);
			
			//Stats
			
			int users = 100; //graph.order
			int connections = 237; //graph.size
			String userMsg = "Total Users: " + users;
			String connectionsMsg = "\tTotal Connections: " + connections;
			Label userLabel = new Label(userMsg);
			Label connectionLabel = new Label(connectionsMsg);
			statusHBox.getChildren().add(userLabel);
			statusHBox.getChildren().add(connectionLabel);
			bottomVBox.getChildren().add(statusLabel);
			bottomVBox.getChildren().add(statusHBox);
			
		// Main layout is Border Pane example (top,left,center,right,bottom)
        	BorderPane root = new BorderPane();


		// Add the vertical box to the center of the root pane
			root.setTop(topVBox);
			ScrollPane scrollPane = new ScrollPane(uVBox);
			ScrollPane fScrollPane = new ScrollPane(fVBox);
			scrollPane.setFitToWidth(true);
			fScrollPane.setFitToWidth(true);
        	root.setLeft(leftvbox);
			root.setCenter(fScrollPane);
			root.setRight(scrollPane);
			root.setBottom(bottomVBox);

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
        	primaryStage.setTitle(APP_TITLE);
        	primaryStage.setScene(mainScene);
        	primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   launch(args);
	}
}
