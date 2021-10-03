package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;


public class Facade extends PhysicalMove{
  public Facade() {
    super(Type.NORMAL, 70, 1);
  }

  protected boolean hitFlag;

  @Override
  protected void applyOppDamage(Pokemon self, double damage) {
    Status cond = self.getCondition();
    hitFlag = true;
    if (cond.equals(Status.POISON) || cond.equals(Status.BURN) || cond.equals(Status.PARALYZE)) {
      self.setMod(Stat.HP, -2 * (int) Math.round(damage));
    }
  }

  @Override
  protected String describe() {
    if (hitFlag)
      return "hits with Double damage";
    return "hits the enemy";
  }
}
