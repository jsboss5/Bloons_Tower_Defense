/**
 * Player purchases tower...
 */
public class Tower{
  public void checkTowerUpgrade(Button pauseButton){
      pauseButton.setOnAction(e->{

      myPlayer.purchaseTower(new Tower(this.getName())); //this refers to the button that has been clicked

      });
      }
}

public class Player{
  Game myGame;
  int coins; //instance variable that represents the coins
  int health;  //instance variable that represents the health

  public void purchaseTower(Tower towerType){  //This method will pruchase a tower from the player class given a Attacker Object
    if(this.coins()<towerType.getPrice()){  //checks to make sure that the player has neough money, if not it returns
      return;
    }

    myGame.addTower(Tower towerType); //calls the addTower method from the game class. Game will be resopnsible for storing towers not the player class
    this.coins-=towerType.getPrice(); //decrement the appropriate number of coins after purchsing the tower
  }

}

public class Game{
  Set<Tower> towers; // instance variable of all towers in the game

    myGame.addTower(Tower thisTower){ // adding a tower to the game
    towers.add(thisTower); // adding a tower to the set that contains all the towers
  }
}