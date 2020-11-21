package ooga.view.screens;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


import static org.junit.jupiter.api.Assertions.*;

class MapChooserTest extends DukeApplicationTest {

    private MapChooser mapChooser;
    private Stage stage;
    private ImageView Map1;
    private ImageView Map2;
    private ImageView Map3;

    public void start(Stage stage) {
        this.stage = stage;
        mapChooser = new MapChooser(stage);

        Map1 = lookup("#Map1").query();
        Map2 = lookup("#Map2").query();
        Map3 = lookup("#Map3").query();



    }

    @Test
    public void testNotNull(){
        assertNotNull(stage.getScene());
    }

    @Test
    public void testNextScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clickOn(Map1);

            }
        });
        assertFalse(stage.getScene().getRoot().getChildrenUnmodifiable().contains(Map1));
    }

    @Test
    public void testPropertyFiles2(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mapChooser.selectPropertyFiles("Map2");

            }
        });
        assertFalse(stage.getScene().getRoot().getChildrenUnmodifiable().contains(Map2));

    }

    @Test
    public void testPropertyFiles3(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mapChooser.selectPropertyFiles("Map3");

            }
        });
        assertFalse(stage.getScene().getRoot().getChildrenUnmodifiable().contains(Map3));

    }
}