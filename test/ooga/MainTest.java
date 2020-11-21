package ooga;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.screens.Homepage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class MainTest extends DukeApplicationTest {

  private Main mainTest;

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    mainTest = new Main();
    mainTest.start(stage);
  }

  @Test
  public void myTest() {
    assertTrue(mainTest.getHomepage() instanceof Homepage);
  }
}