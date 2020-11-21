/**
 * This method will determine whether or not a player has completed a level.
 */

 public boolean hasWon(){
    if(balloonsGone()){ // will check if the data structure containing target objects is empty
      if(currentLevel!=MAX_LEVEL){
      myPlayer.advanceLevel(currentLevel+1); // allows the player to move on to the next level if they have not reached the end of the game
      }
      else(currentLevel==MAX_LEVEL){
        myPlayer.endGame(); // will display screen indicating that player has won the game
      }
      return true; // helps program know when to execute this method
    }
    return false; // if this returned, game will continue playing
    }