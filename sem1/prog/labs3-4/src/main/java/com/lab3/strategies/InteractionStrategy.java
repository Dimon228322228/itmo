package com.lab3.strategies;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.InhabitedPlace;
import com.lab3.locations.Place;
import com.lab3.entities.Character;

public interface InteractionStrategy {
	public void moveThingToPlace(Thing thing, Place oldPlace, Place place);
	public Thing pickUpThing(Thing thing, Place place);
	public boolean dropThing(Thing thing, Place toPlace);
	public void pullOut(Thing thing, House house, Place newPlace);
  public String sayToAll(InhabitedPlace location, String message);
  public void sayToOne(Character character, String message);
	public Thing getRandomFurniture();
}