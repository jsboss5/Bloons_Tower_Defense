package ooga.controller.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import ooga.model.targets.RegularTarget;
import ooga.model.GamePiece;
import ooga.view.ObjectView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class BidirectionalMapTest extends DukeApplicationTest {
      BidirectionalMap<GamePiece, ObjectView> myMap;

    @BeforeEach
    public void setup(){
      myMap = new BidirectionalMap<>();
    }

    @Test
    public void putTestBackToFront(){
      GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
      ObjectView balloonView = new ObjectView("RegularTarget1");

      assertEquals(myMap.size(), 0);
      myMap.put(target, balloonView);
      assertEquals(myMap.size(), 1);

    }
  @Test
  public void putTestFrontToBack(){
    GamePiece target = new RegularTarget(0,0,1,1,1,"RegulaTarget");
    ObjectView balloonView = new ObjectView("RegularTarget1");

    assertEquals(myMap.size(), 0);
    myMap.put(balloonView, target);
    assertEquals(myMap.size(), 1);

  }

  @Test
  public void getTest(){
    GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
    ObjectView balloonView = new ObjectView("RegularTarget1");

    myMap.put(balloonView, target);

    assertEquals(myMap.get(target), balloonView);
    assertEquals(myMap.get(balloonView), target);
  }

  @Test
  public void iteratorTest(){
      for(int i = 0; i<10; i++){
        GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
        ObjectView balloonView = new ObjectView("RegularTarget1");
        myMap.put(balloonView, target);
      }

      Iterator myIterator = myMap.iterator();

      int count = 0;
      while(myIterator.hasNext()){
          count++;
          myIterator.next();
      }
      assertEquals(count, 10);
  }
  @Test
  public void removeTest(){
    GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
    ObjectView balloonView = new ObjectView("RegularTarget1");
    myMap.put(balloonView, target);

    assertEquals(myMap.size(),1);
    myMap.remove(target);
    assertEquals(myMap.size(),0);

    myMap.put(target, balloonView);

    assertEquals(myMap.size(),1);
    myMap.remove(balloonView);
    assertEquals(myMap.size(),0);

  }
  @Test
  public void removeAllTest(){
    Set<GamePiece> pieceSet = new HashSet<>();
      for(int i = 0; i<10; i++){

        GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
        ObjectView balloonView = new ObjectView("RegularTarget1");
        myMap.put(balloonView, target);
        if(i%2==0){
           pieceSet.add(target);
        }
      }

    assertEquals(myMap.size(),10);
    myMap.removeAll(pieceSet);
    assertEquals(myMap.size(),5);
  }

  @Test
  public void clearTest(){
    for(int i = 0; i<10; i++){
      GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
      ObjectView balloonView = new ObjectView("RegularTarget1");
      myMap.put(balloonView, target);
    }

    assertEquals(myMap.size(),10);
    myMap.clear();
    assertEquals(myMap.size(),0);
  }


  @Test
  public void containsTest(){
      Set<GamePiece> balloonSet = new HashSet<>();
      for(int i = 0; i<10; i++){
        GamePiece target = new RegularTarget(0,0,1,1,1,"RegularTarget");
        ObjectView balloonView = new ObjectView("RegularTarget1");
        myMap.put(balloonView, target);
        balloonSet.add(target);
    }


      assertEquals(myMap.size(),10);
      boolean checkAllThere = true;
      for(GamePiece target: balloonSet){
          checkAllThere &= myMap.contains(target);
      }
      assertTrue(checkAllThere);
  }


}