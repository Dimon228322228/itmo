package com.lab3.entities;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;

public class Human extends Character {
  public Human(String name, InteractionStrategy strategy) { super(name, strategy); }
  
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
    System.out.printf("%s вытаскивает предмет \"%s\" из Дома.\n", name, house);
    super.pullOut(thing, house, newPlace);
  }
}
