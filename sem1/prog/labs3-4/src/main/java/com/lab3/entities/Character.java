package com.lab3.entities;

import com.lab3.locations.InhabitedPlace;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;
import com.lab3.things.Rope;

import java.util.ArrayList;
import java.util.Objects;

import com.lab3.enums.Thing;
import com.lab3.exceptions.NullPlaceException;

public class Character extends BaseCharacter {
  protected InhabitedPlace place;
  protected final String name;
  protected boolean happyFlag = false;
  protected Thing thingInHands; 
  protected InteractionStrategy strategy;
  protected boolean heardFlag = false;
  protected ArrayList<String> heardPhrases = new ArrayList<String>();

  public Character(String name, InteractionStrategy strategy) { 
    this.name = name;
    this.strategy = strategy;
  }
  
  public String getName() { return this.name; }
  public InhabitedPlace getPlace() { return this.place; }

  public void setPlace(InhabitedPlace place) { this.place = place; }

  public void sayToAll(String message) {
    if (this.place == null) {
      throw new NullPlaceException();
    }
    String heardCharacters = strategy.sayToAll(this.place, message);
    System.out.println("Персонажи " + heardCharacters + "услышали: " + message);   
  }

  public boolean hasHeard() {
    boolean lastState = heardFlag;
    setHeard(false);
    return lastState;
  }

  public boolean hasHeard(String phrase) {
    for (String heardPhrase : heardPhrases) {
      if (heardPhrase.equals(phrase)) {return true; }
    }
    heardPhrases.add(phrase);
    return false;
  }
  
  public void setHeard(boolean flag) {
    this.heardFlag = flag;
  }
  
  public void beHappy(boolean flag) { this.happyFlag = flag; }

  public boolean isHappy() { return this.happyFlag; }

  public void liftupThing(Rope rope, Thing thing) {
    System.out.println("Персонаж " + this.getName() + " поднимает предмет \"" + thing.toString() + "\" с помощью приспособления \"" + rope.toString() + "\"");
  }

  public void sayToOne(Character character, String message) { 
    strategy.sayToOne(character, message);
  }
  
  public void hitAnotherCharacter(Character character) { 
    System.out.printf("%s толкнул персонажа %s\n", name, character);
    character.dropThing(character.getPlace());
  }

	public Thing getRandomFurniture() {
		return this.strategy.getRandomFurniture();
	}
  
  @Override
	public void moveThingToPlace(Thing thing, Place oldPlace, Place newPlace) {
    this.strategy.moveThingToPlace(thing, oldPlace, newPlace); 
  }
  
  @Override
  public boolean dropThing(Place toPlace) {
    boolean dropped = this.strategy.dropThing(thingInHands, toPlace); 
    thingInHands = null;
    return dropped;
  }

  @Override
	public Thing pickUpThing(Thing thing, Place fromPlace) {
    thingInHands = thing;
    return this.strategy.pickUpThing(thing, fromPlace); 
  }
  
  @Override
  public Thing getThingInHands() {
    return this.thingInHands;
  }

  @Override
	public void pullOut(Thing thing, House house, Place newPlace) {
    this.strategy.pullOut(thing, house, newPlace); 
  }
  
  @Override
  public void doAction(String action) {
    System.out.println("Персонаж " + name + " " +  action);
  }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null) {
			return false;
		}
		if (!(o instanceof Character))
			return false;
		Character character = (Character) o;
		return Objects.equals(name, character.name) && Objects.equals(place, character.place);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, place);
	}

  @Override
  public String toString() {
      return this.name;
  }
}