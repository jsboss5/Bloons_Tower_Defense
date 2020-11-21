package ooga.model.targets;

import ooga.model.GamePiece;
import ooga.model.attackers.Attacker;

public class CamoTarget extends Target {

  public CamoTarget(int xPosition, int yPosition, int balloonState, int health, double speed,
      String id) {
    super(xPosition, yPosition, balloonState, health, speed, id);
  }

  @Override
  public void update(GamePiece attacker){
  }
}
