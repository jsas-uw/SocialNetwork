# SocialNetwork

# Remy's tasks
- Shortest path algorithm
-

# John's tasks
Created "Combined" branch with all files.
I added a class for the bottom pane based on the original GUI mockup. I also added a file field since that seemed easier than trying to update the "user" label depending on context. Every action should be based on a user or a file so that covers the GUI.
Modified the menu/cmdEnum to include remaining functions


# Bill's tasks
- Today 
-     built the executable .jar; works on my machine and runs with the batch file; put the zip in email and canvas files;
- Next.
-     I have the day off Friday 12/10. I will be working on displaying actual buttons from the Graph data by using a HashMap to cache user buttons.
-     Will not need to have Remy change the backend. Returning a List<String> will be good. I'll do the conversion in a "UserButtonFactory" class.
- Later (if no one gets to it first)
-     I hope to get a File Picker to work for both the load and save. -Bill 20211207




Todo: Main thing is hooking everything together so this actually works. With the bottom pane/menu updates the GUI should at least look functional so updates there should be secondary.
- use the graph to populate the flow panes
(right now the buttons are all from hardcoded lists)
- Make the buttons work
(Show all users is the only one with a built out event handler)
- Remy has to add a final function for the SocialNetworkADT to get Shortest path between friends

For reference: SocialNetMenu calls event listeners in the ListenerClass subclass of Main. 
From Main we still need to instantiate a SocialNetworkManager
which should be able to initalize a graph based on a file
and handle making the calls to modify the graph. 
Then listeners trigger events which use the SocialNetworkManager to modify the graph
and make calls to rebuild the panes.

I was playing with event handlers to see if I could get it to accept a file.
The file input field works the same as user. The issue is the load file button isn't getting triggered like add user.
I modeled the load file event handler after add user and its just a second if statement in listener / menuCmdEvent/

The ListenerClass should probably move over to the SocialNetworkManager class and the .setOnAction(...) instructions be refactored to call SocialNetworkManager.ListenerClass... -Bill 20211207

Before class today we can discuss refactors. Before we implement too many buttons I think we should make sure the class structure makes sense.

# Graph

*Removed Cycle detection from the graph classes

https://github.com/jsas-uw/p4-graph
I made a repo for my graph program. 
Since its not using hash tables it may be 
better to use a different graph program as 
the base as long as its not missing
a function we need for the social network.
