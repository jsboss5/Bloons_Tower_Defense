package ooga.view.screens;

import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import ooga.view.utilities.ViewUtilities;


public abstract class Screen {

  public abstract void setUp();

  public abstract void goToNextScreen();

  public Button createButton(String id, String text) {
    Button tempButton = ViewUtilities.createButton(id, text);
    tempButton.setOnAction(event -> goToNextScreen());
    return tempButton;
  }

  public void nodeResize(Node resize, Properties properties) {
    resize.setLayoutY(Integer.parseInt(properties.getProperty(resize.getId() + "Y")));
    resize.setLayoutX(Integer.parseInt(properties.getProperty(resize.getId() + "X")));
  }

  public int getYSize(Node toGetSize, Properties properties) {
    return Integer.parseInt(properties.getProperty(toGetSize.getId() + "Y"));
  }

  public int getXSize(Node toGetSize, Properties properties) {
    return Integer.parseInt(properties.getProperty(toGetSize.getId() + "X"));
  }
}
