package pokemons;
import moves.Rest;
import moves.Swagger;
import moves.Facade;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Burmy extends Pokemon {
    public Burmy(String name, int level) {
        super(name, level);
        setType(Type.BUG);
        setStats(40, 29, 45, 29, 45, 36);
        
        addMove(new Rest());
        addMove(new Swagger());
        addMove(new Facade());
    }
}
