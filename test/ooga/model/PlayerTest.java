package ooga.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class PlayerTest extends DukeApplicationTest {
  Driver myDriver;
  Player myPlayer;

  @Override
  public void start(Stage mystage) throws FileNotFoundException {
    myDriver = new Driver("data/gamepath_01.csv");
    myPlayer = new Player();
  }

  @Test
  public void simpleUpdateTest(){
    Player myPlayer = new Player();
    assertEquals(myPlayer.getHealth(), 100);
    myPlayer.updateHealth(1);
    assertEquals(99,myPlayer.getHealth());
  }
  @Test
  public void simpleCoindAdd(){
    Player myPlayer = new Player();
    assertEquals(myPlayer.getCoins(), 250);
    myPlayer.updateCoins(10);
    assertEquals(260, myPlayer.getCoins());
  }

  //TODO - test it with real towers


}