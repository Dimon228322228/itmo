package com.lab3.entities;

import com.lab3.locations.InhabitedPlace;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;
import com.lab3.enums.Thing;
import com.lab3.locations.House;

public class TinyRu extends Character {
	private String actionCharacteristic = "в припрыжку";

  public TinyRu(String name, InteractionStrategy strategy) { super(name, strategy); }

	public void hideInHouse(House house) {
    house.moveWithMessage(this, "исчез в Доме");
	}

	public void appear(InhabitedPlace place, String action) {
    place.moveWithMessage(this, "появился " + action);
	}

  @Override
  public void doAction(String action) {
    System.out.printf("%s %s %s\n", name, actionCharacteristic, action);
  }

  @Override
public void sayToAll( String message) {
    System.out.printf("%s сказал всем: \"%s\"\n", name, message);
    super.sayToAll(message);
  }
  
  @Override
  public void sayToOne(Character character, String message) {
    System.out.printf("%s сказал персонажу %s: \"%s\"\n", name, character, message);
    super.sayToOne(character, message);
  }
  
  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s %s вытаскивает предмет \"%s\" из Дома.\n", name, actionCharacteristic, house);
    super.pullOut(thing, house, newPlace);
  }
}
