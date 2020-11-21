/**
 * Attacker shoots a bullet
 */
public class Tower {

  public void shoot() {
    Bullet specialBullet = new SpecialBullet(20); //type of bullet depends on type/level of tower
    Balloon closestBalloon = this.findClosestBalloon(); //method in Attacker class to find closest balloon to pop
    closestBalloon.updateHealth(specialBullet.getStrength()); //method in Target class to update health after being hit by bullet
  }
}