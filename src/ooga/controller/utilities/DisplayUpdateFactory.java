package ooga.controller.utilities;

import ooga.model.Player;
import ooga.view.displays.StatusDisplay;

public class DisplayUpdateFactory {

  private final Player myPlayer;

  public DisplayUpdateFactory(Player myPlayer) {
    this.myPlayer = myPlayer;
  }

  public void updateDisplay(StatusDisplay display) {
    switch (display.getId()) {
      case "HealthDisplay" -> display.update(myPlayer.getHealth());
      case "CoinDisplay" -> myPlayer.getCoins();
    }
  }
}
