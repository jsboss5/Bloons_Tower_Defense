package ooga.view;

import java.util.Properties;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ooga.PropertiesReader;
import ooga.view.exceptions.PathException;
import ooga.view.utilities.ViewUtilities;

/**
 * This class is the general object class that will create the view of all objects in the scene
 *
 * @author jodiyeh
 */

public class ObjectView extends Rectangle implements Updatable {

  public static final String PROPERTIES_FILENAME = "View/ObjectImages.properties";
  public static final String X_SIZE = "XSize";
  public static final String Y_SIZE = "YSize";
  public static final int DEFAULT_SIZE = 30;

  private final Properties objectProperties;
  private int xSize;
  private int ySize;
  private double nextRow;
  private double nextCol;

  /**
   * This initializes the object based upon the objectType
   *
   * @param objectType
   */
  public ObjectView(String objectType) {
    super(DEFAULT_SIZE, DEFAULT_SIZE);
    PropertiesReader propertiesReader = new PropertiesReader();
    objectProperties = (Properties) propertiesReader.read(PROPERTIES_FILENAME);
    this.setId(objectType);
    if (checkProperty(objectType)) {
      this.setFill(ViewUtilities.createImagePattern(objectProperties.getProperty(objectType)));
    }
    try {
      xSize = Integer.parseInt(objectProperties.getProperty(objectType + X_SIZE));
      ySize = Integer.parseInt(objectProperties.getProperty(objectType + Y_SIZE));
      this.setHeight(ySize);
      this.setWidth(xSize);
    } catch (NumberFormatException e) {
      new PathException("Path size does not exist");
    }
  }

  public void updateDroppedImage(ImagePattern image, String id) {
    this.setFill(image);
    this.setId(id);
  }


  private Boolean checkProperty(String objectType) {
    return (!objectProperties.getProperty(objectType).equals("n/a"));
  }

  /**
   * This method updates the location of this object
   *
   * @param xCord
   * @param yCord
   */
  public void updateNextLocation(double xCord, double yCord) {
    nextRow = xCord;
    nextCol = yCord;
  }

  public void moveObject() {
    this.setX(nextRow);
    this.setY(nextCol);
  }

  /**
   * This method updates the image of the object
   *
   * @param objectType
   */
  public void updateImage(String objectType) {
    this.setFill(ViewUtilities.createImagePattern(objectProperties.getProperty(objectType)));
    this.setId(objectType);
  }

  public double getNextRow() {
    return nextRow;
  }

  public double getNextCol() {
    return nextCol;
  }


}
