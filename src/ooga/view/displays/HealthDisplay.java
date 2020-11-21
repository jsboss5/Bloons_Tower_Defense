package ooga.view.displays;

import java.util.Properties;
import javafx.scene.shape.Rectangle;
import ooga.PropertiesReader;

public class HealthDisplay extends Rectangle implements StatusDisplay {

  public static final String STATUS_DISPLAY_FILENAME = "View/StatusDisplay.properties";
  public static final int DEFAULT_SIZE = 30;
  private final Properties statusDisplayProperties;
  private final int maxHealth;
  private final double maxWidth;
  private float currentHealth;

  public HealthDisplay() {
    super(DEFAULT_SIZE, DEFAULT_SIZE);
    this.setId("HealthDisplay");

    PropertiesReader propertiesReader = new PropertiesReader();
    statusDisplayProperties = (Properties) propertiesReader.read(STATUS_DISPLAY_FILENAME);

    maxWidth = Double.parseDouble(statusDisplayProperties.getProperty("HealthDisplayMaxWidth"));
    this.setWidth(maxWidth);
    this.setHeight(Double.parseDouble(statusDisplayProperties.getProperty("HealthDisplayHeight")));

    maxHealth = Integer.parseInt(statusDisplayProperties.getProperty("MaxHealth"));
    currentHealth = maxHealth;

    //this.setFill();
  }


  public float getPercentage() {
    return currentHealth / maxHealth;
  }

  public float getHealthDisplayFill() {
    return getPercentage() * (float) maxWidth;
  }

  public float getCurrentHealth() {
    return currentHealth;
  }

  @Override
  public void update(int status) {
    currentHealth = status;
    this.widthProperty().set(getHealthDisplayFill());
  }

  @Override
  public int getValue() {
    return (int) currentHealth;
  }

}
