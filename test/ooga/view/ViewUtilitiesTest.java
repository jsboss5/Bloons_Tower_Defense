package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.view.exceptions.ImageException;
import ooga.view.utilities.ViewUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import javax.swing.text.View;
import java.io.FileNotFoundException;

class ViewUtilitiesTest extends DukeApplicationTest {


  public void start(Stage stage) {
    stage.show();
  }

  @Test
  public void testCreateImage() {
    ImageView imageView = new ImageView(ViewUtilities.createImage("Images/Background.png"));
    assertNotNull(imageView);
  }

  @Test
  public void testRetrieveIdEquals() {
    Text testText = ViewUtilities.createText("id=TestText,", "This is for testing purposes.");
    assertEquals("TestText",ViewUtilities.retrieveIdEquals(testText.getId()));
  }

  @Test
  public void testRetrieveIdEqualsComma() {
    Text testText = ViewUtilities.createText("id=TestText", "This is for testing");
    assertEquals("id=TestText", ViewUtilities.retrieveId(testText.getId()));
  }

  @Test
  public void testRetrieveId() {
    Text testText = ViewUtilities.createText("id=TestText,otherText", "This is for testing");
    assertEquals("id=TestText", ViewUtilities.retrieveId(testText.getId()));
  }

  @Test
  public void testRetrieveIdNoComma() {
    Text testText = ViewUtilities.createText("id=TestText", "This is for testing");
    assertEquals("id=TestText", ViewUtilities.retrieveId(testText.getId()));
  }

  @Test
  public void testCreateButton() {
    assertNotNull(ViewUtilities.createButton("button", "New Button"));
  }

  @Test
  public void testCreateLabel() {
    assertNotNull(ViewUtilities.createLabel("label", "Test Label"));
  }

  @Test
  public void testCreateImagePattern() {
    assertNotNull(ViewUtilities.createImagePattern("Images/Background.png"));
  }

  @Test
  public void testCreateImageView() {
    assertNotNull(ViewUtilities.createImageView("Images/Background.png"));
  }

  @Test
  public void testCreateText() {
    assertNotNull(ViewUtilities.createText("testText", "This is for testing purposes"));
  }

  @Test
  public void testException(){
    Throwable e = null;
    try{
      ViewUtilities.createImageView("dfjasf");
    } catch (Exception ex) {
      e = ex;
      new ImageException("e", e);
    }
    assertFalse(e instanceof NullPointerException);
  }

  @Test
  public void testExceptionPattern(){
    Throwable e = null;
    try{
      ViewUtilities.createImagePattern("dfjasf");
    } catch (Exception ex) {
      e = ex;
      new ImageException("e", e);
    }
    assertFalse(e instanceof NullPointerException);
  }

  @Test
  public void testExceptionImage(){
    Throwable e = null;
    try{
      ViewUtilities.createImage("dfjasf");
    } catch (Exception ex) {
      e = ex;
      new ImageException("e", e);
    }
    assertFalse(e instanceof NullPointerException);
  }

}