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

Todo:
- Final checks: review assignment/rubric to make sure we have everything
- Bottom pane: check that we have all status messages and hook the counters up to SocialNetworkManager to get graph stats (done)

Added an alert popup to prompt the user to save before exiting.


# Bill's tasks
- Today 
-     added some quick styling to the round user buttons.
- Next.
-     wire up the last of the menu items and comment out initialize (unless John gets to these first)
- Later (if no one gets to it first)
-     I hope to get a File Picker to work for both the load and save. -Bill 20211207


# Graph

*Removed Cycle detection from the graph classes

*Some of the buttons still aren't implemented, but based on load file the graph is constructed as expected.

https://github.com/jsas-uw/p4-graph
I made a repo for my graph program. 
Since its not using hash tables it may be 
better to use a different graph program as 
the base as long as its not missing
a function we need for the social network.
