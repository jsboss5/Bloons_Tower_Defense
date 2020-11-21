/**
 * Targets make it to the end of the path - player loses health
 */
public class Player {

  private int health;
// This instance variable is in the player class and will keep track of the player's health (lives/health bar)
  public void updateHealth(Balloon balloon) {
    health -= balloon.getHealth();
    // Taking in the balloon that made it to the end, the player will lose the remaining health of the balloon
  }
}