/**
 * This will be used to implement different types of players.
 */
public interface Player{
  // This will update the health of the player if they were to lose a life.
  public void updateHealth();

  // This will be used to enable the player to buy a PowerUp from the store.
  public void purchasePowerUp();

  // This will be used to help the player purchase an upgraded tower from the store.
  public void purchaseTower();

  // This will be executed when the player has lost the game.
  public void hasLost();

  // This will be executed when the player has won the game.
  public void hasWon();
}