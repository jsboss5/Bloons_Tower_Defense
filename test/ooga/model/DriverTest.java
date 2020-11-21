package ooga.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.model.attackers.*;
import ooga.model.targets.RegularTarget;
import ooga.model.targets.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class DriverTest extends DukeApplicationTest {
  Driver myDriver;
  List<Path> pathList;
  Properties properties;
  private final PropertiesReader propertyReader = new PropertiesReader();

    @Override
    public void start(Stage mystage) throws FileNotFoundException {
    myDriver = new Driver("data/gamepath_01.csv");
    myDriver.createProperties(0);
    System.out.println(myDriver.getStepCounter());
  }



  @BeforeEach
  void createPathList(){
      pathList = new ArrayList<>();

      pathList.add(new Path(0,0));
      pathList.add(new Path(0,1));
      pathList.add(new Path(0,2));
      pathList.add(new Path(1,2));
      pathList.add(new Path(1,3));
      pathList.add(new Path(2,3));
      pathList.add(new Path(2,4));
      pathList.add(new Path(3,4));
      pathList.add(new Path(4,4));

      pathList.add(new Path(5,4));
      pathList.add(new Path(6,4));
      pathList.add(new Path(7,4));
      pathList.add(new Path(8,4));
      pathList.add(new Path(9,4));
      pathList.add(new Path(10,4));
      pathList.add(new Path(11,4));
      pathList.add(new Path(12,4));
      pathList.add(new Path(13,4));

    myDriver.addPathList(pathList);
  }

  @Test
  void testBalloonMovesToNextPath(){
      Target testTarget = new RegularTarget(0,0,1,1,1, "RegularTarget");
    myDriver.getPathList().get(0).getPieces().add(testTarget);

      //test contents of first and second path cells pre iteration
      assertEquals(myDriver.getPathList().get(0).getPieces().size(), 1);
      assertEquals(myDriver.getPathList().get(1).getPieces().size(), 0);

      //test location of my balloon at start
      assertEquals(testTarget.getX(),0);
      assertEquals(testTarget.getY(),0);

      //iterate once
      myDriver.movePieces();

      //test contents of first and
    assertEquals(myDriver.getPathList().get(0).getPieces().size(), 0);
    assertEquals(myDriver.getPathList().get(1).getPieces().size(), 1);

    //Test Target has moved
    assertEquals(testTarget.getX(),0);
    assertEquals(testTarget.getY(),1);


  }

  @Test
  void testBalloonOffSpeed(){
    myDriver.getPathList().get(0).getPieces().add(
        new RegularTarget(0,0,1,1,0.5,"RegularTarget"));

    assertEquals(myDriver.getPathList().get(0).getPieces().size(), 1);
    assertEquals(myDriver.getPathList().get(1).getPieces().size(), 0);

    myDriver.movePieces();
    assertEquals(myDriver.getPathList().get(0).getPieces().size(), 1);
    assertEquals(myDriver.getPathList().get(1).getPieces().size(), 0);

    myDriver.movePieces();

    assertEquals(myDriver.getPathList().get(0).getPieces().size(), 0);
    assertEquals(myDriver.getPathList().get(1).getPieces().size(), 1);

  }


  @Test
  void testLastPathBalloon(){
    int lastDex = pathList.size()-1;
    myDriver.getPathList().get(lastDex).getPieces().add(
        new RegularTarget(13,4,1,1,1,"RegularTarget"));

    //size of last one has 1
    assertEquals(myDriver.getPathList().get(lastDex).getPieces().size(), 1);

    myDriver.movePieces();

    //it should be removed from the last one
    assertEquals(0,myDriver.getPathList().get(lastDex).getPieces().size());

  }

    @Test
    void testCreateThreeBalloons(){
        myDriver.createTargets(3);
        //should be three balloons in first path
        assertEquals(myDriver.getPathList().get(0).getPieces().size(), 3);

    }

    @Test
    void testTowerShootsBalloonOneTarget(){
        Attacker firstAttacker = new RegularAttacker(0, 1);
        firstAttacker.setFiringRate(0);
        Target target = myDriver.createTargets(1).iterator().next();
        myDriver.movePieces();
        myDriver.movePieces();
        int health = target.getHealth();
        health--;
        myDriver.checkTargetsForWithinAttackersRange(firstAttacker);
        for(Path path: myDriver.getPathList()){
            for(Target piece: path.getPieces()){
                    assertEquals(health, piece.getHealth());
            }
        }
    }
    @Test
    void testTowerDoesNotHitBalloonOutOfRange() {
        Attacker firstAttacker = new RegularAttacker(3, 3);
        firstAttacker.setFiringRate(0);
        Target target = myDriver.createTargets(1).iterator().next();
        int health = target.getHealth();
        myDriver.movePieces();
        myDriver.movePieces();
        myDriver.checkTargetsForWithinAttackersRange(firstAttacker);
        for (Path path : myDriver.getPathList()) {
            for (GamePiece piece : path.getPieces()) {
                if (piece instanceof Target) {
                    Target balloon = (Target) piece;
                    assertEquals(balloon.getState(), 1);
                    assertEquals(balloon.getHealth(), health);
                }
            }
        }
    }

        @Test
        void testTowerHitsBalloonThreeTargets(){
            Attacker firstAttacker = new TackAttacker(1, 1);
            firstAttacker.setFiringRate(0);
            Target target = myDriver.createTargets(3).iterator().next();
            int health = target.getHealth() - 1;
            myDriver.movePieces();
            myDriver.movePieces();
            myDriver.checkTargetsForWithinAttackersRange(firstAttacker);
            for(Path path: myDriver.getPathList()){
                for(Target piece: path.getPieces()){
                    assertEquals(piece.getHealth(), health);
                }
            }
        }

    @Test
    void testGlueTowerSlowsTargetSpeed(){
        Path firstPath = myDriver.getPathList().get(0);
        Attacker firstAttacker = new GlueAttacker(firstPath.getX(), firstPath.getY());
        firstAttacker.setFiringRate(0);
        firstAttacker.setRange(3);
        Target target = myDriver.createTargets(1).iterator().next();
        double speed = target.getSpeed();
        speed = speed/2;
        System.out.println(target.getSpeed());
        myDriver.checkTargetsForWithinAttackersRange(firstAttacker);
        for(Path path: myDriver.getPathList()){
            for(Target piece: path.getPieces()){
                assertEquals(piece.getSpeed(), speed);
            }
        }

    }
    @Test
    void testGlueTowerSlowsTargetSpeedForNumberOfSteps(){
        Path firstPath = myDriver.getPathList().get(0);
        Attacker firstAttacker = new GlueAttacker(firstPath.getX(), firstPath.getY());
        firstAttacker.setRange(3);
        firstAttacker.setFiringRate(0);
        Target target = myDriver.createTargets(1).iterator().next();
        double speed = target.getSpeed();
        myDriver.checkTargetsForWithinAttackersRange(firstAttacker);
        for(int i = 0; i < 20; i++){
            myDriver.movePieces();
        }
        for(Path path: myDriver.getPathList()){
            for(Target piece: path.getPieces()){
                assertEquals(piece.getSpeed(), speed);
            }
        }
    }


}