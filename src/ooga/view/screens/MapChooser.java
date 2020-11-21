package ooga.view.screens;

import java.io.FileNotFoundException;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.controller.Controller;
import ooga.view.utilities.ViewUtilities;

public class MapChooser extends Screen {

    public static final String SCREEN_PROPERTIES = "View/Screen.properties";
    public static final String MAP_PROPERTIES = "View/MapSelections.properties";
    public static final String MAP_IMAGES = "View/MapImages.properties";
    public static final String SCREEN_STYLESHEET = "View/Screen.css";

    private Properties mapProperties;
    private Properties screenProperties;
    private PropertiesReader propertiesReader;
    private Properties mapImages;
    private Group root;
    private Stage stage;
    private int sceneWidth;
    private int sceneHeight;
    private String propertyFile;
    private ImageView background;
    private String defaultBackground;
    private int gameWidth;

    public MapChooser(Stage stage){
        this.stage = stage;
        propertiesReader = new PropertiesReader();
        root = new Group();
        mapProperties = (Properties) propertiesReader.read(MAP_PROPERTIES);
        screenProperties = (Properties) propertiesReader.read(SCREEN_PROPERTIES);
        mapImages = (Properties) propertiesReader.read(MAP_IMAGES);
        sceneWidth = Integer.parseInt(screenProperties.getProperty("SceneWidth"));
        sceneHeight = Integer.parseInt(screenProperties.getProperty("SceneHeight"));
        gameWidth = Integer.parseInt(screenProperties.getProperty("GameWidth"));
        defaultBackground = screenProperties.getProperty("Default");
        setUp();
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        scene.getStylesheets().add(SCREEN_STYLESHEET);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setUp() {
        background = ViewUtilities.createImageView(mapImages.getProperty(defaultBackground));
        background.setFitHeight(sceneHeight);
        background.setFitWidth(sceneWidth);
        root.getChildren().add(background);
        VBox titleText = new VBox();
        Label label = ViewUtilities.createLabel("title", screenProperties.getProperty("title"));
        titleText.getChildren().add(label);
        HBox options = new HBox(Integer.parseInt(screenProperties.getProperty("Spacing")));
        options.setPrefWidth(sceneWidth);
        options.setAlignment(Pos.CENTER);
        titleText.setAlignment(Pos.CENTER);
        for (String mapOptions : mapProperties.stringPropertyNames()) {
            ImageView map = ViewUtilities.createImageView(mapImages.getProperty(mapOptions));
            map.setId(mapOptions);
            setImageViewFactors(map);
            options.getChildren().add(map);
        }
        titleText.getChildren().add(options);
        Label click = ViewUtilities.createLabel("click", screenProperties.getProperty("click"));
        titleText.getChildren().add(click);
        root.getChildren().add(titleText);
    }

    private void setImageViewFactors(ImageView map) {
        map.setFitHeight(sceneHeight/mapProperties.size());
        map.setFitWidth(gameWidth/mapProperties.size());
        map.setOnMouseEntered(event -> changeBackground(map.getId()));
        map.setOnMouseExited(event -> changeBackground(defaultBackground));
        map.setOnMouseClicked(event -> selectPropertyFiles(map.getId()));
    }

    private void changeBackground(String id) {
        background.setImage(ViewUtilities.createImage(mapImages.getProperty(id)));
    }

  public void selectPropertyFiles(String id) {
    propertyFile = mapProperties.getProperty(id);
    goToNextScreen();
  }


  @Override
  public void goToNextScreen() {
    Properties levels = (Properties) propertiesReader.read(propertyFile);
    try {
      Controller controller = new Controller(stage, levels.getProperty("Level1"),
          Integer.parseInt(levels.getProperty("Level1Number")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      //TODO: add custom exception
    }
  }
}
