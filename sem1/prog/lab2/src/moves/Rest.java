package moves;

import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;


public class Rest extends StatusMove{
  public Rest(){
      super(Type.PSYCHIC, 0, 0);
  }
  @Override
  protected void applySelfEffects(Pokemon self){
      Effect eff = new Effect();
      eff = eff.condition(Status.SLEEP);
      eff = eff.turns(2);
      self.setMod(Stat.HP, -(int)(self.getStat(Stat.HP)-self.getHP()));
      self.addEffect(eff);
  }

  @Override
  protected boolean checkAccuracy(Pokemon self,Pokemon def){
      return true;
  }

  @Override
  protected String describe(){
      return "Healed, but felt asleap for 2 turns";
  }
}