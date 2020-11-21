package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ooga.controller.Controller;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.FileNotFoundException;

class GameSceneTest extends DukeApplicationTest {

    private GameScene gameScene;
    private Stage stage;
    private Controller controller;

    public void start(Stage stage) throws FileNotFoundException {
        controller = new Controller(stage,"data/gamepath_01.csv", 1);
        gameScene = controller.getMyGameScene();
        stage.show();
    }

    @Test
    public void testGetRoot() {
        assertNotNull(gameScene.getRoot());
    }

}