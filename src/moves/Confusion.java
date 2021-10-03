package moves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Confusion extends SpecialMove {
  public Confusion() {
      super(Type.PSYCHIC, 50, 1);
  }

  private boolean effectFlag = false;

  @Override
  protected void applyOppEffects(Pokemon opp) {
      if (Math.random() <= 0.1) {
        effectFlag = true;
        Effect.confuse(opp);
      }
  }

  @Override
  protected String describe() {
    if (effectFlag) return "used Confusion";
    return "hits the enemy";
  }
}