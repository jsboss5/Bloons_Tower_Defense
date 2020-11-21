package ooga.model;

public interface Engine {
  //we expect there to be a front end and back end implementation of this...


  /**
   * Called every step - happens regardless of what happnes in front end
   */
  void movePieces();

  /**
   * Listeners call this with some sort of args
   */

}
