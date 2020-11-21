package ooga.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ooga.model.dataloaders.CSV_Parser;


public class Grid {

  private List<List<GridCell>> grid;
  private List<Path> gamePath;
  private int numPaths;
  private int gridRows;


  private List<String[]> readInFile;
  private int gridCols;

  public Grid(String filename) throws FileNotFoundException {
    readIn(filename);
  }

  /**
   * Takes info from config file and creates the grid from it
   *
   * @param filename
   * @throws FileNotFoundException
   */
  public void readIn(String filename) throws FileNotFoundException {
    CSV_Parser boardReader = new CSV_Parser();
    readInFile = (List<String[]>) boardReader.read(filename);
    grid = createInitialGrid(readInFile, boardReader.getRows(), boardReader.getColumns());
  }

  /**
   * | This method simply creates a 2d list
   *
   * @param configuration
   * @param rows
   * @param columns
   * @return
   */
  private List<List<GridCell>> createInitialGrid(List<String[]> configuration, int rows,
      int columns) {
    gridRows = rows;
    gridCols = columns;
    gamePath = new ArrayList<>();
    numPaths = configuration.size() - 1;
    List<List<GridCell>> newGrid = new ArrayList<>();

    addPaths(configuration);
    addLandCells(rows, columns, newGrid);
    setPathsInGrid(newGrid);

    return newGrid;
  }

  private void setPathsInGrid(List<List<GridCell>> newGrid) {
    for (Path path : gamePath) {
      int pathRow = path.getX();
      int pathCol = path.getY();
      newGrid.get(pathRow).set(pathCol, path);
    }
  }

  private void addLandCells(int rows, int columns, List<List<GridCell>> newGrid) {
    for (int r = 0; r < rows; r++) {
      newGrid.add(new ArrayList<>());
      for (int c = 0; c < columns; c++) {
        GridCell nextPath = new LandCell(r, c);
        newGrid.get(r).add(c, nextPath);
      }
    }
  }

  private void addPaths(List<String[]> configuration) {
    for (int i = 1; i < numPaths + 1; i++) {
      int currentRow = Integer.parseInt(configuration.get(i)[0]);
      int currentCol = Integer.parseInt(configuration.get(i)[1]);
      Path currentPath = new Path(currentRow, currentCol);
      gamePath.add(currentPath);
    }
  }


  public List<Path> getPathList() {
    return gamePath;
  }

  public List<List<GridCell>> getGrid() {
    return grid;
  }


  public Set<GamePiece> getPiecesAtPosition(int row, int col) {
    Set<GamePiece> pieces = grid.get(row).get(col).getPieces();
    return pieces;
  }

  public int getGridRows() {
    return gridRows;
  }

  public int getGridCols() {
    return gridCols;
  }

  public List<String[]> getReadInFile() {
    return readInFile;
  }

}
