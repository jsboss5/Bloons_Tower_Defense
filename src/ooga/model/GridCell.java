package ooga.model;

import java.util.Collection;
import java.util.Set;

public abstract class GridCell<E extends GamePiece> {

  private final int xPos;
  private final int yPos;

  public GridCell(int xPosition, int yPosition) {
    xPos = xPosition;
    yPos = yPosition;
  }

  public abstract void add(E piece);

  public abstract Set<E> getPieces();

  public abstract void removeAll(Collection<E> removables);


  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }

}
