package ooga;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PropertiesReaderExceptionTest extends DukeApplicationTest {

  public void start(Stage stage) {
    stage.show();
  }

  @Test
  public void testExceptionThrown() {
    PropertiesReaderException thrown = assertThrows(
        PropertiesReaderException.class,
        () -> {
          PropertiesReader propertiesReader = new PropertiesReader();
          propertiesReader.read("FileDoesNotExist");
        }
    );
  }
}
