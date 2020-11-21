package ooga.model.targets;

import ooga.model.attackers.Attacker;
import ooga.model.attackers.GlueAttacker;
import ooga.model.attackers.SniperAttacker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetalTargetTest {

    @Test
    void testUpdate() {
        Target target = new MetalTarget(1, 1, 1, 1, 1, "MetalTarget");
        Attacker attacker = new SniperAttacker(2, 1);
        int health = target.getHealth();
        health--;
        target.update(attacker);
        assertEquals(health, target.getHealth());

    }

}