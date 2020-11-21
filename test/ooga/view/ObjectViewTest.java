package ooga.view;

import javafx.scene.paint.ImagePattern;
import ooga.PropertiesReader;
import ooga.view.utilities.ViewUtilities;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ObjectViewTest {

    private Properties properties;

    public ObjectViewTest(){
        PropertiesReader propertiesReader = new PropertiesReader();
        properties = (Properties) propertiesReader.read("View/ObjectImages.properties");
    }


    @Test
    public void objectCreated(){
        ObjectView tower = new ObjectView("RegularTarget1");
        assertEquals(tower.getId(),"RegularTarget1");

    }

    @Test
    public void checkUpdateDroppedImage(){
        ObjectView tower = new ObjectView("RegularTarget1");
        ImagePattern image = ViewUtilities.createImagePattern(properties.getProperty("RegularAttacker"));
        tower.updateDroppedImage(image,"RegularAttacker");
        assertTrue(tower.getFill() == image);
    }

    @Test
    public void checkMovedToNextLocation(){
        ObjectView tower = new ObjectView("RegularTarget1");
        tower.updateNextLocation(3,4);
        tower.moveObject();
        assertEquals(tower.getX(), 3);
        assertEquals(tower.getY(),4);
    }

    @Test
    public void checkMoveObject(){
        ObjectView tower = new ObjectView("RegularTarget2");
        tower.updateNextLocation(10,40);
        tower.moveObject();
        assertEquals(tower.getX(), 10);
        assertEquals(tower.getY(),40);
    }

    /*
    This test fails in the overall coverage but does not fail in the actual test
     */
    @Test
    public void checkImageUpdated(){
        ObjectView thistower = new ObjectView("RegularTarget1");
        thistower.updateImage("RegularAttacker");
        ImagePattern image = ViewUtilities.createImagePattern(properties.getProperty("RegularAttacker"));
        assertTrue(thistower.getFill()==image);
    }

}