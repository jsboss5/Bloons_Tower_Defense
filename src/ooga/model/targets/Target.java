package ooga.model.targets;

import java.util.Properties;
import ooga.PropertiesReader;
import ooga.model.GamePiece;

public abstract class Target implements GamePiece {

  public final static String GAME_PIECE_PROPERTIES = "View/GamePieceProperties.properties";
  private final int poppedState;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  private double speed;  //cells per step... .45, .02, blah, blah
  private double xPos;
  private double yPos;
  private int state;
  private int health;
  private String id;
  private int stepCounter = 0;
  private final int STEP_DELAY = 10;
  private final double STARTING_SPEED;
  private boolean isGlued;



  private Properties viableAttackerProperties;

  public Target(int xPosition, int yPosition, int balloonState, int balloonHealth, double ballSpeed,
      String ID) {

    xPos = xPosition;
    yPos = yPosition;
    state = balloonState;
    health = balloonHealth;
    speed = ballSpeed;
    id = ID;
    STARTING_SPEED = ballSpeed;

    PropertiesReader propertiesReader = new PropertiesReader();
    Properties gamePieceProperties = (Properties) propertiesReader.read(GAME_PIECE_PROPERTIES);

    viableAttackerProperties = (Properties) propertiesReader.read("model/targets/"+ id+".properties");

    poppedState = Integer.parseInt(gamePieceProperties.getProperty("PoppedTargetState"));

  }

  @Override
  public abstract void update(GamePiece piece);

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
    if (stepCounter != 0) {
      stepCounter--;
    } else {
      setSpeed(STARTING_SPEED);
    }

    if (nextX > this.xPos) {
      this.xPos = this.xPos + this.speed;
    } else if (nextX < this.xPos) {
      this.xPos = this.xPos - this.speed;
    } else if (nextY > this.yPos) {
      this.yPos = this.yPos + this.speed;
    } else if (nextY < this.yPos) {
      this.yPos = this.yPos - this.speed;
    }
  }

  public void pop() {
    this.state = poppedState;
  }

  public void setxPos(double xPos) {
    this.xPos = xPos;
  }


  public void setyPos(double yPos) {
    this.yPos = yPos;
  }

  @Override
  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getHealth() {
    return this.health;
  }

  public void setHealth(int newHealth){this.health = newHealth;}

  public double getSpeed() {
    return speed;
  }

  public boolean isPopped() {
    return health == 0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void startStepCounter() {
    stepCounter = STEP_DELAY;
  }

  public void glue() {
    isGlued = true;
  }

  public boolean isGlued() {
    return isGlued;
  }
  public Properties getViableAttackerProperties() {
    return viableAttackerProperties;
  }
}
