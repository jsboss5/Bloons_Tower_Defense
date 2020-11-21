package ooga.model.attackers;

import ooga.model.targets.RegularTarget;
import ooga.model.targets.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TackAttackerTest {

    @Test
    void testUpdate(){
        Target target = new RegularTarget(1, 1, 1, 1, 1, "RegularTarget");
        Attacker attacker = new TackAttacker(2, 1);
        int health = target.getHealth();
        attacker.update(target);
        assertEquals(health - 1, target.getHealth());

    }

}