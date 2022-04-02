package com.lab3.entities;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;

public class Owl extends Character{
  // private static final Action PickUpThing = null;
	private String actionCharacteristic = "со старческой нерасторопностью";

	public Owl(String name, InteractionStrategy strategy) { super(name, strategy); }
  
  public void doAction(String action) {
    System.out.printf("%s %s %s\n", name, actionCharacteristic, action);
  }

	void responseWithIndignation(String respone) {
		System.out.println(this.getName() + " c негодованием отвечала: " + respone);
	}
	void beSurprised() {
		System.out.println(this.getName() + " удивилась.");
	}
	void look() {
		System.out.println(this.getName() + " посмотрела.");
	}
	void laughWithSarcasm() {
		System.out.println(this.getName() + " саркастически засмеялась.");
	}
	void explain(String message) {
		System.out.print(this.getName() + "объяснила, что " + message);
	}

  @Override
public void sayToAll( String message) {
    System.out.printf("%s сказала всем: \"%s\"\n", name, message);
    super.sayToAll(message);
  }
  
  @Override
  public void sayToOne(Character character, String message) {
    System.out.printf("%s сказала персонажу %s: \"%s\"\n", name, character, message);
    super.sayToOne(character, message);
  }
  
  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s %s вытаскивает предмет \"%s\" из Дома.\n", name, actionCharacteristic, house);
    super.pullOut(thing, house, newPlace);
  }
  
  @Override
  public Thing pickUpThing(Thing thing, Place fromPlace) {
    System.out.printf("%s подняла вещь \"%s\"\n", name, thing);
    return super.pickUpThing(thing, fromPlace);
  }

  @Override
  public boolean dropThing(Place toPlace) {
    boolean dropped = super.dropThing(toPlace);
    if (dropped) {
      System.out.printf("%s уронила вещь \"%s\"");
    }
    return dropped;
  }
}
