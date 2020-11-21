package ooga.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import javafx.stage.Stage;
import ooga.model.Driver;
import ooga.model.attackers.Attacker;
import ooga.view.GameScene;
import ooga.view.ObjectView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ControllerTest extends DukeApplicationTest {
  Controller testController;
  @Override
  public void start(Stage stage) throws FileNotFoundException {
    testController = new Controller(stage, "data/gamepath_01.csv", 1);
  }

  @Test
  public void instanceOfView() {
    assertTrue(testController.getMyGameScene() instanceof GameScene);
  }
  @Test
  public void instanceOfModel() {
    assertTrue(testController.getMyDriver() instanceof Driver);
  }

  @Test
  void testSwitchingLevel(){
    testController.switchLevel(3);
    assertEquals(0, testController.getMyDriver().getTotalDefeatedTargets());
  }




  @Test
  void testCreateAttackers(){
    testController.createAttackers();
    int size = testController.getMyGameScene().getNewAttackers().size();
    assertEquals(size, 0);
  }




}