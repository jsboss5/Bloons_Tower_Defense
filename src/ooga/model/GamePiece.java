package ooga.model;

public interface GamePiece {

  void update(GamePiece piece);

  double getX();

  double getY();

  void move(int x, int y);

  int getState();
}
