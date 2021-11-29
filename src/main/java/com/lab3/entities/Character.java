package com.lab3.entities;

import com.lab3.interfaces.Moveable;
import com.lab3.locations.InhabitedPlace;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;
import com.lab3.things.Rope;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.lab3.enums.Thing;
import com.lab3.exceptions.NullPlaceException;
import com.lab3.interfaces.AbleToInteractWithThings;

public class Character extends BaseCharacter implements AbleToInteractWithThings{
  protected InhabitedPlace place;
  protected final String name;
  private boolean heardFlag = false;
  private InteractionStrategy strategy;
  private ArrayList<String> heardPhrases = new ArrayList<String>();

  Character(String name, InteractionStrategy strategy) { 
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

  public void liftupThing(Rope rope, Thing thing) {
    System.out.println("Персонаж " + this.getName() + " поднимает предмет \"" + thing.toString() + "\" с помощью приспособления \"" + rope.toString() + "\"");
  }

  public void sayToOne(Character character, String message) { 
    character.setHeard(true);
  }

  public void sayToOne(Character character) { 
    character.setHeard(true);
  }

	public Thing getRandomFurniture() {
		return this.strategy.getRandomFurniture();
	}
  
  @Override
	public void moveThingToPlace(Thing thing, Place oldPlace, Place newPlace) {
    this.strategy.moveThingToPlace(thing, oldPlace, newPlace); 
  }
  
  @Override
	public void pickUpThing(Thing thing, Place fromPlace) {
    this.strategy.pickUpThing(thing, fromPlace); 
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