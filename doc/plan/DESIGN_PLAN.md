# Design Plan - Team JAJA

## Introduction:
The genre of game that team jaja plans to implement is classic video games, which encompasses a wide 
variety of different games. We want the design to accomodate for ((read in)) different level layouts 
and the ability to easily add/integrate new objects (ex: new powerups, roadblocks, lives) with the 
old ones. The open portion will be objects, possible players, new features that extend abstract 
classes. The closed portion will consist of reading in the file configuration and the abstract 
object possibilities. Overall, it will be structured through MVC (Model View Controller). It'll be 
data-driven design that utilizes abstraction, interfaces, and reflection. 

## Overview
*This section serves as a map of your design for other programmers to gain a general understanding 
of how and why the program was divided up, and how the individual parts work together to provide the 
desired functionality. Describe specific modules you intend to create, their purpose with regards to 
the program's functionality, and how they collaborate with each other, focusing specifically on each 
one's API. Include a picture of how the modules are related (these pictures can be hand drawn and 
scanned in, created with a standard drawing program, or screen shots from a UML design program). 
Discuss specific classes, methods, and data structures, but not individual lines of code.*

The three most high level modules will be model, view, and controller. The model represents the 
backend of the program and controls where different objects should be positioned, how they interact 
with eachother, reading in properties files, updating gamestates and more. The view is the frontend, 
which is literally just a visual extension of the model. It provides the user a user interface to 
visualize what is being produced in the backend, while also providing a medium by which they can 
change the game and use their strategy to beat the game. The controller module will be responsible 
for mediating between the backend and the frontend, and contain instances of each. The reason at a 
high level it will be divided in this way, is so that the logic for the game will never be conflated 
with the visualization, and we can have different team members focus on each without having to worry 
about some change in the back end affecting another change in the front end. It's good to keep 
things completely encapsulated into these three silos. 

Another module that we will have will be an Input/Output module that allows us to read in properties 
files, and gameboard csv files. The output will allow the user to save the files. 

## Design Details
*This section describes each module introduced in the Overview in detail (as well as any other 
sub-modules that may be needed but are not significant to include in a high-level description of the 
program). Describe how each module's API handles specific features given in the assignment 
specification, what resources it might use, how it collaborates with other modules, and how each 
could be extended to include additional requirements (from the assignment specification or discussed 
by your team). Finally, justify the decision to create each module with respect to the design's key 
goals, principles, and abstractions.*

Model- this module will contain the back-end of the program with the characteristics and states of 
the objects.There would be abstract classes for general objects and abstractions of these classes 
for specific types of these objects. The model will continuously update the states for each object 
and may facilitate interactions between objects (collisions).

Input/Output - this module will read in files in a format best used for the model and view modules. 
It may use reflection or property files to read in certain style preferences for the front end

View - this module will contain the front-end of the program with visualizations of objects from the 
back-end. The view may notify the Controller of collisions through an API call in the controller 
(get/set request). 

Controller - this module will facilitate interactions and info between the model and view modules. 
The controller will listen for collisions that are found in the View and make API calls in the 
backend to update states based on this collision. The controller will then allow the view to see 
these updated states so that the visualization of the backend can change with the backend.

## Example Games
*Describe three example games that differ significantly in detail. Clearly identify how the 
functional differences in these games is supported by your design. Use these examples to help 
clarify the abstractions in your design. These do not need to be the same as the final ones you 
implement but could be.* 

* Pac-Man
    * Player controls the character as it travels through a maze, accumulating points. 
    * The game includes characters that need to be avoided, as they will kill the main character. 
    * There are different paths for the character to travel through to collect objects along the way.
    * The game includes PowerUps that enable the main character to eat the ghosts, which are the other 
    characters that the main character usually tries to avoid. 
    
* Bloons Tower Defense
    * Player uses Towers to shoot balloons as they enter the screen. 
    * The goal is to hit all balloons before they reach a certain point. 
    * The game also includes PowerUps, such as a bomb, that will destroy numerous balloons at a time.
    * Each level of the game involves the balloons following a distinct path, which increases the difficulty
    since the player has to adjust to the new path and its different turns as they play. 
    
* Flappy Bird
    * The player controls a bird that travels through a series of pipes. 
    * The goal is to fly through the gap between the pipes and avoid running into the pipes. 
    * Along the way, there are coins scattered throughout that increase the player's points. 
    * As the gametime increases, the speed of the bird quickens, making it more difficult for the player to
    control the bird. 

These three games have a number of elements in common. They all involve characters, goals, rewards, 
paths, and obstacles. Our goal with our design is for it to be flexible in a way that involves the 
addition and integration of such features with the previously existing ones. So, although the 
characters, goals, rewards, paths, and obstacles all differ in these games, they operate under the 
same idea in terms of their functionality, which would make it easier to use a common design to 
accommodate multiple different games. 

## Design Considerations
*This section describes any issues which need to be addressed or resolved before attempting to 
devise a complete design solution. Include any design decisions discussed at length (include pros 
and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies 
regarding the program that impact the overall design.*

Before attempting a complete design solution, we need to consider the following: 

1. Data File Structure
    * We are figuring out whether we want the file to be a text or csv file or grid-based. The question that we have lies with how to make the game flexible without making it look like a 'block'and limiting ourselves hard-coded X & Y coordinates. The pros of utilizing a Grid structure is that it is easy to reference what goes where, drag and drop, and get the coordinates of an object with respect to the grid. The cons are that we will have to hard-code a window size into a css or properties file. 
2. Methods the Controller will make use of to update the Model & View
3. How will the Object predict where to shoot the bullet
4. How to best communicate between the Front-End and Back-End (Map?)

