package moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;


public class PoisonPowder extends StatusMove {
  public PoisonPowder() {
    super(Type.POISON, 0, 0.75);
  }

  @Override
  protected void applyOppEffects(Pokemon opp) {
    Effect.poison(opp);
  }

  @Override
  protected String describe() {
    return "used Poison Powder";
  }
}
