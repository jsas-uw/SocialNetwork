package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class SocialNetMenu {

    public HBox getMenu(){
        
        HBox menuHbox = new HBox();
        // label to display events
		Label l = new Label("\t\t\t\t" + "");
		
        // text field for user parameter
        TextField userParameter = new TextField();
        userParameter.setPromptText("enter user then select menu command"); //to set the hint text
        userParameter.setPrefWidth(250);
		userParameter.setId("USERPARAMETER");
		userParameter.setOnAction(Main.ListenerClass.txtFieldEvent());
		userParameter.addEventHandler(KeyEvent.KEY_RELEASED, Main.ListenerClass.eventHandlerTextFieldKeyReleased);

		// text field for file parameter
        TextField fileParameter = new TextField();
        fileParameter.setPromptText("enter file path then select menu command"); //to set the hint text
        fileParameter.setPrefWidth(250);
		fileParameter.setId("FILEPARAMETER");
		fileParameter.setOnAction(Main.ListenerClass.txtFieldEvent());
		fileParameter.addEventHandler(KeyEvent.KEY_RELEASED, Main.ListenerClass.eventHandlerTextFieldKeyReleased);

		// create a menubar
		MenuBar mb = new MenuBar();

        // create a File menu
		Menu fileMenu = new Menu("File");
		// create menuitems
		MenuItem f1 = new MenuItem("Initialize");
        f1.setId(CmdEnum.INIT.toString());
		MenuItem f2 = new MenuItem("Load Social Network");
        f2.setId(CmdEnum.LOADFILE.toString());
		MenuItem f3 = new MenuItem("Save Social Network");
        f1.setId(CmdEnum.SAVEFILE.toString());
		// add menu items to menu
		fileMenu.getItems().add(f1);
		fileMenu.getItems().add(f2);
		fileMenu.getItems().add(f3);

        // create a Users menu
		Menu usersMenu = new Menu("Users");
		// create menuitems
		MenuItem u1 = new MenuItem("Add User");
        u1.setId(CmdEnum.USERADD.toString());
		MenuItem u2 = new MenuItem("Remove User");
        u2.setId(CmdEnum.USERREMOVE.toString());
		MenuItem u3 = new MenuItem("Show All");
        u3.setId(CmdEnum.USERSHOWALL.toString());
		MenuItem u4 = new MenuItem("Path to User");
        u4.setId(CmdEnum.SHORTESTPATH.toString());
		// add menu items to menu
		usersMenu.getItems().add(u1);
		usersMenu.getItems().add(u2);
		usersMenu.getItems().add(u3);
		usersMenu.getItems().add(u4);

        // create a Users menu
		Menu connectionsMenu = new Menu("Connections");
		// create menuitems
		MenuItem c1 = new MenuItem("Add Connection");
        c1.setId(CmdEnum.CONNECTIONADD.toString());
		MenuItem c2 = new MenuItem("Remove Connection");
        c2.setId(CmdEnum.CONNECTIONREMOVE.toString());
		MenuItem c3 = new MenuItem("Shared Friends");
        c3.setId(CmdEnum.SHAREDFRIENDS.toString());
		// add menu items to menu
		connectionsMenu.getItems().add(c1);
		connectionsMenu.getItems().add(c2);
		connectionsMenu.getItems().add(c3);
		
		

		// create events for menu items
		// action event
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{	
				System.out.println(((MenuItem)e.getSource()).getId());
				if (((MenuItem)e.getSource()).getId().equals(CmdEnum.USERADD.toString())){
					//print label to console for debugging
					String label = "\t\t" + ((MenuItem)e.getSource()).getText() + " selected" +
	                        " with user parameter: " + userParameter.getText() +
	                        " with ID: " + ((MenuItem)e.getSource()).getId(); 
					l.setText(label);
					System.out.println(label);
				}
				
				if (((MenuItem)e.getSource()).getId().equals(CmdEnum.LOADFILE.toString())){
					//print label to console for debugging
					String label = "\t--" + ((MenuItem)e.getSource()).getText() + 
							"(ID: " + ((MenuItem)e.getSource()).getId() +") " +
							"\n\t selected" + " with file: " + fileParameter.getText();
					l.setText(label);
					System.out.println(label);
				}
			}
		};

		// add events -- the event handler is for dev purposes only
		f1.setOnAction(Main.ListenerClass.menuCmdEvent()); //INIT
		f2.setOnAction(Main.ListenerClass.menuCmdEvent()); //LOADFILE
		f3.setOnAction(Main.ListenerClass.menuCmdEvent()); //SAVEFILE
        u1.setOnAction(Main.ListenerClass.menuCmdEvent());
		u2.setOnAction(Main.ListenerClass.menuCmdEvent());
		u3.setOnAction(Main.ListenerClass.menuCmdEvent());
        c1.setOnAction(Main.ListenerClass.menuCmdEvent());
		c2.setOnAction(Main.ListenerClass.menuCmdEvent());
		c3.setOnAction(event);


		// add menus to menubar
		mb.getMenus().add(fileMenu);
        mb.getMenus().add(usersMenu);
        mb.getMenus().add(connectionsMenu);
        

		menuHbox.getChildren().add(mb);
        menuHbox.getChildren().add(new Label("\t"+"user: "));
        menuHbox.getChildren().add(userParameter);
        menuHbox.getChildren().add(new Label("\t"+"file: "));
        menuHbox.getChildren().add(fileParameter);
        
        return menuHbox;
        
    }
    
}
