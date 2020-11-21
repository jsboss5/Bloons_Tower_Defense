/**
 * This interface will be implemented by each type of balloon.
 */
public interface Balloon{

  // This will remove the balloon from the board after it has received the required number of hits.
  public void pop();

  // This will update the balloon's state after it has been hit, including calling updateHealth().
  public void onCollision();

  // This will decrease the number of pops required to eliminate this balloon.
  public void updateHealth();

  // This will return the number of pops remaining to eliminate this balloon.
  public void getHealth();
}