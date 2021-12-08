package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    John Sas
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class SocialNetworkManager {
    
    private Graph graph;
    static ArrayList<Package> packages;
    private ArrayList<String> commandLog;

    /*
     * Package Manager default no-argument constructor.
     */
    public SocialNetworkManager() {
        this.graph = new Graph();
        this.commandLog = new ArrayList<String>();
    }
    
    /*
     * constructGraph is really two methods
     * spin off parseJSON to create an ArrayList 
     * of packages, then from that you can create the graph
     */
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void loadFile(String filePath) throws FileNotFoundException, IOException
    {
    	
    	File file = new File(filePath);
    	Scanner scanIn = new Scanner(file);
		
		// Reading any string of characters from the input file & printing to file
		while(scanIn.hasNextLine()) {
			String inCommand = scanIn.nextLine();
			
			// Do the next line of commands
			logCommand(inCommand);
			
		}
		// Close the new instances		
		scanIn.close();
    	
    }
    
    public void logCommand(String command)
    {
    	//No matter what, log the action
    	commandLog.add(command);
    	
    	// Split the commands up to usable entries.
    	String[] splitStr = command.split("\\s+");
    	
    	// If the first piece is S or null, GUI handles the action, so quit
    	if (splitStr[0] == null || splitStr[0] == "s")
    		return;
    	
    	// If the first piece is a, add the edge or vertex to the graph
    	if (splitStr[0] == "a")
    	{
    		if (splitStr[2] == null)
    			graph.addVertex(splitStr[1]);
    		else
    			graph.addEdge(splitStr[1],splitStr[2]);
    	}
    	
    	// If the first piece is r, remove the edge or vertex (and edges) of the graph
    	if (splitStr[0] == "r")
    	{
    		if (splitStr[2] == null)
    			graph.removeVertex(splitStr[1]);
    		else
    			graph.removeEdge(splitStr[1],splitStr[2]);
    	}
    }
    
    // Return the most recent logged action
    public String getMostRecentAction()
    {
    	String action = new String();
    	String abrevAction = commandLog.get(commandLog.size()-1);
    	
    	// Split the commands up to usable entries.
    	String[] splitStr = abrevAction.split("\\s+");
    	
    	// If the first piece is S or null, GUI handles the action, so quit
    	if (splitStr[0] == "s")
    		action = "Set the central user to " + splitStr[1];
    	
    	// If the first piece is a, friendships/users were added.
    	if (splitStr[0] == "a")
    	{
    		if (splitStr[2] == null)
    			action = "Added user " + splitStr[1] + " to the Social Network";
    		else
    			action = "Added a friendship between " + splitStr[1] + " and " + splitStr[2];
    	}
    	
    	// If the first piece is r, remove the edge or vertex (and edges) of the graph
    	if (splitStr[0] == "r")
    	{
    		if (splitStr[2] == null)
    			action = "Removed user " + splitStr[1] + " from the Social Network";
    		else
    			action = "Removed the friendship between " + splitStr[1] + " and " + splitStr[2];
    	}
    	
    	return action;
    }
    
    // Save the file
    public void saveLog(String fileOut) throws FileNotFoundException, IOException
    {
    	// An instance of PrintWriteer to generate output file
    	 PrintWriter pwOut = new PrintWriter(fileOut);
    	 for (int i = 0; i < commandLog.size(); i++)
    	 {
    		 pwOut.println(commandLog.get(i)); 	 
    	 }
    	 
    	// Close the new instances
 		pwOut.close();	
    }
    
    
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPeople() {    	
    	return graph.getAllVertices();
    }
    
    // Search for a person. If found, return true.
    public boolean findUser(String person)
    {
    	if (graph.containsVertex(person).isEmpty())
    		return false;
    	else
    		return true;
    }
    
    // Get mutual friends
    public List<String> mutualFriends(String person1, String person2)
    {
    	// Instatiate the lists
    	List<String> person1Friends = new ArrayList<String>();
    	List<String> person2Friends = new ArrayList<String>();
    	List<String> mutualFriends = new ArrayList<String>();
    	
    	// Get the friends lists
    	person1Friends = this.getFriends(person1);
    	person2Friends = this.getFriends(person2);

    	// Check if the friends list have overlap and add to the mutual friends list
    	for (String s: person1Friends) {
    		if (person2Friends.contains(s))
    			mutualFriends.add(s);
    	}
    	
    	return mutualFriends;
    }
    
    // Get number of connected components
    public int numConnections(){
    	int count = 0;
    	Set<String> allPeople = this.getAllPeople();
    	List<String> searchedPeople = new ArrayList<String>();
    	List<String> currSearchPeople = new ArrayList<String>();
    	
    	for (String s: allPeople)
    	{
    		if (!searchedPeople.contains(s))
    		{
    			currSearchPeople = graphDFS(s);
    			
    			// There should always be one person in the connection so I can just add the count
    			count++;
    			for (String curr: currSearchPeople)
    				searchedPeople.add(curr);
    		}
    	}
    	return count;
    }
    
    /**
     * graphDFS is just a wrapper for DPS, 
     * instantiates the lists and starts the 
     * recursive call to DFS
     */
    private ArrayList<String> graphDFS(String vertex){
    	HashSet<String> visited = new HashSet<String>();
    	HashSet<String> cycle = new HashSet<String>();
    	ArrayList<String> order = new ArrayList<String>();

    	return DFS(vertex, visited, cycle, order);
    }
    
    /**
	 * DFS is the actual implementation
	 * this is the meat of the program
	 * most other things are based on this
	 */
    private ArrayList<String> DFS(String v, HashSet<String> visited, HashSet<String> cycle, ArrayList<String> order){
    
		if (visited.contains(v)) {
			return order;
		}
		
		cycle.add(v);
		
		var depList = getFriends(v);
		for (String dep : depList) {
			order = DFS(dep, visited, cycle, order);
		}
		
		order.add(v);
		visited.add(v);
		cycle.remove(v);
    	
    	return order;
    }//method
 
    // Gets all friend's for a given person
    public List<String> getFriends(String person){    	
    	for (String s : getAllPeople()) {
    		if (s.equals(person)) {
    			return graph.getAdjacentVerticesOf(s);
    		}
    	}
    	
    	return new ArrayList<String>();
    }
}
