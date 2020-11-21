package ooga.model.dataloaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import ooga.ConfigurationLoader;

public class CSV_Parser implements Data {

  private List<String[]> path;

  private int rows;
  private int columns;


  @Override
  public Object read(String fileName) {
    try {
      InputStream data = new FileInputStream(fileName);
      ConfigurationLoader config = new ConfigurationLoader();
      path = config.readConfigFile(data);

      if (path == null) {
        throw new RuntimeException(
            "No configuration information found. Please reload the game with a "
                + "valid configuration file");
      }
      rows = Integer.parseInt(path.get(0)[0]);
      columns = Integer.parseInt(path.get(0)[1]);
      return path;
      //TODO: write custom exception for this
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return path;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }
}
