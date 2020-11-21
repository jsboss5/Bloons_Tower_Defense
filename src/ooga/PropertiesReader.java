package ooga;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ooga.model.dataloaders.Data;

public class PropertiesReader implements Data {

  /**
   * This class is responsible for reading in properties files
   *
   * @author jodiyeh
   */
  private Properties properties;


  @Override
  public Object read(String filename) {
    properties = new Properties();
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      InputStream fileContents = classLoader.getResourceAsStream(filename);
      properties.load(fileContents);
    } catch (Exception e) {
      throw new PropertiesReaderException("File not found", e);
    }
    return properties;
  }

}
