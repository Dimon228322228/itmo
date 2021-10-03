package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class RockTomb extends PhysicalMove{
  public RockTomb(){ 
    super(Type.ROCK, 60, 10);
  }

  // @Override
  // protected void applyOppEffects(Pokemon opp) {
  //   opp.setMod(Stat.SPEED, -1);
  // }
  
  @Override
  protected String describe() {
    return "used Rock Tomb";
  }
}
