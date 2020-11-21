package ooga.view.screens;

import java.util.Properties;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.controller.Controller;
import ooga.view.utilities.ViewUtilities;

public class Homepage extends Screen {

  public static final String SCREEN_PROPERTIES = "View/Screen.properties";
  public static final String GAME_PROPERTIES = "View/Game.properties";
  public static final String SCREEN_STYLESHEET = "View/Screen.css";

  private final Stage stage;
  private final int sceneWidth;
  private final int sceneHeight;
  private final Group root;
  private final Properties properties;
  private final Properties gameProperties;
  private final PropertiesReader propertiesReader;

  public Homepage(Stage stage) {
    this.stage = stage;
    propertiesReader = new PropertiesReader();
    properties = (Properties) propertiesReader.read(SCREEN_PROPERTIES);
    root = new Group();
    gameProperties = (Properties) propertiesReader.read(GAME_PROPERTIES);
    sceneWidth = Integer.parseInt(gameProperties.getProperty("SceneWidth"));
    sceneHeight = Integer.parseInt(gameProperties.getProperty("SceneHeight"));
    setUp();
    Scene scene = new Scene(root, sceneWidth, sceneHeight);
    scene.getStylesheets().add(SCREEN_STYLESHEET);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void setUp() {
    ImageView background = ViewUtilities.createImageView(properties.getProperty("Homepage"));
    background.setFitWidth(sceneWidth);
    background.setFitHeight(sceneHeight);
    root.getChildren().add(background);
    Button enter = createButton("enterGame", properties.getProperty("enterGame"));
    nodeResize(enter, properties);
    root.getChildren().add(enter);
  }

  @Override
  public void goToNextScreen() {
    MapChooser mapChooser = new MapChooser(stage);
  }

}
