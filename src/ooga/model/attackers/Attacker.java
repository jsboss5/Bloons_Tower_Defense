package ooga.model.attackers;

import ooga.model.GamePiece;
import ooga.model.targets.Target;

public abstract class Attacker implements GamePiece {


  private final double xPos;
  private final double yPos;
  private int rank;

  private int firingRate;
  private int range;
  private int numTargets;

  public Attacker(int xPosition, int yPosition, int towerRank, int towerRange, int targets,
      int speed) {
    xPos = xPosition;
    yPos = yPosition;
    rank = towerRank;
    range = towerRange;
    numTargets = targets;
    firingRate = speed;
  }

  public Attacker(int xPosition, int yPosition) {
    xPos = xPosition;
    yPos = yPosition;
  }


  @Override
  public abstract void update(GamePiece piece);

  public void upgrade() {
    rank += 1;
  }

  @Override
  public double getX() {
    return xPos;
  }

  @Override
  public double getY() {
    return yPos;
  }

  @Override
  public void move(int nextX, int nextY) {
  }

  //TODO: With more time, add a cheat key to move attackers

  @Override
  public int getState() {
    return rank;
  }


  public int getNumTargets() {
    return numTargets;
  }

  public void setNumTargets(int numTargets) {
    this.numTargets = numTargets;
  }

  public int getRange() {
    return range;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public boolean canShoot(int stepCounter) {
    return stepCounter % firingRate == 0;
  }

  public void setFiringRate(int speed) {
    this.firingRate = speed;
  }

  public int getFiringRate(){
    return firingRate;
  }

  //public abstract Target shoot(Target target);


}
