package ooga.model.attackers;

import java.util.Properties;
import ooga.PropertiesReader;
import ooga.model.GamePiece;
import ooga.model.targets.Target;

public class SniperAttacker extends Attacker {

  public SniperAttacker(int xPosition, int yPosition, int towerRank, int towerRange, int targetNum,
      int fireRate) {
    super(xPosition, yPosition, towerRank, towerRange, targetNum, fireRate);
  }

  public SniperAttacker(int xPosition, int yPosition) {
    super(xPosition, yPosition);
    PropertiesReader propertiesReader = new PropertiesReader();
    Properties gamePieceProperties = (Properties) propertiesReader
        .read("model/attackers/SniperAttacker.properties");
    this.setNumTargets(Integer.parseInt(gamePieceProperties.getProperty("StartingNumTargets")));
    this.setRange(Integer.parseInt(gamePieceProperties.getProperty("StartingRange")));
    this.setRank(Integer.parseInt(gamePieceProperties.getProperty("StartingRank")));
    this.setFiringRate(Integer.parseInt(gamePieceProperties.getProperty("StartingFireRate")));
  }

    @Override
    public void update(GamePiece piece) {
        Target target = (Target)piece;
        target.update(this);
    }
}
