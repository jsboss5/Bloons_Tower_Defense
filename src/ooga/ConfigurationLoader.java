package ooga;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for reading in the data provided by configuration files in a .csv file
 * format.
 *
 * @author AmritaLakhanpal
 */
public class ConfigurationLoader {

  /**
   * Reads in configuration file (.csv file format) and returns contents of these files This method
   * is extracted from a Piazza post by Professor Duvall
   *
   * @param data, InputStream that will convert the bytes within the configuration file into
   *              readable characters
   * @return List of String arrays that contain the contents of the configuration files in a
   * readable format
   */
  public List<String[]> readConfigFile(InputStream data) {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    } catch (IOException | CsvException e) {
      return Collections.emptyList();
    }
  }
}
