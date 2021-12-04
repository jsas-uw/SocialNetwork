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
	private static final String APP_TITLE = "Hello JavaFX World!";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		List<Button> list = new ArrayList<Button>();
		List<Button> friends = new ArrayList<Button>();
		Button roundButton = new Button();
		Button initButton = new Button("BS");

        initButton.setStyle(
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


		// Create a vertical box
        	VBox vbox = new VBox();
        	vbox.getChildren().add(initButton);

        //added another HBox so the labels don't
        //throw off the button alignment
        	

        	
		// Create a flow pane for the buttons
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

		// Create a flow pane for the buttons
			FlowPane friendsFlow = new FlowPane();
			friendsFlow.setVgap(8);
			friendsFlow.setHgap(4);
			for (Button f : friends){
				friendsFlow.getChildren().add(f);
			}
			
			VBox fVBox = new VBox();
			fVBox.getChildren().add(new Label("Current Friends"));
			fVBox.getChildren().add(friendsFlow);
			


		// Main layout is Border Pane example (top,left,center,right,bottom)
        	BorderPane root = new BorderPane();


		// Add the vertical box to the center of the root pane
			root.setTop(new Label("This is the root.setTop"));
			ScrollPane scrollPane = new ScrollPane(uVBox);
			ScrollPane fScrollPane = new ScrollPane(fVBox);
			scrollPane.setFitToWidth(true);
			fScrollPane.setFitToWidth(true);
        	root.setLeft(vbox);
			root.setCenter(fScrollPane);
			root.setRight(scrollPane);
			root.setBottom(new Label("This is the root.setBottom"));

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
