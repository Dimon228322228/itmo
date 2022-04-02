package moves;


import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

public class DizzyPunch extends PhysicalMove {
    public DizzyPunch() {
        super(Type.NORMAL, 70, 1.0);
    }
    
    @Override
    protected void applyOppEffects(Pokemon opp) {
      Effect e = new Effect().chance(0.2).turns(-1);
      e.confuse(opp);
    }
    
    @Override
    public String describe(){
        return "used Dizzy Punch";
    }
}
