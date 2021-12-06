/**
 * 
 */
//package application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MenuEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "Hello CS400 Social Network World!";

	static Label hiddenLabel = new Label("CS400");
	static Label menuUserParameter = new Label(""); // this is set by event handler for key presses in the menu
	static Button initButton = new RoundButton(hiddenLabel.getText());
	
	// Main layout is Border Pane example (top,left,center,right,bottom)
	static BorderPane root = new BorderPane();
	static ScrollPane rightPane = new ScrollPane();

	public SocialNetMenu menuBar = new SocialNetMenu();
	
	// an inner class to handle the menu actions and rounded pushbutton actions
	public static class ListenerClass {

		//Handling the key typed event 
		static EventHandler<KeyEvent> eventHandlerTextFieldKeyReleased = new EventHandler<KeyEvent>() { 
			@Override 
			public void handle(KeyEvent event) { 
				if (((TextField)event.getSource()).getId() == "USERPARAMETER"){
					menuUserParameter.setText(((TextField)event.getSource()).getText());
					Main.root.setBottom(new Label("The Value of the Target User Parameter: " + menuUserParameter.getText()));
					// comment in the next two lines if you want to see this working
					//Alert alertTxtFieldChanged = new Alert(AlertType.INFORMATION, menuUserParameter.getText(), ButtonType.OK);
					//alertTxtFieldChanged.showAndWait();		
				} 
			}           
		 };

		public static EventHandler<ActionEvent> txtFieldEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
	
					if (((TextField)t.getSource()).getId() == "USERPARAMETER"){
						Alert alertTxtFieldChanged = new Alert(AlertType.INFORMATION, "user param changed", ButtonType.OK);
						alertTxtFieldChanged.showAndWait();
					}
				}
			};
		}

		public static EventHandler<ActionEvent> menuCmdEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					if (((MenuItem)t.getSource()).getId() == CmdEnum.USERSHOWALL.toString()){
						// Create a flow pane for the buttons
						BuildRightPane buildRightPane = new BuildRightPane();
						Main.root.setRight(buildRightPane.getPane(new ArrayList<Button>()));
					}
					
				}
			};
		}

		public static EventHandler<ActionEvent> userButtonClickEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent rb) {
					Button tempButton = (Button)rb.getTarget();
					hiddenLabel.setText(tempButton.getId());
					initButton.setText(tempButton.getId());
					initButton.setId(tempButton.getId());
					// build/refresh the center pane
					BuildCenterPane buildCenterPane = new BuildCenterPane();
					Main.root.setRight(buildCenterPane.getPane(new ArrayList<Button>()));
					}
				};
			};
		}
		
		

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();
		
		initButton.setId(hiddenLabel.getText());
		initButton.setOnAction(Main.ListenerClass.userButtonClickEvent());		
        initButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 75px; " +
                "-fx-min-height: 75px; " +
                "-fx-max-width: 75px; " +
                "-fx-max-height: 75px;"
        );		
		
			
			// Create a vertical box 
        	VBox vbox = new VBox();
        	vbox.getChildren().add(initButton);
        	ScrollPane leftPane = new ScrollPane(vbox);	

			// Add the vertical box to the center of the root pane
			root.setTop(this.menuBar.getMenu());
        	root.setLeft(leftPane);
			root.setRight(null);
			root.setBottom(new Label("This is the root.setBottom"));

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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