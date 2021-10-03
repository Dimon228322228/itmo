package pokemons;
import moves.PoisonPowder;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Wormadam extends Burmy{
  public Wormadam(String name, int level) {
      super(name, level);
      setType(Type.BUG, Type.GRASS);
      setStats(60, 59, 85, 79, 105, 36);
      addMove(new PoisonPowder());
  }
}
