package ooga.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.PropertiesReader;
import ooga.controller.utilities.BidirectionalMap;
import ooga.controller.utilities.CoordinateConverter;
import ooga.controller.utilities.DisplayUpdateFactory;
import ooga.model.Driver;
import ooga.model.GamePiece;
import ooga.model.Player;
import ooga.model.attackers.Attacker;
import ooga.model.targets.Target;
import ooga.view.GameScene;
import ooga.view.ObjectView;
import ooga.view.Updatable;
import ooga.view.displays.StatusDisplay;

public class Controller {

  private static final String GAME_PROPERTIES = "View/Game.properties";
  private static final String TEXT_PROPERTIES = "text/ControllerText.properties";
  private final Driver myDriver;
  private final GameScene myGameScene;
  private final ArrayList<StatusDisplay> statusDisplays;
  private final Player myPlayer;
  private final BidirectionalMap<GamePiece, Updatable> objectMap = new BidirectionalMap<>();
  private final CoordinateConverter myCoordinateConverter;
  private final PropertiesReader propertiesReader;
  private final Properties gameProperties;
  int startBalloonCount = 1;
  private Map<List<Integer>, ObjectView> newTowers;
  private Timeline timeline;
  private boolean isPaused;
  private int level = 1;

  private int startingBalloonCount;
  private boolean levelOver = false;
  private int stepCounter = 0;
  private Properties textProperties;

  public Controller(Stage primaryStage, String filename, int level) throws FileNotFoundException {
    myDriver = new Driver(filename);
    myGameScene = new GameScene(primaryStage, myDriver.getGrid().getReadInFile());
    myPlayer = myDriver.getMyPlayer();
    statusDisplays = myGameScene.getStatusDisplays();
    myCoordinateConverter = new CoordinateConverter(myDriver.getGrid().getGridRows(),
    myDriver.getGrid().getGridCols());
    propertiesReader = new PropertiesReader();
    gameProperties = (Properties) propertiesReader.read(GAME_PROPERTIES);
    textProperties = (Properties)propertiesReader.read(TEXT_PROPERTIES);
    myDriver.createProperties(level);
    addCheatKeys();
    startTimeline();
  }

  public void startTimeline() {
    KeyFrame frame = new KeyFrame(Duration.seconds(Double.parseDouble(
    gameProperties.getProperty("MovementDuration"))), e -> step());
    timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(frame);
    timeline.play();
  }

  public void step() {
    stepCounter++;
    gameLost();
    moveAndUpdatePieces();
    updateStatusDisplays();
    checkLevelOver();
    checkGameOver();
  }

  private void moveAndUpdatePieces() {
    myDriver.movePieces();
    newTowers = myGameScene.getNewAttackers();
    createCorrectNumTargets();
    updateBalloons();
    myGameScene.movePieces();
    newTowers = myGameScene.getNewAttackers();
    myGameScene.updateGrid();
    createAttackers();
    myGameScene.clearNewAttackers();
  }


  private void createCorrectNumTargets() {
    if (startBalloonCount++ <= myDriver.getNumTargets()) {
      createBalloons();
    }
  }

  private void checkGameOver() {
    if (levelOver && !checkGameLost()) {
      level++;
      if (level > Integer.parseInt(gameProperties.getProperty("maximumLevel"))) {
        timeline.pause();
        myGameScene.addGameScreen(true);
      } else {
        switchLevel(level);
      }
    }
  }

  private void updateStatusDisplays() {
    DisplayUpdateFactory displayUpdateFactory = new DisplayUpdateFactory(myPlayer);
    for (StatusDisplay display : statusDisplays) {
      displayUpdateFactory.updateDisplay(display);
    }
  }

  private void gameLost() {
    if (checkGameLost()) {
      myGameScene.addGameScreen(false);
      timeline.pause();
    }
  }

  private Boolean checkGameLost() {
    return (myPlayer.getHealth() == 0);
  }


  private void checkLevelOver() {
    if (startBalloonCount >= myDriver.getNumTargets() &&
            (myDriver.getNumTargets() == myDriver.getTotalRemovedTargets() + myDriver
                    .getTotalDefeatedTargets())) {
      levelOver = true;
    } else {
      levelOver = false;
    }
  }

  public void switchLevel(int levelNumber) {
      myDriver.createProperties(levelNumber);
      startBalloonCount = 1;
      myDriver.setTotalDefeatedTargets(0);
      myDriver.setTotalRemovedTargets(0);

  }

  private void addCheatKeys() {
    myGameScene.getKeyHandler().addCheatKeys(KeyCode.DIGIT1, () -> switchLevel(1));
    myGameScene.getKeyHandler().addCheatKeys(KeyCode.DIGIT2, () -> switchLevel(2));
    myGameScene.getKeyHandler().addCheatKeys(KeyCode.DIGIT3, () -> switchLevel(3));
    myGameScene.getKeyHandler().addCheatKeys(KeyCode.SPACE, () -> {
      if (isPaused) {
        timeline.play();
      } else {
        timeline.pause();
      }
      isPaused = !isPaused;
    });
  }

  private void updateBalloons() { // show them on the front-end & update position


    gameLost();
    shootTargets();
    removeOffScreenTargets();
  }



  private void removeOffScreenTargets() {
    Iterator mapIterator = objectMap.iterator();

    while (mapIterator.hasNext()) {
      GamePiece piece = (GamePiece) mapIterator.next();
      if (myDriver.getRemovedTargets().contains(piece)) {
        gameLost();
        myGameScene.removeTarget((ObjectView) objectMap.get(piece));
      } else {
        double[] pixArray = myCoordinateConverter.gridToPixels(piece.getX(), piece.getY());
        objectMap.get(piece).updateNextLocation(pixArray[0], pixArray[1]);
      }
    }
    objectMap.removeAll(myDriver.getRemovedTargets());
    myDriver.clearRemovedTargets();
  }

  private void shootTargets() {
    for (Attacker attacker : myDriver.getAttackerList()) {
      if (attacker.canShoot(stepCounter)) {
        Set<Target> targetList = myDriver.checkTargetsForWithinAttackersRange(attacker);
        for (Target target : targetList) {

          Updatable targetView = objectMap.get(target);
          Updatable attackerView = objectMap.get(attacker);

          ObjectView realAttackerView = (ObjectView) attackerView;
          ObjectView realTargetView = (ObjectView) targetView;
          myGameScene.isPopped(realAttackerView, realTargetView, target.isPopped());
          if(!target.isPopped()) {
            objectMap.get(target).updateImage(target.getId() + target.getHealth());
          }
        }
      }
    }
    myDriver.clearAttackersAndTheirTargets();
    objectMap.removeAll(myDriver.getRecentDefeatedTargets());
    myDriver.clearRecentDefeatedTargets();
  }

  private void createBalloons() {
    gameLost();
    Set<Target> targetSet = myDriver.createTargets(Integer.parseInt(
        gameProperties.getProperty("StartingTargetCount")));

    for (Target target : targetSet) {
      ObjectView balloonView = new ObjectView(target.getId() + target.getHealth());
      objectMap.put(target, balloonView);

      double[] pixArray = myCoordinateConverter.gridToPixels(target.getX(), target.getY());
      myGameScene.addTarget(balloonView, pixArray[0], pixArray[1]);
    }
  }

  public void createAttackers() {
    try {
      Set<Attacker> newAttackerSet = new HashSet<>();
      for (List<Integer> cord : newTowers.keySet()) {
        String id = newTowers.get(cord).getId().substring(3);
        String className = textProperties.get("attackerClass") + id;
        Object newAttacker = Class.forName(className).getConstructors()[1]
            .newInstance(cord.get(0), cord.get(1));
        Attacker newRealAttacker = (Attacker) newAttacker;
        newAttackerSet.add(newRealAttacker);
        objectMap.put(newRealAttacker, newTowers.get(cord));
      }
      myDriver.addNewAttackers(newAttackerSet);
      myGameScene.clearNewAttackers();
      newTowers.clear();

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      System.out.println("Class Not Found");
   }
  }

  public Driver getMyDriver() {
    return myDriver;
  }

  public GameScene getMyGameScene() {
    return myGameScene;
  }
}
