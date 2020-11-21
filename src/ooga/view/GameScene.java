package ooga.view;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.PropertiesReader;
import ooga.controller.utilities.CoordinateConverter;
import ooga.controller.utilities.KeyHandler;
import ooga.model.Engine;
import ooga.view.displays.CoinDisplay;
import ooga.view.displays.HealthDisplay;
import ooga.view.displays.StatusDisplay;
import ooga.view.screens.GameOver;
import ooga.view.utilities.ViewUtilities;


public class GameScene implements Engine, TargetView {

  public static final String PROPERTIES_FILENAME = "View/Game.properties";
  public static final String STYLESHEET = "View/GameScene.css";
  public static final String TARGET_VALUES_FILENAME = "View/TargetValues.properties";
  public static final String SCREEN_STYLESHEET = "View/Screen.css";
  private final int sceneWidth;
  private final int sceneHeight;
  private final Stage stage;
  private final Properties properties;
  private final Map<ObjectView, String> towers = new HashMap<ObjectView, String>();
  private final Map<List<Integer>, ObjectView> newTowers = new HashMap<List<Integer>, ObjectView>();
  private final ArrayList<StatusDisplay> statusDisplays;
  private final List<String[]> readInFile;
  private final Set<Updatable> moveable;
  private final Set<Updatable> removable = new HashSet<>();
  private Timeline timeline;
  private double duration;
  private final String invalid;
  private int gameWidth;
  private int gameHeight;
  private ViewGrid viewGrid;
  private HBox display;
  private Shop shop;
  private HealthDisplay healthDisplay;
  private Group root;
  private CoinDisplay coinDisplay;
  private final MediaPlayer musicPlayer;
  private final Media gameMusic;
  private final KeyHandler keyHandler;


  /**
   * This class is the main visualization for all front-end
   *
   * @param stage
   */

  public GameScene(Stage stage, List<String[]> readFile) {
    this.stage = stage;
    this.readInFile = readFile;
    root = new Group();
    moveable = new HashSet<>();
    statusDisplays = new ArrayList<>();
    PropertiesReader propertiesReader = new PropertiesReader();
    properties = (Properties) propertiesReader.read(PROPERTIES_FILENAME);
    sceneWidth = Integer.parseInt(properties.getProperty("SceneWidth"));
    sceneHeight = Integer.parseInt(properties.getProperty("SceneHeight"));
    Scene scene = new Scene(root, sceneWidth, sceneHeight);
    invalid = properties.getProperty("invalid");
    scene.getStylesheets().addAll(STYLESHEET, SCREEN_STYLESHEET);
    gameMusic = new Media(new File(properties.getProperty("GameMusic")).toURI().toString());
    musicPlayer = new MediaPlayer(gameMusic);
    setUpScene();
    stage.setScene(scene);
    stage.show();
    keyHandler = new KeyHandler();
    scene.setOnKeyPressed(e -> keyHandler.handleKeyInput((e.getCode())));
  }


  private void setUpScene() {
    ImageView background = ViewUtilities.createImageView(properties.getProperty("background"));
    gameWidth = Integer.parseInt(properties.getProperty("GameWidth"));
    gameHeight = Integer.parseInt(properties.getProperty("GameHeight"));
    background.setFitWidth(gameWidth);
    background.setFitHeight(gameHeight);
    root.getChildren().add(background);
    viewGrid = new ViewGrid(readInFile);
    display = viewGrid.getGameWithShop();
    shop = viewGrid.getShop();
    coinDisplay = shop.getCoinDisplay();
    healthDisplay = shop.getHealthDisplay();
    statusDisplays.add(coinDisplay);
    statusDisplays.add(healthDisplay);
    root.getChildren().add(display);
    timeline = new Timeline();
    duration = Double.parseDouble(properties.getProperty("MovementDuration"));
    playMusic();
    //timeline.setCycleCount(Timeline.INDEFINITE);
  }

  /**
   * This method updates the balloons location(?)
   */
  @Override
  public void movePieces() {
    for (Updatable object : moveable) {
      moveAnimation((ObjectView) object);
      object.moveObject();
    }
  }

  private void moveAnimation(ObjectView object) {
    // TODO: Update controller so all balloons are not added at once

    KeyValue endKeyValueX = new KeyValue(object.xProperty(), object.getNextRow());
    KeyValue endKeyValueY = new KeyValue(object.yProperty(), object.getNextCol());
    KeyFrame endKeyFrame = new KeyFrame(Duration.seconds(duration), endKeyValueX, endKeyValueY);
    Timeline timeline = new Timeline(endKeyFrame);
    timeline.play();
  }


  /**
   * This method updates the a list from the grid of the placed towers
   */

  //TODO: make this cleaner & write a test -- look at bigger grid
  public void updateGrid() {
    List<Node> cells = onlyGrid();
    VBox grid = (VBox) cells.get(0);
    List<Node> rows = grid.getChildren();
    for (int row = 0; row < rows.size(); row++) {
      HBox cellCol = (HBox) rows.get(row);
      List<Node> columns = cellCol.getChildren();
      for (int col = 0; col < columns.size(); col++) {
        String id = columns.get(col).getId();
        if (!invalid.contains(id)) {
          ArrayList<Integer> coordinates = new ArrayList<>();
          coordinates.add(row);
          coordinates.add(col);
          String coordinateString = row + "," + col;
          if (!towers.containsValue(coordinateString)) {
            towers.put((ObjectView) columns.get(col), coordinateString);
            newTowers.put(coordinates, (ObjectView) columns.get(col));
          }
        }
      }
    }
  }


  private List<Node> onlyGrid() {
    List<Node> cells = display.getChildren();
    List<Node> noShop = new ArrayList<>();
    for (Node cell : cells) {
      if (properties.getProperty("Remove").contains(cell.getId())) {
        continue;
      }
      noShop.add(cell);
    }
    return noShop;

  }

  //front-end list of balloons
  @Override
  public void addTarget(ObjectView balloon, double xPos, double yPos) {
    balloon.setX(xPos);
    balloon.setY(yPos);
    moveable.add(balloon);
    root.getChildren().add(balloon);
  }

  /**
   * This method shoots a bullet from given X and Y coordinates to the balloon
   *
   * @param tower
   * @param target
   */
  public void isPopped(ObjectView tower, ObjectView target, boolean popped) {
    PropertiesReader targetValuesReader = new PropertiesReader();
    Properties targetValues = (Properties) targetValuesReader.read(TARGET_VALUES_FILENAME);
    Properties viableAttackersForTarget = (Properties) targetValuesReader.read("model/targets/"+ target.getId().substring(0,target.getId().length()-1) + ".properties");



    Timeline timeline = new Timeline();
    popBalloon(target);
    ImageView bullet = shootBullet(tower, target, timeline);

    timeline.setOnFinished(e -> {
      root.getChildren().remove(bullet);

      String viableAttackersString = (String)viableAttackersForTarget.get("ViableAttackers");

      if(!properties.getProperty("invalid").contains(ViewUtilities.retrieveIdEquals(tower.getId())) && viableAttackersString.indexOf(ViewUtilities.retrieveIdEquals(tower.getId()))>-1) {
        coinDisplay.update(Integer.parseInt(targetValues.getProperty(target.getId())));
      }

      if (popped) {
        target.setId("popped");
      }
    });
  }

  private ImageView shootBullet(ObjectView tower, ObjectView target, Timeline timeline) {
    ImageView bullet = createBullet();

    double[] attackerCoords = getTowerCoordinates(tower);
    bullet.setX(attackerCoords[0]);
    bullet.setY(attackerCoords[1]);

    root.getChildren().add(bullet);

    KeyValue shootX = new KeyValue(bullet.xProperty(), target.getX());
    KeyValue shootY = new KeyValue(bullet.yProperty(), target.getY());
    KeyFrame shootingKeyFrame = new KeyFrame(Duration.seconds(Double.parseDouble(
        properties.getProperty("PopDuration"))), shootX, shootY);
    timeline.getKeyFrames().addAll(shootingKeyFrame);
    timeline.play();

    timeline.setOnFinished(e -> {
      root.getChildren().remove(bullet);
    });

    return bullet;
  }

  private void popBalloon(ObjectView balloon) {
    balloon.setFill(new ImagePattern(new Image(properties.getProperty("popped"))));
    removable.add(balloon);
  }

  private ImageView createBullet() {
    ImageView bullet = ViewUtilities.createImageView(properties.getProperty("bullet"));

    bullet.setPreserveRatio(true);
    bullet.setFitWidth(Integer.parseInt(properties.getProperty("BulletWidth")));
    bullet.setFitHeight(Integer.parseInt(properties.getProperty("BulletHeight")));

    return bullet;
  }

  private double[] getTowerCoordinates(ObjectView tower) {
    CoordinateConverter coordinateConverter =
        new CoordinateConverter(viewGrid.getRows(), viewGrid.getColumns());
    String[] coordinates = towers.get(tower).split(",");
    return coordinateConverter.gridToPixels(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
  }

  @Override
  public void removeTarget(ObjectView balloon) {
    balloon.setId("offScreen");
    //root.getChildren().remove(balloon);
    moveable.remove(balloon);
    removable.add(balloon);
    if (moveable.size() == 0) {
      makeNewScene();
    }
  }

  private void makeNewScene() {
    root.getChildren().remove(removable);
    Group tempRoot = new Group();
    tempRoot.getChildren().addAll(root.getChildren());
    root = tempRoot;
    Scene tempScene = new Scene(root, sceneWidth, sceneHeight);
    tempScene.getStylesheets().add(STYLESHEET);
    stage.setScene(tempScene);
  }

  public void addGameScreen(Boolean isWon) {
    GameOver gameOver = new GameOver(this.root, stage, isWon);
  }

  public void playMusic() {
    musicPlayer.play();
    AtomicBoolean isPaused = new AtomicBoolean(false);

    ImageView speaker = ViewUtilities.createImageView("Images/Speaker.png");
    root.getChildren().add(speaker);
    speaker.setOnMouseClicked(e -> {
      if (isPaused.get() == false) {
        speaker.setImage(pauseMusic().getImage());
        isPaused.set(true);
      } else {
        speaker.setImage(ViewUtilities.createImageView("Images/Speaker.png").getImage());
        musicPlayer.play();
        isPaused.set(false);
      }
    });
  }

  private ImageView pauseMusic() {
    musicPlayer.pause();
    ImageView mutedSpeaker = ViewUtilities.createImageView("Images/MutedSpeaker.png");
    return mutedSpeaker;
  }

  public Group getRoot() {
    return root;
  }

  public MediaPlayer getMusicPlayer() {
    return musicPlayer;
  }

  public Map<List<Integer>, ObjectView> getNewAttackers() {
    return newTowers;
  }

  public void clearNewAttackers() {
    newTowers.clear();
  }

  public ArrayList<StatusDisplay> getStatusDisplays() {
    return statusDisplays;
  }

  public KeyHandler getKeyHandler() {
    return keyHandler;
  }
}

