package ooga;


import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.screens.Homepage;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {


  /**
   * Start of the program.
   */
  private Homepage homepage;

  public static void main(String[] args) {
    Application.launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    Homepage homepage = new Homepage(primaryStage);
  }

  public Homepage getHomepage() {
    return homepage;
  }

}




