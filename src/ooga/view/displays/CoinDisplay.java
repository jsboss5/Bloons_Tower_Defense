package ooga.view.displays;

import java.util.Properties;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ooga.PropertiesReader;
import ooga.view.utilities.ViewUtilities;

public class CoinDisplay extends HBox implements StatusDisplay {

  public static final String IMAGE_PROPERTIES = "View/ObjectImages.properties";
  public static final String STATUS_DISPLAY_PROPERTIES = "View/StatusDisplay.properties";
  public static final String STYLESHEET = "View/StatusDisplay.css";

  private final Label coinDisplay;
  private final Properties imageProperties;
  private final Properties statusDisplayProperties;
  private int coins;

  public CoinDisplay() {
    super();
    this.setId("CoinDisplay");

    PropertiesReader propertiesReader = new PropertiesReader();
    imageProperties = (Properties) propertiesReader.read(IMAGE_PROPERTIES);
    statusDisplayProperties = (Properties) propertiesReader.read(STATUS_DISPLAY_PROPERTIES);

    ImageView coin = ViewUtilities.createImageView(imageProperties.getProperty("coins"));
    coin.setId("coin");
    coin.setFitWidth(Integer.parseInt(statusDisplayProperties.getProperty(coin.getId() + "Size")));
    coin.setFitHeight(Integer.parseInt(statusDisplayProperties.getProperty(coin.getId() + "Size")));
    int startingCoins = Integer.parseInt(statusDisplayProperties.getProperty("StartingCoins"));
    coinDisplay = ViewUtilities.createLabel("CoinDisplay", String.valueOf(startingCoins));
    coins = startingCoins;

    this.getChildren().add(coin);
    this.getChildren().add(coinDisplay);
    this.getStylesheets().add(STYLESHEET);
  }

  public Label getCoinDisplay() {
    return coinDisplay;
  }

  @Override
  public void update(int status) {
    coins += status;
    coinDisplay.setText(String.valueOf(coins));
  }

  @Override
  public int getValue() {
    return coins;
  }

}
