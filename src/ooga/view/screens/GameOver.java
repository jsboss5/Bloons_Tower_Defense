package ooga.view.screens;

import java.util.Properties;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.view.utilities.ViewUtilities;

public class GameOver extends Screen {

  public static final String SCREEN_PROPERTIES = "View/Screen.properties";
  public static final String GAME_PROPERTIES = "View/Game.properties";
  public static final String LEVELS_FILENAME = "View/Levels.properties";
  public static final String SCREEN_STYLESHEET = "View/Screen.css";

  private final Properties properties;
  private final PropertiesReader propertiesReader;
  private Properties gameProperties;
  private final Group root;
  private final Stage stage;
  private int currentLevel;
  private String image;

  public GameOver(Group root, Stage stage, Boolean isWon) {
    propertiesReader = new PropertiesReader();
    properties = (Properties) propertiesReader.read(SCREEN_PROPERTIES);
    this.root = root;
    this.stage = stage;
    getWonLost(isWon);
    setUp();
  }

  @Override
  public void setUp() {
    ImageView gameOver = ViewUtilities.createImageView(gameProperties.getProperty("GameOver"));
    gameOver.setId("GameOver");
    gameOver.setY(getYSize(gameOver, gameProperties));
    gameOver.setX(getXSize(gameOver, gameProperties));
    gameOver
        .setFitHeight(Integer.parseInt(gameProperties.getProperty(gameOver.getId() + "Height")));
    gameOver.setFitWidth(Integer.parseInt(gameProperties.getProperty(gameOver.getId() + "Width")));
    root.getChildren().add(gameOver);
    Button startOver = createButton("playAgain", gameProperties.getProperty("playAgain"));
    nodeResize(startOver, gameProperties);
    root.getChildren().add(startOver);
    Label levelOver = ViewUtilities
        .createLabel("levelOver", gameProperties.getProperty("levelOver"));
    nodeResize(levelOver, gameProperties);
    root.getChildren().add(levelOver);

  }

  @Override
  public void goToNextScreen() {
    stage.setScene(null);
    stage.close();
    Homepage homepage = new Homepage(stage);


  }

  private void getWonLost(Boolean isWon) {
    if (isWon) {
      gameProperties = (Properties) propertiesReader.read(properties.getProperty("GameWon"));
    } else {
      gameProperties = (Properties) propertiesReader.read(properties.getProperty("GameOver"));
    }
  }

  public Group getRoot() {
    return root;
  }

}
