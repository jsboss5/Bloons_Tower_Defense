package ooga.controller.utilities;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;

public class KeyHandler {

  Map<KeyCode, Runnable> myKeyActions;

  public KeyHandler() {
    myKeyActions = new HashMap<>();
  }

  public void handleKeyInput(KeyCode code) {
    if (myKeyActions.containsKey(code)) {
      myKeyActions.get(code).run();
    }
  }

  public void addCheatKeys(KeyCode code, Runnable action) {
    myKeyActions.put(code, action);
  }
}
