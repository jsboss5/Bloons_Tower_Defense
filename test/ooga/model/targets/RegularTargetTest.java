package ooga.model.targets;

import ooga.model.attackers.Attacker;
import ooga.model.attackers.RegularAttacker;
import ooga.model.attackers.SniperAttacker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularTargetTest {

    @Test
    void testUpdate() {
        Target target = new RegularTarget(1, 1, 1, 1, 1, "RegularTarget");
        Attacker attacker = new RegularAttacker(2, 1);
        int health = target.getHealth();
        health--;
        target.update(attacker);
        assertEquals(health, target.getHealth());

    }

}