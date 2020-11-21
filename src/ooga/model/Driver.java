package ooga.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import ooga.PropertiesReader;
import ooga.model.attackers.Attacker;
import ooga.model.factories.Factory;
import ooga.model.factories.TargetFactory;

import ooga.model.targets.Target;

public class Driver implements Engine {

  private final Set<Target> removedTargets = new HashSet<>();
  private final Set<Attacker> attackerList = new HashSet<>();


  private final Player myPlayer;
  private final List<Target> recentDefeatedTargets = new ArrayList<>();
  private final Map<Attacker, Set<Target>> attackersAndTheirTargets = new HashMap<>();
  private final Grid grid;
  private final PropertiesReader propertyReader = new PropertiesReader();
  private Properties properties;
  private List<Path> pathList;
  private Set<Target> hit;
  private Set<Target> pathRemove;
  private int stepCounter;
  private int numTargets;


  private int totalDefeatedTargets;
  private int totalRemovedTargets;

  public Driver(String filename) throws FileNotFoundException {
    grid = new Grid(filename);
    pathList = grid.getPathList();
    myPlayer = new Player();
    totalDefeatedTargets = 0;
  }

  /**
   * This will move the balloons and tell towers to shoot which balloons
   */
  @Override
  public void movePieces() {
    stepCounter++;

    checkLastCell();

    for (int dex = pathList.size() - 2; dex >= 0; dex--) {

      Path currentPath = pathList.get(dex);
      Path nextPath = pathList.get(dex + 1);
      int nextPathX = nextPath.getX();
      int nextPathY = nextPath.getY();

      Set<Target> tempRemoveSet = new HashSet<>();

      moveTargetsInPath(currentPath, nextPathX, nextPathY, tempRemoveSet, dex);

      currentPath.removeAll(tempRemoveSet);
    }

  }

  private void checkLastCell() {
    Path lastPath = (pathList.get(pathList.size() - 1));

    int[] endCoords = getNextCoords(lastPath);
    int nextX = endCoords[0];
    int nextY = endCoords[1];


    Set<Target> tempRemoveSet = new HashSet<>();
    moveTargetsInPath(lastPath, nextX, nextY, tempRemoveSet, pathList.size()-1);

    lastPath.removeAll(tempRemoveSet);

    removedTargets.addAll(tempRemoveSet);
    totalRemovedTargets += tempRemoveSet.size();
  }

  private void moveTargetsInPath(Path curPath, int nextX, int nextY, Set<Target> tempRemoveSet, int pathDex) {
    for (Target target : curPath.getPieces()) {
      target.move(nextX, nextY);
      int newX = (int) target.getX();
      int newY = (int) target.getY();
      if (newX == nextX && newY == nextY) {
        tempRemoveSet.add(target);

        if(pathDex==pathList.size()-1){
          myPlayer.updateHealth(target.getHealth());
        }
        else{
          Path nextPath = pathList.get(pathDex + 1);
          nextPath.add(target);
        }
      }
    }
  }

  public void createProperties(int level) {
    stepCounter = 0;
    String fileName = "levels/" + level + ".properties";
    properties = (Properties) propertyReader.read(fileName);
    numTargets = Integer.parseInt((String) properties.get("numBalloons"));
  }


  public Set<Target> createTargets(int numBalloons) {
    Set<Target> newTargets = new HashSet<>();
    Path firstPath = pathList.get(0);

    for (int i = 0; i < numBalloons; i++) {
      int[] startCoords = getNextCoords(firstPath);
      int startX = startCoords[0];
      int startY = startCoords[1];

      Factory targetFactory = new TargetFactory();
      Target newTarget = (Target) targetFactory.getGamePiece(properties);
      newTarget.setxPos(startX);
      newTarget.setyPos(startY);
      newTargets.add(newTarget);
      firstPath.add(newTarget);

  }
    return newTargets;
  }


  private int[] getNextCoords(Path firstPath){
    int startX = firstPath.getX();
    int startY = firstPath.getY();

    if (firstPath.getX() == 0) {
      startX = -1;
    } else if (firstPath.getX() == grid.getGridRows() - 1) {
      startX = grid.getGridRows();
    } else if (firstPath.getY() == 0) {
      startY = -1;
    } else if (firstPath.getY() == grid.getGridCols() - 1) {
      startY = grid.getGridCols() ;
    }
    return new int[]{startX,startY};
  }

  public Set<Target> checkTargetsForWithinAttackersRange(Attacker attacker) {
    int numTargets = attacker.getNumTargets();
    hit = new HashSet<>();
    pathRemove = new HashSet<>();

    for (int i = pathList.size() - 1; i >= 0; i--) {
      Path currentPath = pathList.get(i);
      if (numTargets == 0){
        break;
      }

      numTargets = getNewNumTargets(attacker, numTargets, currentPath);
      attackersAndTheirTargets.put(attacker, hit);
      currentPath.getPieces().removeAll(pathRemove);
    }
    return hit;
  }

  private int getNewNumTargets(Attacker attacker, int numTargets, Path currentPath) {
    for (Target piece : currentPath.getPieces()) {
      double distance = findDistance(attacker.getX(), piece.getX(), attacker.getY(),
          piece.getY());
      if(distance <= attacker.getRange()) {

        if (!hit.contains(piece)) {
          attacker.update(piece);
          hit.add(piece);
          numTargets--;
        }

        checkPopped(pathRemove, piece);
      }
      if (numTargets == 0) {
        break;
      }
    }
    return numTargets;
  }

  private void checkPopped(Set<Target> pathRemove, Target piece) {
    if (piece.isPopped()) {
      recentDefeatedTargets.add(piece);
      totalDefeatedTargets++;
      pathRemove.add(piece);
    }
  }


  public double findDistance(double x1, double x2, double y1, double y2) {
    double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    return distance;
  }

  public void createAttacker(Attacker newAttacker) {
    attackerList.add(newAttacker);
  }


  public void addPathList(List<Path> pathlist) {
    this.pathList = pathlist;
  }

  public List<Path> getPathList() {
    return this.pathList;
  }

  public Grid getGrid() {
    return grid;
  }

  public Set<Attacker> getAttackerList() {
    return attackerList;
  }


  public void clearRemovedTargets() {
    removedTargets.clear();
  }

  public void addNewAttackers(Set<Attacker> newAttackers) {
    attackerList.addAll(newAttackers);
  }

  public List<Target> getRecentDefeatedTargets() {
    return recentDefeatedTargets;
  }

  public void clearRecentDefeatedTargets() {
    recentDefeatedTargets.clear();
  }


  public void clearAttackersAndTheirTargets() {
    attackersAndTheirTargets.clear();
  }

  public Player getMyPlayer() {
    return myPlayer;
  }

  public int getNumTargets() {
    return numTargets;
  }

  public int getTotalRemovedTargets() {
    return totalRemovedTargets;
  }

  public void setTotalRemovedTargets(int totalRemovedTargets) {
    this.totalRemovedTargets = totalRemovedTargets;
  }

  public int getTotalDefeatedTargets() {
    return totalDefeatedTargets;
  }

  public void setTotalDefeatedTargets(int totalDefeatedTargets) {
    this.totalDefeatedTargets = totalDefeatedTargets;
  }

  public int getStepCounter() {
    return stepCounter;
  }

  public Set<Target> getRemovedTargets() {
    return removedTargets;
  }
}
