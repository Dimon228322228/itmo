package com.lab3.entities;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;

public class Kenga extends Character {
	private boolean nervousFlag = false;
  public Kenga(String name, InteractionStrategy strategy) { super(name, strategy); }

  public void doAction(String action) {
    System.out.println(name + " " +  action);
  }
	public void tieKnots() {
		System.out.println(this.getName() + " связывает узлы");
	}

	public void getNervous(String reason) {
		nervousFlag = true;
		System.out.println(this.getName() + " нервничала, потому что " + reason);
	}
	
	public boolean isNervous() {return this.nervousFlag; }

	public void shoutAt(Character character, String message) {
		System.out.println(this.getName() + " покрикивала на" + character.getName() + ": "
				+ message);
	}

	public boolean distinguish(Thing thing1, Thing thing2) {
		if ("губка".equals(thing1.toString()) &&  "поганки".equals(thing2.toString())) {
			System.out.print(this.getName() + " не может отличить предмет " + thing1.toString() + " от " + thing2.toString());
			return false;
		}
		return true;
	}

  @Override
	public void sayToAll( String message) {
    System.out.printf("%s сказала всем: \"%s\"\n", name, message);
    super.sayToAll(message);
  }
  
  @Override
  public void sayToOne(Character character, String message) {
    System.out.printf("%s сказала персонажу %s: \"%s\"\n", this.name, character, message);
    super.sayToOne(character, message);
  }
  
  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s в припрыжку вытаскивает предмет \"%s\" из Дома.\n", name, house);
    super.pullOut(thing, house, newPlace);
  }
}
