package ooga.view.screens;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.PropertiesReader;
import ooga.view.Shop;
import ooga.view.displays.HealthDisplay;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class GameOverTest extends DukeApplicationTest {

    public static final String SCREEN_PROPERTIES = "View/Screen.properties";

    private Properties properties;
    private PropertiesReader propertiesReader;
    private Group root;
    private Stage stage;
    private GameOver gameOver;
    private String image;
    private Scene scene;
    private Button startOver;

    public void start(Stage stage) {
        propertiesReader = new PropertiesReader();
        properties = (Properties) propertiesReader.read(SCREEN_PROPERTIES);
        root = new Group();
        gameOver = new GameOver(root,stage,true);
        scene = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.show();

        startOver = lookup("#playAgain").query();

    }


    @Test
    public void testGameOverNotNull(){
        assertNotNull(root.getChildren());
    }

    @Test
    public void testGoToNextScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clickOn(startOver);

            }
        });
        assertTrue(this.stage == null);

    }

    @Test
    public void getRoot(){
        assertEquals(gameOver.getRoot(),this.root);
    }


}