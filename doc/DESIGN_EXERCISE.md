# OOGA Lab Discussion
## Jodi Yeh (jhy9), Amrita Lakhanpal (al418), Josh Boss (jsb91), Arjun Rao (ar482)


## Fluxx

### High Level Design Ideas
* Controller 
    * Contains instance of model and display that updates every iteration
    * step function in controller 
    * Pass a stage to display (only need one display)
    * Only way that backend and frontend are connected; neither frontend nor backend should know
    about anything but itself 
* Frontend
    * Tower View
    * Balloon View
    * Display game layout file that was read in 
    * Store View (allows you to get more towers) 
        * Will update coins as well 
    * View Components of Player
        * Health Bar
        * Coins
* Backend
    * Configuration loader class to read in .properties file and file containing game layout
        * Types of balloons will be in .properties file 
        * Levels contained within individual .properties file 
    * Abstract Balloon Class
    * Abstract Tower Class
    * Bullet Class
    * Player Class (contains Coin field)  
    * Abstract Collisions Class 
* Error Checking
    * Validation to make sure path is complete (custom exception) 
* Current Questions
    * How can we implement this without using a grid in a way that is still flexible? 

### CRC Card Classes

This class's purpose or value is to manage something:
```java
 public class Something {
     public int getTotal (Collection<Integer> data)
     public Value getValue ()
 }
```

This class's purpose or value is to be useful:
```java
 public class Value {
     public void update (int data)
 }
```

### Use Cases

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```
