package ooga.view;

public interface Updatable {

  void updateNextLocation(double xCord, double yCord);

  void moveObject();

  void updateImage(String objectType);

  void setId(String id);
}
