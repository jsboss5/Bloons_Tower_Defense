package ooga.model.targets;

import ooga.model.GamePiece;
import ooga.model.attackers.Attacker;

public class MetalTarget extends Target {

  public MetalTarget(int xPosition, int yPosition, int balloonState, int health, double speed,
      String id) {
    super(xPosition, yPosition, balloonState, health, speed, id);
  }

  @Override
  public void update(GamePiece attacker){
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