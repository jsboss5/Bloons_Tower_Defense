#Bloons Tower Defense

In the file MANAGEMENT_PLAN.md, write your plan for how to manage and prioritize the project that describes the features each team member plans to take primary and secondary responsibility for and a rough timeline of how the team will order the work to complete the program. Specifically, each person should take responsibility for specific features and use cases they intend to work on during each Sprint. This requires the team to agree on the feature priorities and set goals for what to complete at the end of each Sprint (i.e., each week).

###Game Elements

Question: How do we make an interactive board?

_The basic objects that we would need to create the game_

Working balloons on a track
 - Balloons that can only be popped by a certain attacker
 - Balloon that requires multiple shots
 - Super speedy Balloon
 
A Tower object (to be displayed/ created), with diff ranges
- Normal "Monkey" Tower
- Boomerang Tower
- Sniper Tower
- Glue Tower
- Cannon Tower
- Airplanes

*When you place the attacker, you see the range*
- Towers are able to be upgradable
* Farms that generate more money 

Collision Objects that can be placed
* Bombs
* Exploding Pineapples
* Road Spikes

Misc. Elements:
*Able to read in csv files that represents the board
* basic drag and drop based on the part of the board
* Tower shoots at balloons
* Fast forward Speed up
* Lives/Health Bar (doesn't reset)
* Bullets should be able to shoot more than one target
* Easy/Medium/Hard mode
* PowerUps for Individual Towers
* Upgrade Towers
* Music 
* Different tracks (ideally data-driven)
* File Tracker: no complete track then it throws an error
* A target object (to be displayed/ created)


Screens/Visual Elements:
* The board to be displayed
* Store
* Splash screen that pops up to tell you new feature
* Tower Profile (hover over it, see what it does)
* PowerUp Splash Screen
* Instructions
* Pause Screen
* Health Bar/Lives
* Homepage
* Visuals for all towers/collision objects
* Visual for the track
* Bullets
* Drag/Drop



###Sprint 1: PLAN (due 11/2)
_The most basic version of this game that is functional_

_One attacker type, one target, a board that you can see & drag/drop a attacker that shoots a target that runs on a track_

* interactive gameboard (grid-based, csv, else??)
* Basic Balloon Object
* Basic Tower Object
* How will the towers shoot the balloons? (ex: how will attacker shoot multiple balloons, look into APIs)
* Drag/Drop Function
* Balloon runs on a track and a attacker can shoot it // multiple balloons multiple towers (only one type for this sprint)

####Sprint 1: Individual Responsibilities

*Jodi Yeh*
- Make the board show up 
- updateMethod
- Drag/Drop
- Make the attacker show up
- Make the Balloon show up 
- tests

*Joshua Boss*
- Balloon Object
- Tower Object
- updateMethod
- tests

*Amrita Lakhanpal*
- Read Configuration of Grid 
- File checker
- tests
* help with Front-End

*Arjun Rao*
- Create the controller that has instances of the front-end/back-end
- passes stage to front-end
- tests
- step function, that constantly updates
* help with Back-End

###Sprint 2: PLAN (due 11/9)

* curved Grid
* Multiple types of Balloons
* 3 types of Towers
* The store
* different difficulties (different maps)
* coins
* Collision Objects
* Properties Files
* Upgrade Towers

###Sprint 3: Complete (due 11/18)
* finalize everything (more specifics to come)
* adding additional features, polishing it
* making it look good


**Team Members:**

**Jodi Yeh:**
* Primary Responsibility: Front-End
* Secondary Responsibility: Data Management


**Joshua Boss:**
* Primary Responsibility: Back-End
* Secondary Responsibility: Controller


**Amrita Lakhanpal:**
* Primary Responsibility: Data Management
* Secondary Responsibility: Front-End


**Arjun Rao:**
* Primary Responsibility: Controller
* Secondary Responsibility: Back-End

