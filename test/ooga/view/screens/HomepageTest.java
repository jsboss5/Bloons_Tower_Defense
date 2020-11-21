package ooga.view.screens;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class HomepageTest extends DukeApplicationTest {

    public static final String SCREEN_PROPERTIES = "View/Screen.properties";
    public static final String GAME_PROPERTIES = "View/Game.properties";
    public static final String SCREEN_STYLESHEET = "View/Screen.css";

    private Group root;
    private Stage stage;
    private Properties properties;
    private PropertiesReader propertiesReader;
    private Homepage homepage;
    private Button enterGame;
    private Scene scene;

    public void start(Stage stage) {
        this.stage = stage;
        propertiesReader = new PropertiesReader();
        properties = (Properties) propertiesReader.read(SCREEN_PROPERTIES);
        root = new Group();
        homepage = new Homepage(stage);
        scene= stage.getScene();

        enterGame = lookup("#enterGame").query();

    }

    @Test
    public void testNotNull(){
        assertNotNull(stage.getScene());
    }

    @Test
    public void testGoToNextScreen() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clickOn(enterGame);

            }
        });
        assertTrue(stage.getScene().getRoot().getChildrenUnmodifiable().contains(enterGame));

    }
}