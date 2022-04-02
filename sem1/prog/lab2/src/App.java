import ru.ifmo.se.pokemon.Battle;
import pokemons.*;


public class App {
    public static void main(String[] args) {
        Battle battleground = new Battle();

        battleground.addAlly(new Magearna("Mag", 87));
        battleground.addAlly(new Burmy("Bur", 87));
        battleground.addAlly(new Wormadam("Wor", 87));

        battleground.addFoe(new Deino("Dei", 87));
        battleground.addFoe(new Zweilous("Zwe", 87));
        battleground.addFoe(new Hydreigon("Hyd", 87));

        battleground.go();
    }
}