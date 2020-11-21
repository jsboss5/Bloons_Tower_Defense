package ooga.view;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.view.displays.CoinDisplay;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Properties;

public class CoinDisplayTest extends DukeApplicationTest {

    public static final String IMAGE_PROPERTIES = "View/ObjectImages.properties";
    public static final String STATUS_DISPLAY_PROPERTIES = "View/StatusDisplay.properties";

    private Properties imageProperties;
    private Properties statusDisplayProperties;

    public void start(Stage stage) {
        PropertiesReader propertiesReader = new PropertiesReader();
        imageProperties = (Properties) propertiesReader.read(IMAGE_PROPERTIES);
        statusDisplayProperties = (Properties) propertiesReader.read(STATUS_DISPLAY_PROPERTIES);
        stage.show();
    }

    @Test
    public void testCoinDisplayNotNull() {
    CoinDisplay playerCoins = new CoinDisplay();
    Label coinLabel = playerCoins.getCoinDisplay();
    assertEquals(statusDisplayProperties.getProperty("StartingCoins"), coinLabel.getText());
  }

  @Test
  public void testCoinsDecrease(){
        CoinDisplay playerCoins = new CoinDisplay();
        playerCoins.update(-3);
        int newValue = Integer.parseInt(statusDisplayProperties.getProperty("StartingCoins"));
        assertEquals(newValue -3, Integer.parseInt(playerCoins.getCoinDisplay().getText()));

  }
    @Test
    public void testCoinsIncrease(){
        CoinDisplay playerCoins = new CoinDisplay();
        playerCoins.update(3);
        int newValue = Integer.parseInt(statusDisplayProperties.getProperty("StartingCoins"));
        assertEquals(newValue +3, Integer.parseInt(playerCoins.getCoinDisplay().getText()));

    }

    @Test
    public void testCoinsGetValue(){
        CoinDisplay playerCoins = new CoinDisplay();
        assertEquals(playerCoins.getValue(), Integer.parseInt(playerCoins.getCoinDisplay().getText()));

    }

}
