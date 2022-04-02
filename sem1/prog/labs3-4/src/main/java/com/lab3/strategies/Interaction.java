package com.lab3.strategies;

import com.lab3.enums.Thing;
import com.lab3.exceptions.ThingNotFoundException;
import com.lab3.locations.House;
import com.lab3.locations.InhabitedPlace;
import com.lab3.locations.Place;

import java.util.Random;

import com.lab3.entities.Character;

public class Interaction implements InteractionStrategy {
  @Override 
  public void moveThingToPlace(Thing thing, Place oldPlace, Place newPlace) {
    oldPlace.removeThing(thing);
    newPlace.addThing(thing);
  }

  @Override
  public Thing pickUpThing(Thing thing, Place fromPlace) {
    if (!fromPlace.removeThing(thing)) {
      throw new ThingNotFoundException(thing, fromPlace);
    }
    return thing;
  }
  
  @Override
  public boolean dropThing(Thing thing, Place toPlace) {
    if (thing == null) return false;
    toPlace.addThing(thing);
    return true;
  }

  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    this.moveThingToPlace(thing, house, newPlace);
  }

  @Override
  public String sayToAll(InhabitedPlace location, String message) {
    String heardCharacters = "";
    for (Character c : location.getCharacters()) {
      if(tryToHear(message)) {
        c.setHeard(true);
        heardCharacters += c.getName() + " ";
      }
    }
    return heardCharacters;
  }

  @Override
	public Thing getRandomFurniture() {
		return Thing.values()[new Random().nextInt(4)];
	}
  
  @Override
  public void sayToOne(Character character, String message) {
    character.setHeard(true);
  }
    
  private boolean tryToHear(String message) {
    if (getRandomBoolean()) {
      return true;
    }
    return false;
  }

  private boolean getRandomBoolean(){
    return Math.random() < 0.5;
  }
}
