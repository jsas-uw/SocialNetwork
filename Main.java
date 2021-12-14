package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.MenuEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "CS400 Social Network !";

	static Label hiddenLabel = new Label("");
	static Label menuUserParameter = new Label(""); // this is set by event handler for key presses in the menu
	static Label menuFileParameter = new Label("");

	static RoundButton centralUserButton  = new RoundButton(hiddenLabel.getText()); // was previously initButton 
	static HashMap<String, RoundButton> ButtonHashMap = new HashMap<String, RoundButton>();
	static UserButtonFactory userButtonFactory = new UserButtonFactory();
	
	// Main layout is Border Pane example (top,left,center,right,bottom)
	static BorderPane root = new BorderPane();
	static ScrollPane rightPane = new ScrollPane();
	static ScrollPane leftPane = new ScrollPane();
	static ScrollPane centerPane = new ScrollPane();

	static BuildCenterPane buildCenterPane = new BuildCenterPane();
	static BuildRightPane buildRightPane = new BuildRightPane();
	static BuildBottomPane buildBottomPane = new BuildBottomPane();

	public SocialNetMenu menuBar = new SocialNetMenu();

	public static SocialNetworkManager NetManager = new SocialNetworkManager();
	//netManager was throwing an error, making it static fixed that

	// an inner class to handle the menu actions and rounded pushbutton actions
	public static class ListenerClass {

		public static void SetCentralUser(String ctrlUser){
			RoundButton tempButton = UserButtonFactory.getRoundButton(ctrlUser);
			hiddenLabel.setText(tempButton.getId());
			centralUserButton.setText(tempButton.getId());
			centralUserButton.setId(tempButton.getId());
			centralUserButton.setStyle(ButtonHashMap.get(tempButton.getId()).getStyle());
			Tooltip tool=new Tooltip();   
        	tool.setText(hiddenLabel.getText());  
        	centralUserButton.setTooltip(tool);
			centralUserButton.setVisible(true);
			centralUserButton.setOnAction(userButtonClickEvent());
			RefreshCenter();
		}
		
		public static void RefreshCenter(){
			root.setRight(null);
			root.setCenter(null);
			// build/refresh the center pane
			buildCenterPane = new BuildCenterPane();
			System.out.println(centralUserButton.getId());
			buildCenterPane.getPane(NetManager.getFriends(centralUserButton.getId()));
		}

		/*
		 * to make this extensible, pull userList
		 * out as a parameter, otherwise this 
		 * only works for show all users
		 */
		
		public static void RefreshRight(){
			root.setRight(null);
			root.setCenter(null);
			List<String> userList = new ArrayList<String>();
			userList.addAll(NetManager.getAllPeople());
			userList.remove(centralUserButton.getId());
			// Create a flow pane for the buttons
			buildRightPane = new BuildRightPane();
			root.setRight(buildRightPane.getPane(userList));
		}
		
		public static void RefreshBottom(String msg) {
			//updateStats
			buildBottomPane.updateStats(NetManager.getSize(), NetManager.getOrder());
			Main.root.setBottom(buildBottomPane.getBottom(msg));
		}


		//Handling the key typed event 
		static EventHandler<KeyEvent> eventHandlerTextFieldKeyReleased = new EventHandler<KeyEvent>() { 
			@Override 
			public void handle(KeyEvent event) { 
				if (((TextField)event.getSource()).getId() == "USERPARAMETER"){
					menuUserParameter.setText(((TextField)event.getSource()).getText());
					if (!validUserInput(menuUserParameter.getText())) {
						String allowedChars = ("Usernames can only be letters, numbers, apostrophe and underscore ");
						RefreshBottom(allowedChars);
						return;
					}
					String msg = "User text field input : " + menuUserParameter.getText();
					RefreshBottom(msg);
					
				}
				if (((TextField)event.getSource()).getId() == "FILEPARAMETER"){
					menuFileParameter.setText(((TextField)event.getSource()).getText());
					String msg = "The Value of the Target File Parameter: " + menuFileParameter.getText();
					RefreshBottom(msg);
				} 
			}           
		 };

		
		public static EventHandler<ActionEvent> txtFieldEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
	
					if (((TextField)t.getSource()).getId() == "USERPARAMETER"){
					menuUserParameter.setText(((TextField)t.getSource()).getText());
					String msg = "The Value of the Target User Parameter: " + menuUserParameter.getText();
					RefreshBottom(msg);
					}
				}
			};
		}

		public static EventHandler<ActionEvent> menuCmdEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					if (((MenuItem)t.getSource()).getId().equals(CmdEnum.LOADFILE.toString())){
						// Calls into SocialNetworkManager NetManager
						try {
							NetManager.loadFile(menuFileParameter.getText());
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						RefreshCenter();
						String msg = "Loaded file: " + menuFileParameter.getText();
						RefreshBottom(msg);
					}
					if (((MenuItem)t.getSource()).getId().equals(CmdEnum.SAVEFILE.toString())){
						// Calls into SocialNetworkManager NetManager
						try {
							NetManager.saveLog(menuFileParameter.getText());
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						String msg = "Saved to file: " + menuFileParameter.getText();
						RefreshBottom(msg);
					}
					if (((MenuItem)t.getSource()).getId() == CmdEnum.USERSHOWALL.toString()){
						RefreshRight();
						String msg = "Showing all users";
						RefreshBottom(msg);
					}
					
					//added validUserInput check to the conditional
					if (((MenuItem)t.getSource()).getId() == CmdEnum.USERADD.toString()){
						if (!menuUserParameter.getText().equals("") && validUserInput(menuUserParameter.getText())){
							NetManager.addUser(menuUserParameter.getText());
							RefreshRight();
							String msg = "Added user: " + menuUserParameter.getText();
							RefreshBottom(msg);
						}
						else
							RefreshBottom("could not add user " + menuUserParameter.getText());
					}
					if (((MenuItem)t.getSource()).getId() == CmdEnum.USERREMOVE.toString()){
						if (!menuUserParameter.getText().equals("") && validUserInput(menuUserParameter.getText())){
							NetManager.removeUser(menuUserParameter.getText());
							RefreshRight();
							String msg = "Removed user: " + menuUserParameter.getText();
							RefreshBottom(msg);
						}	
					}
					if (((MenuItem)t.getSource()).getId() == CmdEnum.CONNECTIONADD.toString()){
						if (!menuUserParameter.getText().equals("") && !centralUserButton.getId().equals("") && validUserInput(menuUserParameter.getText())){
							NetManager.addConnection(centralUserButton.getId(),menuUserParameter.getText());
							RefreshCenter();
							String msg = "Added connection between " + centralUserButton.getId()+ " and " + menuUserParameter.getText();
							RefreshBottom(msg);
						}
					}
					
					if (((MenuItem)t.getSource()).getId() == CmdEnum.CONNECTIONREMOVE.toString()){
						if (!menuUserParameter.getText().equals("") && !centralUserButton.getId().equals("") && validUserInput(menuUserParameter.getText())){
							NetManager.removeConnection(centralUserButton.getId(),menuUserParameter.getText());
							RefreshCenter();
							String msg = "Removed connection between " + centralUserButton.getId()+ " and " + menuUserParameter.getText();
							RefreshBottom(msg);
						}
					}
					
					/*
					 * implementation notes:
					 * The RefreshCenter/Right don't take parameters so they're not extensible
					 * Hopefully I'm interpreting this from yesterday's meeting correctly,
					 * the idea was to not mess with existing stuff so I'm not touching it,
					 * but I left a comment about extensibility 
					 * 
					 * So instead of calling refreshCenter/Right I added methods 
					 * to the Build class and call it that way,
					 * hence root.setCenter instead of refresh right
					 * (logically I'm thinking the goal was to handle everything in the refresh methods,
					 * but same difference I guess)
					 */
					if (((MenuItem)t.getSource()).getId() == CmdEnum.SHORTESTPATH.toString()) {
						if (!Main.validUserInput(menuUserParameter.getText())) {
							RefreshBottom("Path not found, invalid username");
						}
						if (centralUserButton.getId() == null) {
							RefreshBottom("Path not found, current user is null");
						}
						List<String> path = NetManager.getShortestPath(centralUserButton.getId(),menuUserParameter.getText());
						root.setRight(null);
						root.setCenter(null);
						root.setCenter(buildCenterPane.showPath(path));
						String msg = "Showing path between " + centralUserButton.getId()+ " and " + menuUserParameter.getText();
						RefreshBottom(msg);
						
					}
					
					if (((MenuItem)t.getSource()).getId() == CmdEnum.SHAREDFRIENDS.toString()) {
						if (!Main.validUserInput(menuUserParameter.getText())) {
							RefreshBottom("Can't list shared friends, invalid username");
						}
						if (centralUserButton.getId() == null) {
							RefreshBottom("Can't list shared friends, current user is null");
						}
						System.out.println("Shared Friends");
						List<String> sharedFriends = NetManager.mutualFriends(centralUserButton.getId(),menuUserParameter.getText());
						root.setRight(null);
						//root.setCenter(null);
						buildCenterPane.sharedFriends(sharedFriends);
						String msg = "Showing friends between " + centralUserButton.getId()+ " and " + menuUserParameter.getText();
						RefreshBottom(msg);
											
					}
				}
			};
		}

		public static EventHandler<ActionEvent> userButtonClickEvent() {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent rb) {
					Button tempButton = (Button)rb.getTarget();
					String user = tempButton.getId();
					SetCentralUser(user);
					NetManager.logCentralUser(user);
					RefreshCenter();
					String msg = "Set " + tempButton.getText() + " as current user";
					RefreshBottom(msg);
				}
			};
		};
		
	}
		
	/**
	 * program description has a list of allowed characters
	 * which are letters, numbers, apostrophe, and underscore 
	 * the regex checks if the input string has any characters
	 * outside this set
	 *    
	 * @param user input string
	 * @return boolean of whether its valid
	 */
	public static boolean validUserInput(String input) {
		if (input == null) {return false;}
		if (input.equals("")) {return false;}
		Pattern regex = Pattern.compile("[^a-zA-z0-9_']+");
		Matcher regexMatcher = regex.matcher(input);
		if (regexMatcher.find()) {
			return false;
		}
		
		return true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();
		
		
		// Create a vertical box 
        VBox vbox = new VBox();
		vbox.getChildren().add(new Label("Central User"));
        vbox.getChildren().add(centralUserButton);
		centralUserButton.setVisible(false);
        leftPane = new ScrollPane(vbox);	
		// Add the vertical box left pane
        root.setLeft(leftPane);
		

		// Add the vertical box to the center of the root pane
		root.setTop(this.menuBar.getMenu());
        
		root.setRight(null);
		Main.root.setBottom(buildBottomPane.getBottom("Started social network"));

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent close) {
                System.out.println("Exit pressed"); //debug
                
                //argument prepopulates the text field with a sample file
                TextInputDialog dialog = new TextInputDialog("log.txt");
                
                dialog.setHeaderText("Save log as a .txt file before exiting?");
                dialog.setContentText("Enter file path");
                
                //make new button types and map to ButtonData categories
                //essentially this just replaces the default dialog button labels
                ButtonType save = new ButtonType("Save", ButtonData.OK_DONE);
                ButtonType exit = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);
                //add new button types and remove default ones from the dialog pane
                dialog.getDialogPane().getButtonTypes().addAll(save, exit);
                dialog.getDialogPane().getButtonTypes().removeAll(ButtonType.CANCEL, ButtonType.OK);
                
                //wait for an file path
                //(should always be true since a default is provided) 
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                	/*
                	 * try catch is kind of redundant because you don't even need
                	 * the .txt file extension, it will save as a generic file 
                	 * unless you really mess with it and use "/" 
                	 * or other illegal characters for file names...  
                	 */
                    try {
						NetManager.saveLog(result.get());
						System.out.println("Command Log saved to: " + result.get());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                }
           
            }
        });  
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   launch(args);
	}
}