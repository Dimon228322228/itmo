package com.lab3.entities;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;

public class Rabbit extends Character {
	private String actionCharacteristic = "в припрыжку";
  protected boolean impatience = false;
  private Feet feet;

  public Rabbit(String name, InteractionStrategy strategy) { 
    super(name, strategy); 
    this.feet = new Feet();
  }
  
  public void setImpatientce(boolean state) {
    this.impatience = state;
    if (state == true) {
      shuffleFeet();
      return;
    }
    stopShufflingFeet();
  }

  public boolean isImpatient() { return this.impatience; }

  public void shuffleFeet() {
    feet.shuffle();
  } 

  public void stopShufflingFeet() {
    feet.unShuffle();
  } 
  
  public class Feet {
    protected boolean shuffling = false;
    public void shuffle() {
      System.out.println(Rabbit.this.name + " нетерпиливо переминается с ноги на ногу");
      this.shuffling = true;
    }
    public void unShuffle() {
      System.out.println(Rabbit.this.name + " перестал переминаться с ноги на ногу");
      this.shuffling = false;
    }
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
