package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.model.Player;
import ooga.view.displays.CoinDisplay;
import ooga.view.displays.HealthDisplay;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Properties;

public class HealthDisplayTest extends DukeApplicationTest {

  public static final String STATUS_DISPLAY_FILENAME = "View/StatusDisplay.properties";

  private Properties imageProperties;
  private Properties statusDisplayProperties;
  private Player myPlayer;
  private Shop myShop;
  private HealthDisplay healthDisplay;
  private Scene scene;
  private Group root;

  public void start(Stage stage) {
    myShop = new Shop();
    PropertiesReader propertiesReader = new PropertiesReader();
    statusDisplayProperties = (Properties) propertiesReader.read(STATUS_DISPLAY_FILENAME);
    healthDisplay = new HealthDisplay();
    root = new Group();
    root.getChildren().add(healthDisplay);
    scene = new Scene(root,200,200);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  public void testGetPercentage() {
    int maxHealth = Integer.parseInt(statusDisplayProperties.getProperty("MaxHealth"));
    assertEquals(maxHealth/healthDisplay.getCurrentHealth(),healthDisplay.getPercentage());
  }

  @Test
  public void testGetDisplayFill(){
    Double maxWidth = Double.parseDouble(statusDisplayProperties.getProperty("HealthDisplayMaxWidth"));
    float displayFill = (float) (healthDisplay.getPercentage() * maxWidth);
    assertEquals(displayFill,healthDisplay.getHealthDisplayFill());
  }

  @Test
  public void testGetCurrentHealth(){
    int maxHealth = Integer.parseInt(statusDisplayProperties.getProperty("MaxHealth"));
    assertEquals(maxHealth,healthDisplay.getCurrentHealth());
  }

  @Test
  public void testUpdate(){
    healthDisplay.update(3);
    assertEquals(healthDisplay.getCurrentHealth(),3);
  }

  @Test
  public void testGetValue(){
    assertEquals(healthDisplay.getValue(),healthDisplay.getCurrentHealth());
  }

}
