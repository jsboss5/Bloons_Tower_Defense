package ooga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfigurationLoaderTest {

  @Test
  void testBlankConfiguration() {
    boolean exceptionThrown = false;
    List<String[]> path = null;
    try (InputStream data = new FileInputStream("data/TestFiles/blank_gamepath.csv")) {
      ConfigurationLoader configLoader = new ConfigurationLoader();
      path = configLoader.readConfigFile(data);
    } catch (IOException e) {
      List<String[]> returnedList = Collections.emptyList();
      assertEquals(returnedList, path);
    }
  }

  @Test
  void testConfigurationLoaderNotNull() {
    ConfigurationLoader configLoader = new ConfigurationLoader();
    try (InputStream data = new FileInputStream("data/gamepath_01.csv")) {
      List<String[]> path = configLoader.readConfigFile(data);
      assertNotNull(path);
    } catch (Exception e) {
      Assertions.fail("The configuration file is null.");
    }
  }

}
