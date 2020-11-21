package ooga;



import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesReaderTest{

    @Test
    public void testPropertyFileIsFound(){
        PropertiesReader propertiesReader = new PropertiesReader();
        Properties properties = (Properties) propertiesReader.read("View/ObjectImages.properties");
        assertTrue(properties != null);
    }

    @Test
    public void checkPropertyFileContent(){
        PropertiesReader propertiesReader = new PropertiesReader();
        Properties properties = (Properties) propertiesReader.read("View/ObjectImages.properties");
        assertEquals(properties.getProperty("RegularTarget1"),("Images/RegularTarget1.png"));

    }



}