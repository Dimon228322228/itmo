package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;


public class Present extends PhysicalMove {
    public Present() {
        super(Type.NORMAL, 1, 0.9);
    }
    
    /*
    This attack randomly does one of the following:
    (20% chance) - Heals the target by 1/4 of its total HP.
    (40% chance) - This attack's base power is 40.
    (30% chance) - This attack's base power is 80.
    (10% chance) - This attack's base power is 120.
     */
    @Override
    protected void applyOppDamage(Pokemon opp, double damage) {
        double chance = Math.random() * 100;
        if (chance <= 40) {
            damage  = 40.0;
        }
        if (chance > 40 && chance <= 70) {
            damage  = 80.0;
        }
        if (chance > 70 && chance <= 80) {
            damage  = 120.0;
        }
        if (chance > 80) {
            damage = -opp.getStat(Stat.HP) * 0.25;
        }
        opp.setMod(Stat.HP, (int) Math.round(damage));
    }
    
    @Override
    protected String describe(){
        return "gave a present";
    }
}
