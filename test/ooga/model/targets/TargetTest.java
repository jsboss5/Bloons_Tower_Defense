package ooga.model.targets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetTest {
    Target testTarget;

    @BeforeEach
    void beforeAll(){
        testTarget = new RegularTarget(1, 1, 1,1,1, "RegularTarget");



    }    @Test
    void testGetX() {
        assertEquals(1, testTarget.getX());
    }

    @Test
    void testGetY() {
        assertEquals(1, testTarget.getY());
    }

    @Test
    void testPop() {
        testTarget.pop();
        assertEquals(0, testTarget.getState());
    }

    @Test
    void testSetxPos() {
        testTarget.setxPos(3);
        assertEquals(3, testTarget.getX());
    }

    @Test
    void testSetyPos() {
        testTarget.setyPos(3);
        assertEquals(3, testTarget.getY());
    }

    @Test
    void testGetState() {
        assertEquals(1, testTarget.getState());
    }

    @Test
    void testSetState() {
        testTarget.setState(3);
        assertEquals(3, testTarget.getState());
    }

    @Test
    void balloonMoveTestSimple(){
        Target moveTarget = new RegularTarget(1,1,1,1,1,"RegularTarget");
        moveTarget.move(2,1);
        assertEquals(2, moveTarget.getX());
        assertEquals(1, moveTarget.getY());
    }
    @Test
    void balloonMoveTestComplex(){
        Target moveTarget = new RegularTarget(1,1,1,1,1,"RegularTarget");
        moveTarget.move(4,1);
        assertEquals(2, moveTarget.getX());

        moveTarget.move(2,8);
        assertEquals(2, moveTarget.getY());

        moveTarget.move(-1,2);
        assertEquals(1, moveTarget.getX());
        assertEquals(2, moveTarget.getY());
    }




}