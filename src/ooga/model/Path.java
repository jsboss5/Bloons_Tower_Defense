package ooga.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import ooga.model.targets.Target;

public class Path extends GridCell {

  private final Set<Target> pieces;


  public Path(int xPos, int yPos) {
    super(xPos, yPos);
    pieces = new HashSet<>();
  }

  @Override
  public void add(GamePiece piece) {
    pieces.add((Target) piece);
  }

  @Override
  public Set<Target> getPieces() {
    return pieces;
  }


  @Override
  public void removeAll(Collection removables) {
    pieces.removeAll(removables);
  }

}
