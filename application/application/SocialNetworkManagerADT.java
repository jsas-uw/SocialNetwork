package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface SocialNetworkManagerADT {

	// Loads in a file to add to a graph and logs all commands.
	public void loadFile(String filePath) throws FileNotFoundException, IOException;

    
	/**
	 * Can be used to call a single action and log it.
	 * For instance, sending "a remy" will add remy to the social network
	 * 
	 * @param command the action in machine readable format
	 */
	public void logCommand(String command);

	/**
	 * Gives the most recent action on the log
	 * 
	 * @return Returns a human readable action.
	 */
	public String getMostRecentAction();

	/**
     * Given a valid file path, saves the log to a file
     * 
     * @param fileOut the specified file output
     * @throws FileNotFoundException thrown if the file output cannot be found
     * @throws IOException thrown if error writing the log to file
     */
	public void saveLog(String fileOut) throws FileNotFoundException, IOException;
    
        
    /**
     * Returns a Set that contains all the friends
     * 
     * @return a Set<String> which contains all the vertices in the graph
     */
	public Set<String> getAllPeople();
    
    
	/**
	 * Attempts to find a given user in the Social Network
	 * 
	 * @param person to find in the Social network
	 * @return True if they are in the network, otherwise false
	 */
	public boolean findUser(String person);
    

    /**
     * Returns a list of mutual friends between two people in the Social Network.
     * 
     * @param person1 to check mutual friends with person2
     * @param person2 to check mutual friends with person1
     * @return List of mutual friends.
     */
	public List<String> mutualFriends(String person1, String person2);
    
    
    /**
     * Returns the number of connected groups in the Social Network
     *  
     * @return number of connected groups in graph.
     */
	public int numConnections();
	
    /**
     * Get all the friends (adjacent-dependencies) of a person
     * 
     * For the example graph, A->[B, C], D->[A, B] 
     *     getAdjacentVerticesOf(A) should return [B, C]. 
     * 
     * In terms of friends, this list contains the immediate 
     * friends of A and depending on your graph structure, 
     * this could be either the predecessors or successors of A.
     * 
     * @param person the specified person
     * @return an List<String> of all the adjacent vertices for specified vertex
     */
	public List<String> getFriends(String person);
}
