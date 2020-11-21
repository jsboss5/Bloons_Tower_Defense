package ooga.view;

import java.util.Properties;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.PropertiesReader;
import ooga.view.displays.CoinDisplay;
import ooga.view.displays.HealthDisplay;
import ooga.view.utilities.ViewUtilities;

public class Shop extends BorderPane {

  public static final String SHOP_PROPERTIES = "View/StoreOptionImages.properties";
  public static final String SHOP_SIZES = "View/StoreOption.properties";
  public static final String STORE_CSS = "View/StoreOption.css";

  private final Properties shopImages;
  private final Properties shopProperties;
  private final VBox shopItems;
  private CoinDisplay coinDisplay;
  private HealthDisplay healthDisplay;


  public Shop() {
    super();
    this.setId("Shop");
    PropertiesReader propertiesReader = new PropertiesReader();
    shopImages = (Properties) propertiesReader.read(SHOP_PROPERTIES);
    shopProperties = (Properties) propertiesReader.read(SHOP_SIZES);
    shopItems = new VBox(Integer.parseInt(shopProperties.getProperty("Spacing")));
    this.getStylesheets().add(STORE_CSS);
    setUpShop();
  }

  //TODO: maximum number of items then you scroll
  private void setUpShop() {
    addStatusDisplays();
    for (String shopOptions : shopImages.stringPropertyNames()) {
      if (shopOptions.equals("Background")) {
        BackgroundImage backgroundImage = new BackgroundImage(
            ViewUtilities.createImage(shopImages.getProperty(shopOptions)),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        continue;
      }
      VBox imageTile = new VBox();
      ImageView optionImage = ViewUtilities.createImageView(shopImages.getProperty(shopOptions));
      setImageFactors(optionImage, shopOptions);
      setDragDrop(optionImage);
      optionImage.setOnMouseEntered(event -> description(shopOptions));
      optionImage.setOnMouseExited(event -> this.setBottom(null));
      imageTile.setAlignment(Pos.CENTER);
      imageTile.getChildren().add(optionImage);
      imageTile.getChildren().add(createPrice(shopOptions));

      shopItems.getChildren().add(imageTile);
    }
    this.setCenter(shopItems);
  }

  private void description(String shopOptions) {
    VBox descriptor = new VBox();
    descriptor.setId("descriptionBox");
    StackPane descriptionBox = new StackPane();
    Rectangle backgroundBox = new Rectangle(Integer.parseInt(shopProperties.getProperty("Width")),
        Integer.parseInt(shopProperties.getProperty("Height")));
    backgroundBox.setId("BackgroundBox");
    Text description = ViewUtilities
        .createText(shopOptions, shopProperties.getProperty(shopOptions + "Text"));
    description.setWrappingWidth(Integer.parseInt(shopProperties.getProperty("TextWrapping")));
    description.wrappingWidthProperty().bind(backgroundBox.widthProperty());
    descriptionBox.getChildren().addAll(backgroundBox, description);
    descriptor.getChildren().add(descriptionBox);
    this.setBottom(descriptor);
  }

  private void addStatusDisplays() {
    coinDisplay = new CoinDisplay();
    coinDisplay.setAlignment(Pos.CENTER);
    healthDisplay = new HealthDisplay();
    VBox statusDisplay = new VBox();
    statusDisplay.setPrefWidth(Integer.parseInt(shopProperties.getProperty("Width")));
    statusDisplay.getChildren().add(healthDisplay);
    statusDisplay.getChildren().add(coinDisplay);
    shopItems.getChildren().add(statusDisplay);

  }

  private Node createPrice(String shopOptions) {
    StackPane price = new StackPane();
    String id = shopOptions + "Price";
    price.setId(id);
    Text priceText = ViewUtilities.createText(id, shopProperties.getProperty(id));
    price.getChildren().add(priceText);
    return price;
  }

  private void setImageFactors(ImageView optionImage, String shopOptions) {
    optionImage.setId(shopOptions);
    optionImage.setFitWidth(Double.parseDouble(shopProperties.getProperty(shopOptions)));
    optionImage.setFitHeight(Double.parseDouble(shopProperties.getProperty(shopOptions)));
    optionImage.setPreserveRatio(true);
  }

  private void setDragDrop(ImageView optionImage) {
    optionImage.setOnDragDetected(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Dragboard areas = optionImage.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(optionImage.getImage());
        areas.setContent(content);
        event.consume();
      }
    });
    optionImage.setOnDragOver(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        if (event.getGestureSource() != optionImage && event.getDragboard().hasImage()) {
          event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
      }
    });
  }

  public CoinDisplay getCoinDisplay() {
    return coinDisplay;
  }

  public HealthDisplay getHealthDisplay() {
    return healthDisplay;
  }
}



