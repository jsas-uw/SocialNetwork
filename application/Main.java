/**
 * 
 */
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
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

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "CS400 Social Network";

	static Label hiddenLabel = new Label("CS400");
	static Label menuUserParameter = new Label(""); // this is set by event handler for key presses in the menu
	static Label menuFileParameter = new Label("");
	static Button initButton = new RoundButton(hiddenLabel.getText());
	static BuildCenterPane centerPane;
	static BuildRightPane rightPane;
	
	// Main layout is Border Pane example (top,left,center,right,bottom)
	static BorderPane root = new BorderPane();
	//static ScrollPane rightPane = new ScrollPane();

	public SocialNetMenu menuBar = new SocialNetMenu();
	
	public static SocialNetworkManager netManager = new SocialNetworkManager();
	//netManager was throwing an error, making it static fixed that
	
	// an inner class to handle the menu actions and rounded pushbutton actions
	public static class ListenerClass {

		//Handling the key typed event 
		static EventHandler<KeyEvent> eventHandlerTextFieldKeyReleased = new EventHandler<KeyEvent>() { 
			@Override 
			public void handle(KeyEvent event) { 
				if (((TextField)event.getSource()).getId() == "USERPARAMETER"){
					menuUserParameter.setText(((TextField)event.getSource()).getText());
					String msg = "The Value of the Target User Parameter: " + menuUserParameter.getText();
					BuildBottomPane buildBottomPane = new BuildBottomPane();
					Main.root.setBottom(buildBottomPane.getBottom(msg));
					// comment in the next two lines if you want to see this working
					//Alert alertTxtFieldChanged = new Alert(AlertType.INFORMATION, menuUserParameter.getText(), ButtonType.OK);
					//alertTxtFieldChanged.showAndWait();		
				} 
				if (((TextField)event.getSource()).getId() == "FILEPARAMETER"){
					menuFileParameter.setText(((TextField)event.getSource()).getText());
					String msg = "The Value of the Target File Parameter: " + menuFileParameter.getText();
					BuildBottomPane buildBottomPane = new BuildBottomPane();
					Main.root.setBottom(buildBottomPane.getBottom(msg));	
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
						
						BuildBottomPane buildBottomPane = new BuildBottomPane();
						Main.root.setRight(rightPane.getPane(netManager.getAllPeople(), CmdEnum.USERSHOWALL.toString()));
						Main.root.setBottom(buildBottomPane.getBottom("Showing all network users"));
					}
					
					if (((MenuItem)t.getSource()).getId().equals(CmdEnum.LOADFILE.toString())){
						// Calls into SocialNetworkManager netManager
						try {
							netManager.loadFile(menuFileParameter.getText());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						BuildBottomPane buildBottomPane = new BuildBottomPane();
						Main.root.setBottom(buildBottomPane.getBottom("Loaded file: " + menuFileParameter.getText()));
					} //end LOADFILE
					
				}
			};
		} 

		public static EventHandler<ActionEvent> userButtonClickEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent rb) {
					Button tempButton = (Button)rb.getTarget();
					hiddenLabel.setText(tempButton.getId());
					initButton.setText(tempButton.getText());
					initButton.setId(tempButton.getId());
					//refresh the center pane
					//In BuildCenterPane get pane handles the buttons
					//so all we need to do here is pass in the list of friends
					Main.root.setRight(centerPane.getPane(netManager.getFriends(initButton.getText())));
				}
			};
		};
	} //listener class
		
	/**
	 * Implements the "s" command from the input file
	 * @param user
	 */
	public void setUserButton(String user, List<String> friends) {
		Button tempButton = new Button();
		hiddenLabel.setText(tempButton.getId()); //debug statement?
		initButton.setText(user);
		initButton.setId(tempButton.getId());
		// build/refresh the center pane
		BuildCenterPane buildCenterPane = new BuildCenterPane();
		
		//debug loop
		System.out.println("Set user friend list");
		for(String f: friends) {
			System.out.print(f+", ");
		}
		System.out.println(); //end debug statements
		Main.root.setRight(buildCenterPane.getPane(friends));
		this.buildLeft();
	}
	
	/**
	 * As always this is quick and dirty
	 * could refactor so the vBox and scroll pane
	 * are class variables so you don't need to create
	 * them every time
	 */
	public void buildLeft() {
		// Create a vertical box 
    	VBox vbox = new VBox();
    	vbox.getChildren().add(initButton);
    	ScrollPane leftPane = new ScrollPane(vbox);	
    	root.setLeft(leftPane);
    	
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
		
        //initializes BuildCenterPane
        centerPane = new BuildCenterPane();
        rightPane = new BuildRightPane();
    	this.buildLeft();
    	root.setTop(this.menuBar.getMenu());
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