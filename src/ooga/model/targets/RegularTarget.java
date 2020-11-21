package ooga.model.targets;

import ooga.model.GamePiece;

public class RegularTarget extends Target {

  public RegularTarget(int xPosition, int yPosition, int balloonState, int health, double speed,
      String id) {
    super(xPosition, yPosition, balloonState, health, speed, id);
  }

  @Override
  public void update(GamePiece attacker){
    /**
     * decrement the state, call a helper method that will check if it pops
     */
    String viableAttackersList = (String)this.getViableAttackerProperties().get("ViableAttackers");
    System.out.println(viableAttackersList);

    if(viableAttackersList.indexOf(attacker.getClass().getSimpleName())>-1){
      this.setHealth(this.getHealth()-1);
      if (isPopped()) {
        pop();
      }
    }
  }

}
