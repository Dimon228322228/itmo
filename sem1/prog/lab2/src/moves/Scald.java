package moves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Scald extends SpecialMove {
  public Scald() {
      super(Type.WATER, 50, 1);
  }

  private boolean burnedFlag = false;

  @Override
  protected void applyOppEffects(Pokemon opp) {
      if (Math.random() <= 0.3) {
        burnedFlag = true;
        Effect.burn(opp);
      }
  }

  @Override
  protected String describe() {
    if (burnedFlag) return "Burned the enemy";
    return "hits the enemy";
  }
}