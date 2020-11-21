package ooga.model;

import java.util.Collection;
import java.util.Set;
import ooga.model.attackers.Attacker;

public class LandCell extends GridCell {

  private Set<Attacker> pieces;


  public LandCell(int xPos, int yPos) {
    super(xPos, yPos);
  }

  @Override
  public void add(GamePiece piece) {
    pieces.add((Attacker) piece);
  }

  @Override
  public Set<Attacker> getPieces() {
    return pieces;
  }

  @Override
  public void removeAll(Collection removables) {
    pieces.removeAll(removables);
  }

}

