package pokemons;

import moves.DoubleTeam;
import moves.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Deino extends Pokemon {
  public Deino(String name, int level) {
    super(name, level);
    setType(Type.DARK, Type.DRAGON);
    setStats(52, 65, 50, 45, 50, 38);
    addMove(new Swagger());
    addMove(new DoubleTeam());
  }
}