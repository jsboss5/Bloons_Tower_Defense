package ooga.view;

import java.util.List;
import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import ooga.PropertiesReader;
import ooga.view.displays.CoinDisplay;
import ooga.view.utilities.ViewUtilities;

public class ViewGrid {

  public static final String SHOP_PRICES = "View/StoreOption.properties";
  public static final String PATH_TYPES = "View/PathTypes.properties";
  public static final String GAME_PROPERTIES = "View/Game.properties";

  private final int rows;
  private final int columns;
  private final List<String[]> csvData;
  private final HBox gameWithShop;
  private final Shop shop;
  private final Properties shopProperties;
  private final Properties pathTypes;
  private final Properties gameProperties;
  private final CoinDisplay coinDisplay;
  private int cellXSize;
  private int cellYSize;
  private VBox gameView;

  /**
   * This class creates a Grid of VBoxes/HBoxes that consist of ObjectViews
   *
   * @param readInFile
   */

  public ViewGrid(List<String[]> readInFile) {
    this.csvData = readInFile;
    this.rows = Integer.parseInt(readInFile.get(0)[0]);
    this.columns = Integer.parseInt(readInFile.get(0)[1]);
    PropertiesReader propertiesReader = new PropertiesReader();
    shopProperties = (Properties) propertiesReader.read(SHOP_PRICES);
    pathTypes = (Properties) propertiesReader.read(PATH_TYPES);
    gameProperties = (Properties) propertiesReader.read(GAME_PROPERTIES);
    shop = new Shop();
    this.coinDisplay = shop.getCoinDisplay();
    gameWithShop = new HBox();
    createView();
  }

  private void createView() {
    gameView = new VBox();
    gameView.setId("GameView");
    cellYSize = Integer.parseInt(gameProperties.getProperty("GameHeight")) / rows;
    cellXSize = Integer.parseInt(gameProperties.getProperty("GameWidth")) / columns;
    for (int row = 0; row < rows; row++) {
      HBox cellRow = new HBox();
      cellRow.setId("row" + row);
      for (int col = 0; col < columns; col++) {
        ObjectView pathView = new ObjectView(pathTypes.getProperty("BackgroundPath"));
        pathView.setHeight(cellYSize);
        pathView.setWidth(cellXSize);
        setDragDrop(pathView);
        cellRow.getChildren().add(pathView);
      }
      gameView.getChildren().add(cellRow);
    }
    addPath(cellYSize, cellXSize);
    gameWithShop.getChildren().add(gameView);
    gameWithShop.getChildren().add(shop);
  }

  private void addPath(int xSize, int ySize) {
    List<Node> cells = gameView.getChildren();
    for (int line = 1; line < csvData.size(); line++) {
      int xCord = Integer.parseInt(csvData.get(line)[0]);
      int yCord = Integer.parseInt(csvData.get(line)[1]);
      int type = Integer.parseInt(csvData.get(line)[2]);
      HBox cellRow = (HBox) cells.get(xCord);
      List<Node> cols = cellRow.getChildren();
      ObjectView path = new ObjectView("path" + type);
      path.setWidth(ySize);
      path.setHeight(xSize);
      cols.set(yCord, path);

    }
  }

  //TODO: write tests for DragDrop
  private void setDragDrop(ObjectView pathView) {
    pathView.setOnDragOver(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        if (event.getGestureSource() != pathView.getFill() && event.getDragboard().hasImage()) {
          event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
      }
    });
    pathView.setOnDragDropped(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasImage()) {
          ImagePattern droppedImage = new ImagePattern(db.getImage());
          String id = event.getGestureSource().toString();
          if (checkValidPurchase(id)) {
            pathView.updateDroppedImage(droppedImage, ViewUtilities.retrieveId(id));
            pathView.setOnDragDropped(null);
            pathView.setOnDragOver(null);
            event.consume();

          }
        }
      }
    });
  }

  private Boolean checkValidPurchase(String id) {
    id = ViewUtilities.retrieveIdEquals(id) + "Price";
    int price = Integer.parseInt(shopProperties.getProperty(id));
    if (Integer.valueOf(coinDisplay.getCoinDisplay().getText()) >= price) {
      coinDisplay.update(-price);
      return true;
    }
    return false;
  }

  /**
   * This method converts the path into an integer in case it is the beginning of the path or there
   * is a mess up in the csv file that is not a number
   *
   * @param type
   * @return integer that indicates what view to display
   */

  //TODO: make this an error checking thing for format
  private int convertType(String type) {
    StringBuilder convertedType = new StringBuilder();
    char[] typeLetters = type.toCharArray();
    for (char var : typeLetters) {
      try {
        convertedType.append(Integer.parseInt(String.valueOf(var)));
      } catch (NumberFormatException e) {
      }
    }
    return Integer.parseInt(convertedType.toString());
  }


  public VBox getGameView() {
    return gameView;
  }

  public Shop getShop() {
    return shop;
  }

  public int getRows() {
    return this.rows;
  }

  public int getColumns() {
    return this.columns;
  }

  public HBox getGameWithShop() {
    return gameWithShop;
  }
}
