package pokemons;
import moves.DizzyPunch;
import moves.RockTomb;
import moves.Swagger;
import moves.Present;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Magearna extends Pokemon {
    public Magearna(String name, int level) {
        super(name, level);
        setType(Type.STEEL, Type.FAIRY);
        setStats(80, 95, 115, 130, 115, 65);

        addMove(new RockTomb());
        addMove(new Swagger());
        addMove(new DizzyPunch());
        addMove(new Present());
    }
}
