package ooga.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void testGetX() {
        Path testPath = new Path(1, 1);
        assertEquals(1, testPath.getX());
    }

    @Test
    void testGetY() {
        Path testPath = new Path(1, 1);
        assertEquals(1, testPath.getY());
    }

}