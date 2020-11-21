package ooga;

public class Cell {

  private final int xCoord;
  private final int yCoord;

  public Cell(int x, int y) {
    xCoord = x;
    yCoord = y;
  }

  public int getX() {
    return xCoord;
  }

  public int getY() {
    return yCoord;
  }
}

