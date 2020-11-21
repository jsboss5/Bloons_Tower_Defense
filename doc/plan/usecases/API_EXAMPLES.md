#API Examples

Use Case 1: Tower shoots a bullet (16)

```java=
public void shoot(){
    Bullet specialBullet = new SpecialBullet(20); //type of bullet depends on type/level of attacker
    Balloon closestBalloon = this.findClosestBalloon(); //method in Tower class to find closest target to pop
    closestBalloon.updateHealth(specialBullet.getStrength()); //method in Balloon class to update health after being hit by bullet
}
```

Use Case 2: Player purchases a new Tower
```java=

//Every single attacker has an associated upgrade 


public void checkTowerUpgrade(Button pauseButton) {
    pauseButton.setOnAction(e -> {
        
    myPlayer.purchaseTower(new Tower(this.getName())); //this refers to the button that has been clicked
        
    });
  }

public class Player{
   Game myGame;
   int coins; //instance variable that represents the coins
   int health;  //instance variable that represents the health
   
   public void purchaseTower(Tower towerType){  //This method will pruchase a attacker from the player class given a Tower Object
        if(this.coins()<towerType.getPrice()){  //checks to make sure that the player has neough money, if not it returns
            return;
        }

        myGame.addTower(Tower towerType); //calls the addTower method from the game class. Game will be resopnsible for storing towers not the player class
        this.coins-=towerType.getPrice(); //decrement the appropriate number of coins after purchsing the attacker
    }

}

public class Game{
    Set<Tower> towers; // instance variable of all towers in the game
    
    myGame.addTower(Tower thisTower){ // adding a attacker to the game
        towers.add(thisTower); // adding a attacker to the set that contains all the towers 
    }
}


```

Use Case 3: Player completes a level. 

```java=
// This method will determine whether or not a player has completed a level. 
public boolean hasWon() { 
    if(balloonsGone()) { // will check if the data structure containing target objects is empty 
        if(currentLevel != MAX_LEVEL) { 
            myPlayer.advanceLevel(currentLevel + 1); // allows the player to move on to the next level if they have not reached the end of the game
        } else(currentLevel == MAX_LEVEL) { 
            myPlayer.endGame(); // will display screen indicating that player has won the game
        }
        return true; // helps program know when to execute this method 
    }
    return false; // if this returned, game will continue playing
}
```


 Implementation:
 ```java=
 
 private int health;
 // This instance variable is in the player class and will keep track of the player's health (lives/health bar) 
 
 public void updateHealth(Balloon target){ 
     health -= target.getHealth();
 // Taking in the target that made it to the end, the player will lose the remaining health of the target
 }
 ```
