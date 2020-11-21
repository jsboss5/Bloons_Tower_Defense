package ooga.model;

import java.util.Properties;
import ooga.PropertiesReader;

public class Player {

  private int numCoins;
  private int health;

  public Player() {
    PropertiesReader propertiesReader = new PropertiesReader();
    Properties playerProperties = (Properties) propertiesReader.read(
        "View/StatusDisplay.properties");

    numCoins = Integer.parseInt(playerProperties.getProperty("StartingCoins"));
    health = Integer.parseInt(playerProperties.getProperty("MaxHealth"));
  }

  public void updateCoins(int targetValue) {
    numCoins += targetValue;
  }

  public void updateHealth(int targetValue) {
    health -= targetValue;
  }

  public int getCoins() {
    return this.numCoins;
  }

  public int getHealth() {
    return this.health;
  }
}
