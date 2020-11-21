package ooga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

  @Test
  public void testCreateCell() {
    Cell testCell = new Cell(3, 3);
    assertNotNull(testCell);
  }

  @Test
  public void testGetX() {
    Cell testCell = new Cell(3, 5);
    int xCoord = testCell.getX();
    assertEquals(3, xCoord);
  }

  @Test
  public void testGetY() {
    Cell testCell = new Cell(5, 8);
    int yCoord = testCell.getY();
    assertEquals(8, yCoord);
  }

}
