package ooga.controller.utilities;

import java.util.Properties;
import ooga.PropertiesReader;

public class CoordinateConverter {

  public static final String GAME_PROPERTIES = "View/Game.properties";

  private final PropertiesReader propertiesReader;
  private final Properties gameProperties;

  int cellSizeX;
  int cellSizeY;

  public CoordinateConverter(int numRow, int numCol) {
    propertiesReader = new PropertiesReader();
    gameProperties = (Properties) propertiesReader.read(GAME_PROPERTIES);
    cellSizeX = Integer.parseInt(gameProperties.getProperty("GameWidth")) / numCol;
    cellSizeY = Integer.parseInt(gameProperties.getProperty("GameHeight")) / numRow;
  }

  public double[] gridToPixels(double row, double col) {

    double xPix = col * cellSizeX;
    double yPix = row * cellSizeY;

    return new double[]{xPix, yPix};
  }


}
