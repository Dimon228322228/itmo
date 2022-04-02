package com.lab3.entities;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;

public abstract class BaseCharacter {
  public abstract void doAction(String name) ;
	public abstract void moveThingToPlace(Thing thing, Place oldPlace, Place newPlace);
	public abstract void pullOut(Thing thing, House house, Place newPlace);
	public abstract Thing pickUpThing(Thing thing, Place fromPlace);
	public abstract Thing getThingInHands();
	public abstract boolean dropThing(Place toPlace);
	public abstract Thing getRandomFurniture();
}
