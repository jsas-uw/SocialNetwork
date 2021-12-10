# SocialNetwork

# Remy's tasks
- Shortest path algorithm
-

# John's tasks
Still working off the "Combined" branch with all files (Looks like there's someover lap with what I did and what Bill's working on so I don't want to push to main just yet) - 12/10/2021

To run application, follow the helloFx directions exactly. I think the source folder causes issues because it tries to create a default package. When I created the project with src and bin in the same folder it stopped giving me issues with the pacakge. Once the project is setup you should be able to download the combined branch and do a paste/replace all for the application folder in helloFX.

Updates 12/10/2021:

I added a class for the bottom pane based on the original GUI mockup. I also added a file field since that seemed easier than trying to update the "user" label depending on context. Every action should be based on a user or a file so that covers the GUI.

I got it to use the graph to populate the buttons. Show all users and load file will display actual users now. logCommand parses everything from the input files and updates the graph correctly.

# Bill's tasks
- Today 
-     built the executable .jar; works on my machine and runs with the batch file; put the zip in email and canvas files;
- Next.
-     I have the day off Friday 12/10. I will be working on displaying actual buttons from the Graph data by using a HashMap to cache user buttons.
-     Will not need to have Remy change the backend. Returning a List<String> will be good. I'll do the conversion in a "UserButtonFactory" class.
- Later (if no one gets to it first)
-     I hope to get a File Picker to work for both the load and save. -Bill 20211207




Todo: Main thing is hooking everything together so this actually works. With the bottom pane/menu updates the GUI should at least look functional so updates there should be secondary.

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

The ListenerClass should probably move over to the SocialNetworkManager class and the .setOnAction(...) instructions be refactored to call SocialNetworkManager.ListenerClass... -Bill 20211207


# Graph

*Removed Cycle detection from the graph classes
*Some of the buttons still aren't implemented, but based on load file the graph is constructed as expected.

https://github.com/jsas-uw/p4-graph
I made a repo for my graph program. 
Since its not using hash tables it may be 
better to use a different graph program as 
the base as long as its not missing
a function we need for the social network.
