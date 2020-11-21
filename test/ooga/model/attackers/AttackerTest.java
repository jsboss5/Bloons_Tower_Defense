package ooga.model.attackers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackerTest {

    @Test
    void testGetRange() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(3, testAttacker.getRange());
    }

    @Test
    void testGetX() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(1, testAttacker.getX());
    }

    @Test
    void testGetY() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(1, testAttacker.getY());
    }

    @Test
    void testUpgrade() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        testAttacker.upgrade();
        assertEquals(2, testAttacker.getState());
    }

    @Test
    void testGetRank() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(1, testAttacker.getState());
    }

    @Test
    void testGetState() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(1, testAttacker.getState());
    }

    @Test
    void testGetNumTargets() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        assertEquals(1, testAttacker.getNumTargets());
    }
    @Test
    void testSetRange() {
        Attacker testAttacker = new RegularAttacker(1, 1);
        testAttacker.setRange(5);
        assertEquals(5, testAttacker.getRange());
    }
    @Test
    void testSetFiringRate() {
        Attacker testAttacker = new RegularAttacker(1, 1, 1, 3, 1,1);
        testAttacker.setFiringRate(5);
        assertEquals(5, testAttacker.getFiringRate());
    }


}