package ooga.model.attackers;

import ooga.model.targets.RegularTarget;
import ooga.model.targets.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlueAttackerTest {

    @Test
    void testUpdate(){
        Target target = new RegularTarget(1, 1, 1, 1, 1, "RegularTarget");
        Attacker attacker = new GlueAttacker(2, 1);
        double speed = target.getSpeed();
        attacker.update(target);
        assertEquals(speed/2, target.getSpeed());

    }


}