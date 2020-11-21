Bloons Tower Defense (And others)
====

This project implements a player for multiple related games.

Jodi Yeh, Amrita Lakhanpal, Josh Boss, Arjun Rao


### Timeline

Start Date: October 24, 2020

Finish Date: November 19, 2020

Hours Spent: 300+ Hours

### Primary Roles

**Jodi Yeh:**
* Primary Responsibility: Front-End
* Secondary Responsibility: Data Management

**Amrita Lakhanpal:**
* Primary Responsibility: Data Management
* Secondary Responsibility: Front-End

**Joshua Boss:**
* Primary Responsibility: Back-End
* Secondary Responsibility: Controller

**Arjun Rao:**
* Primary Responsibility: Controller
* Secondary Responsibility: Back-End


### Resources Used

**Images:**
* [Bloons Tower Defense Wiki](https://bloons.fandom.com/wiki/Bloons_Wiki)
* [Background](https://lh3.googleusercontent.com/TRfBcl_aVNcp5VaDX1qLlgQ6QW7P7f-uR-Dk_kZIqFAJsoLtvGyhHW5BurRSsMQ96BNhXFo=s113)
* Our other images were drawn by Jodi. 

**External Resources:**
* StackOverflow
* Office Hours (TA and Professor Duvall) 

### Running the Program

Main class: Main

Setting Up IntelliJ:
    * Libraries Needed: 
        * org.testfx:testfx-junit5:4.0.16-alpha
        * com.opencsv:opencsv:5.2
    * We also use JavaFX in our project. 

Data files needed: 
* Our game is heavily reliant on data files, including both CSV and Properties Files. 
    * Game Path File (CSV - located in data directory): 
        * gamepath_01.csv
        * gamepath_02.csv
        * gamepath_03.csv
        * gamepath_sprint1.csv
        * gamepathCoord01.csv
    * Properties Files (located in resources directory): 
        * Levels
            * 1.properties
            * 2.properties
            * 3.properties 
        * Attackers
            * GlueAttacker.properties
            * RegularAttacker.properties
            * SniperAttacker.properties
        * Targets
            * CamoTarget.properties
            * MetalTarget.properties
            * RegularTarget.properties 
        * View
            * Game.properties
            * GameOver.properties
            * GamePieceProperties.properties
            * GameWon.properties
            * Levels.properties
            * Map1.properties
            * Map2.properties
            * Map3.properties
            * MapImages.properties
            * MapSelections.properties
            * ObjectImages.properties
            * PathTypes.properties
            * Screen.properties
            * StatusDisplay.properties
            * StoreOption.properties
            * StoreOptionImages.properties
            * TargetValues.properties
    * CSS Files (style the view - located in resources directory) 
        * GameScene.css
        * Screen.css
        * StatusDisplay.css
        * StoreOption.css
    * Music (located in resources directory) 
        * GameMusic.mp3
    * Images (most of the objects within our game are images - located in resources directory) 
        * Background.png
        * Coin.png
        * GameOver.png
        * GameWon.png
        * GlueAttacker.png
        * Homepage.jpg
        * Map1.png
        * Map2.png
        * Map3.png
        * MapChooser.jpg
        * MetalTarget.png
        * MutedSpeaker.png
        * path1.png
        * path2.png
        * path3.png
        * path4.png
        * path5.png
        * path6.png
        * Popped.png
        * RegularAttacker.png
        * RegularBullet.png
        * RegularTarget1.png
        * RegularTarget2.png
        * RegularTarget3.png
        * Shop.jpg
        * SniperAttacker.png
        * SniperAttackerShop.png
        * Speaker.png
        * TackAttacker.png

Features implemented:
* Targets 
    * Targets move along a path 
    * Different types of targets 
        * RegularTarget1
        * RegularTarget2
        * RegularTarget3
        * MetalTarget
    * The target types differ in the way that they are popped.
        * Some targets require multiple shots before they are hit.
        * The metal target can only be shot by a particular type of attacker. 
* Attackers 
    * Attackers shoot at targets on the path. 
    * Different types of attackers 
        * RegularAttacker
        * TackAttacker
        * GlueAttacker
        * SniperAttacker 
        * The attackers differ in the way in which they affect the targets. 
            * One attacker shoots at every target in their path.
            * The TackAttacker shoots more than one target at once. 
            * The GlueAttacker slows the movement of the targets as it shoots them.
            * The SniperAttacker is able to cover the entire board with its range and shoots metal
            targets. 
* Drag and Drop
    * Attackers are dragged and dropped onto a specific location on the path by the player.
    * Attackers cannot be placed on the path, as this is the track that targets follow. 
* Coins
    * Players begin with a certain number of coins. 
    * As they hit targets, they accumulate more coins. 
* Shop
    * Players use coins to purchase attackers from the shop.
    * Players can hover over each attacker in the shop to learn more about its functionality and cost. 
* Data Input
    * Our game is able to read in CSV files that represent the game path      
* Health Display
    * If the player misses popping certain targets on the path, their health decreases. 
    * The goal of the game is to complete all of the levels with health remaining.
    * The health display is located in the top right corner of the screen, and it decrements as the
    player loses health. 
* Animations
    * Bullet Shooting
        * We implemented an animation that shoots a bullet from a specific attacker towards a target.
    * Target Exploding 
        * When an attacker makes contact with a target, it explodes/pops, and creates an animation
        to reflect this. 
    * Target Movement
        * We created an animation that enabled the targets to move smoothly along the path, rather
        than jumping from cell to cell. 
* Splash Screens
    * Homepage 
        * We created a homepage that invites players to the game. 
    * Game Over
        * We have a splash screen that displays when the player has lost the game.
        * It reveals to the player that they have lost the game and asks if they would like to play
        again. 
    * Victory
        * A splash screen is generated when the player has won the game, which happens if they 
        complete all of the levels with health remaining. 
        * It reveals to the player that they have won the game and asks if they would like to play
        again. 
* Map Chooser
    * After indicating that the player would like to begin playing on the homescreen, they have the
    option to select the map that they would like to begin playing with. 
    * Currently, we have three maps on this screen, but plan to add more during the code freeze. 
* Music
    * There is game playing throughout the game. 
    * The player has the option to pause the sound by clicking the speaker button in the upper
    left corner on any given level. 
    * If the player pauses the music, they can select the unpause button in the upper left corner, 
    which is in the same location as the button that they used to pause the music.  
* Numerous Tracks
    * We created numerous tracks that are generated from data files for the targets to travel along.
 
### Tests/Error Checking 

* The tests that we wrote are located within our test directory. 
* Error Checking 
    * We created custom exceptions to check various potential errors throughout our project. 
        * PropertiesReaderException
        * PathException
        * ImageException 
        
### Cheat Keys
    * Space - Pause Game 
    * Levels (not completely working) 
        * 1 - Switch to Level 1
        * 2 - Switch to Level 2
        * 3 - Switch to Level 3
        
### Notes/Assumptions

Assumptions or Simplifications:
* Our game is heavily driven by data files, mainly CSV files and properties files. With the 
properties files, instead of hard-coding in values for various characteristics within the game,
we read in the value from a properties file. The property name associated with these values are 
coded into the methods themselves, so the game will always need to use these properties names in
order for it to function properly. However, it is rather simple to change the values associated with
these properties, as this only requires changing the value in the properties file. 
* Our data files are CSV files that generate the path. The CSV files contain three pieces: the 
number of rows and columns for the given path, the coordinates of each path portion within the 
track, and last, the path piece that corresponds to each portion of the path. We operate under the
assumption that the CSV files will always be formatted in this way. 

Interesting data files:
* We believe that the path files that we came up are interesting, as they illustrate the complex
paths that our targets are able to traverse. Additionally, the attackers are also able to identify
the targets' locations and shoot them given the attacker's specified range. These files are titled:
    * gamepath_01.csv
    * gamepath_02.csv
    * gamepath_03.csv 
* These data files are located within the data directory in our project. 

Known Bugs:
* Cheat Keys
    * We had hoped to implement cheat keys that would allow the user to easily switch between levels
    if they wished to do so. However, we were not able to figure this out. The code that we wrote
    to try and do this is located in the GameScene, KeyHandler, and Controller classes. 
* JavaFX Refresh Error
    * We realized that JavaFX has a refresh error where objects do not get removed from the root 
    properly, and instead, they remain on the screen. In order to combat that, we had to make the 
    objects transparent, created a new scene without these transparent objects, and replaced the 
    previous scene with this new scene. 

Extra credit:
* The full list of features we implemented is included above. We went above the basic functionality
of Bloons Tower Defense in order to make our game more complex and appealing to the player. 

### Impressions
* We enjoyed this project, as it was nice to be able to see our work visually replicate within our
game. At the end of the project, we feel a great deal of pride in having created such a complex game.


