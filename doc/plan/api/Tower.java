/**
 * This interface will be implemented by each type of tower.
 */
public interface Tower{
  // This will release a bullet from the tower.
  public void shoot();

  // This will upgrade the rank of the tower when it comes time to do so.
  public void upgradeTower();
}