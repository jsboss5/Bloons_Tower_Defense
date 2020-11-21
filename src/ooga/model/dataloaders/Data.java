package ooga.model.dataloaders;

import java.io.FileNotFoundException;

public interface Data {

  /**
   * This interface will be implemented by different types of data readers and writers for CSV fies
   * and Properties files alike arjun is cool
   */


  Object read(String fileName) throws FileNotFoundException;

}
