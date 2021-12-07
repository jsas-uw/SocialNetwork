# SocialNetwork
Created "Combined" branch with all files.
I added a class for the bottom pane based on the GUI mockup in the main brach.

Modified the menu/cmdEnum to include remaining functions

Will made changes in a seperate repo, looks like mainly color/css changes? https://github.com/bill-wisc/src

Todo: Main thing is hooking everything together so this actually works. With the bottom pane/menu updates the GUI should at least look functional so updates there should be secondary.
- use the graph to populate the flow panes
(right now the buttons are all from hardcoded lists)
- Make the buttons work
- Remy has to add a final function for the SocialNetworkADT to get Shortest path between friends

For reference: SocialNetMenu calls event listeners in the ListenerClass subclass of Main. 
From Main we still need to instantiate a SocialNetworkManager
which should be able to initalize a graph based on a file
and handle making the calls to modify the graph. 
Then listeners trigger events which use the SocialNetworkManager to modify the graph
and make calls to rebuild the panes.

# Graph

*Removed Cycle detection from the graph classes

https://github.com/jsas-uw/p4-graph
I made a repo for my graph program. 
Since its not using hash tables it may be 
better to use a different graph program as 
the base as long as its not missing
a function we need for the social network.
