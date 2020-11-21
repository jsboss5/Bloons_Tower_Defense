package ooga.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testReadIn() throws FileNotFoundException {
        Grid testGrid = new Grid("data/gamepathCoord01.csv");
        List<List<GridCell>> testBoard = testGrid.getGrid();
        List<List<GridCell>> actualGrid = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            actualGrid.add(new ArrayList<>());
                for(int j = 0; j < 3; j++){
                    actualGrid.get(i).add(new LandCell(0, 1));
                }
        }
        actualGrid.get(0).set(1, new Path(0, 1));
        actualGrid.get(1).set(0, new Path(1, 0));
        actualGrid.get(2).set(1, new Path(2, 1));
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                assertEquals(actualGrid.get(row).get(col).getPieces().size(), testBoard.get(row).get(col).getPieces().size());
            }
        }
    }


//    @Test
//    void testRemoveGamePiece() throws FileNotFoundException {
//        Grid testGrid = new Grid("data/gamepath_test.csv");
//        Set<GamePiece> piecesHere = testGrid.getPiecesAtPosition(1, 0);
//        GamePiece toRemove = null;
//        for(GamePiece piece: piecesHere){
//            if(piece instanceof Path){
//                toRemove = piece;
//            }
//        }
//        testGrid.removeGamePiece(toRemove);
//        assertEquals(0, testGrid.getPiecesAtPosition(1, 0).size());
//    }

    @Test
    void updateGrid() {
    }
}