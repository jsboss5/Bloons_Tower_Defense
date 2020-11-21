#Use Cases

* A new game is started and the player's coins are reset to the starting value of 100. (1)
 ```java
 Player myPlayer = new Player();
//The constructor will set the number of coins for this player to 100 as well as other values such as lives.
 ```

 * A player buys their first attacker, and their coins are decremented. (2)
 ```java
 myPlayer.purchaseTower(Tower myTower);
//The purchaseTower method will decrement coins and create an instance of a backend object. 
 ```

* A player places a purchased attacker in the game. (3)
```java
grid[row][col].addTower(Tower myTower); 
```

* A player shoots all of the balloons within one level. (4)
```java
if(balloonsGone) { 
    if(currentLevel != MAX_LEVEL) { 
        myPlayer.advanceLevel(currentLevel + 1); 
    }
}
```

 * Player purchases a new road block object. (5)
 ```java
 myPlayer.purchaseRoadBlock(RoadBlock mySpikes);
 
 ```

 * Player's health depletes -- player loses the game (6)
 ```java
if(myPlayer.hasLost()){
    myDisplay.showLoseScreen();
}
 ```

 * Player completes the final level (7)
```java
if(myPlayer.hasWon()) { 
    myDisplay.showWinScreen(); 
}
```

 * Balloons make it to the end of the path - player loses health (8)
 ```java
 myPlayer.updateHealth();
 ```
 
 
 * A player activates a PowerUp. (9)
```java
PowerUp myPowerUp = new PowerUp(); 
myPlayer.activatePowerUp(myPowerUp); 
grid[row][col].addPowerUp(myPowerUp); 
```

* Player upgrades a attacker from rank 1 to rank 2 (10)
```java
if(rank != MAX_RANK) { 
    myTower.upgradeTower(rank + 1);
}
```

* Player gains coins from popping a target (11)
```java
myPlayer.updateCoins();
```

* Bullet hits target but does not pop it (12)
```java
Balloon.updateHealth(bullet.getStrength());
```

* Bullet hits target and pops it (13)
```java
Balloon.updateHealth(bullet.getStrength());
if(Balloon.isPopped()){
    balloonsInPlay.remove(Balloon);
}
```

* Player Levels Up (14)
```java
myPlayer.checkLevelUp();

public void checkLevelUp(){
    if(xp > myLevel.getUpgradeThreshold()){
        myPlayer.levelUp();
    }
}
```

* Player purchases additional lives (15)
```java
myPlayer.purchaseLives(); 
// This method will handle increasing the number of lives for the player. 
```

* Tower shoots a bullet (16)
```java
Tower.shoot(); //each attacker has different implementation of shooting bullet
```

* Player selects difficulty. (17)
```java
Button.onMouseClick(event -> setDifficulty(int difficulty)); 
```

* Player tries to place attacker in incorrect location (18)
```java
if(!myTower.checkLocation()){
    myTowerView.displayRedRange();
}
```

* Player selects map (19)
```java
Button.onMouseClick(event -> selectMap(chosenMap));

public void selectMap(Map chosenMap){
    createMap(chosenMap)
}
```

* Player speeds up game (20)
```java
Button.onMouseClick(event -> increaseGameSpeed());
```

