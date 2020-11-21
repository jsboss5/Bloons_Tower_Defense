# Sprint 2
## Team JAJA: Jodi, Amrita, Josh, and Arjun

---
# Project Progress 
* Connected front-end and back-end (Arjun)
* Balloon movement animation (Amrita) 
* Balloons pop as they pass by tower (Arjun -> Back-End, Jodi -> Front-End)
* Tower shoots bullet to pop balloon (Amrita)  
* Balloon popping animation (Amrita) 
* Added Status Displays: Health Bar and Coins 
* Overcame phantom balloon issue
* Wrote custom bidirectional map data structure (Josh)
* Utilized generics in backend GridCell class (Josh)
* Wrote Player class to track coins
---

# Significant Event 
### The Phantom Balloon 
* JavaFx had a refresh error so it would not recognize that a balloon was removed from the scene. When the balloon got to the end of the path, the balloon would stay there. 
    * We spent numerous hours trying to debug this together because the premise of the game is that the balloons disappear. 
    * One method of resolving this issue that we tried was to make the balloon transparent, but when we removed it from the root, it stayed there.
* After meeting with a TA and attempting to fix this error in a number of ways, we deemed that it was a glitch within JavaFX. 
    * Ultimate Solution
        * Removed the balloon from the root
        * Made a new scene to manually refresh the scene. 
        * Afterwards, there was a lot of flashing in between so to avoid that, we made the balloons disappear and then refreshed the whole scene afterwards.
---
# Teamwork/Communication
* In Person Coding Session!!
    * Made Pancakes
    * Went to the Biden celebration downtown
* Long Zoom Calls
    * Utilized Breakout Rooms/Remoet Control 
* Work through significant bugs together 
---
# Planned Features 

* Front End (Amrita/Jodi)
    * Splash Screens (Home, Pause, and Game Over Screens) 
    * Speed Up Button 
    * Tower Hovers 
    * Implement Cost of Towers
    * Coins
    * Improve Look of View 
---
# Planned Features 

* Back End (Josh/Arjun) 
    * Levels
    * Player Class
    * Implement different types of balloons and speeds
    * Balloon Generator
    * Tower Upgrades 
    * Connect Health Bar 
    * Obstacles in Path
---
# Planned Features 

* Data (Everyone) 
    * Refactor to create a more data-driven design

* Ambitious 
    * Upgrades/PowerUps 

