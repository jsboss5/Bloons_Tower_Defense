package ooga.view.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import ooga.view.exceptions.ImageException;

import java.io.FileNotFoundException;

/**
 * This class is a utilities class for creating images, buttons, and labels.
 */
public class ViewUtilities {

  /**
   * This method creates a label given an id and text
   *
   * @param id
   * @param text
   * @return Label
   */
  public static Label createLabel(String id, String text) {
    Label createdLabel = new Label();
    createdLabel.setId(id);
    createdLabel.setText(text);
    return createdLabel;
  }

  /**
   * This method creates a Button of an id and text
   *
   * @param id
   * @param text
   * @return Button
   */
  public static Button createButton(String id, String text) {
    Button createdButton = new Button();
    createdButton.setId(id);
    createdButton.setText(text);
    return createdButton;
  }

  /**
   * This method returns an imagePattern of a filename
   *
   * @param filename
   * @return ImagePattern
   */
  public static ImagePattern createImagePattern(String filename) {
    try {
      Image levelTransition = new Image(filename);
      ImagePattern imagePattern = new ImagePattern(levelTransition);
      return imagePattern;
    } catch (Exception e) {
      new ImageException("Image not found", e);
    }
    return null;
  }

  /**
   * @param filename: the filename of the image to be added
   */
  public static ImageView createImageView(String filename) {
    try {
      Image levelTransition = new Image(filename);
      ImageView imageView = new ImageView(levelTransition);
      imageView.preserveRatioProperty();
      return imageView;
    } catch (Exception e) {
      new ImageException("Image not found",e);
    }
    return null;
  }

  public static Image createImage(String filename) {
    try {
      Image image = new Image(filename);
      return image;
    }
    catch (Exception e) {
      new ImageException("Image not found",e);
    }
    return null;
  }

  public static String retrieveIdEquals(String source) {
    int beginIndex = source.indexOf("id=");
    int endIndex = source.indexOf(",");
    if (endIndex != -1) {
      return source.substring(beginIndex + 3, endIndex);
    }
    return source.substring(beginIndex + 3);
  }

  public static String retrieveId(String source) {
    int beginIndex = source.indexOf("id=");
    int endIndex = source.indexOf(",");
    if (endIndex != -1) {
      return source.substring(beginIndex, endIndex);
    }
    return source.substring(beginIndex);
  }

  public static Text createText(String id, String text) {
    Text createdText = new Text(text);
    createdText.setId(id);
    return createdText;
  }
}
