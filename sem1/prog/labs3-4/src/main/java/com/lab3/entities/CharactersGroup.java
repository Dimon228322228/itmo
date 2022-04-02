package com.lab3.entities;

import java.util.ArrayList;

import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;


public class CharactersGroup {
  protected ArrayList<Character> characters = new ArrayList<Character>();
  private InteractionStrategy strategy;
  
  public CharactersGroup(InteractionStrategy strategy, Character ... new_characters) {
    for (Character c : new_characters) {
      characters.add(c);
    }
    this.strategy = strategy;
  }
  
  public ArrayList<Character> getCharacters() {
    return characters;
  }

  public String getNames() {
    String charactersStr = "";
    for (int i = 0; i < characters.size(); i++) {
      charactersStr += characters.get(i);
      if (i == characters.size() - 1) { break; }
      if (i == characters.size() - 2) { 
        charactersStr += " и ";
        continue;
       }
      charactersStr += ", ";
    }
    return charactersStr;
  }
    
	public Thing getRandomFurniture() {
    return strategy.getRandomFurniture();
	}

	public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s вытащили предмет \"%s\" из Дома и понесли его на локацию \"%s\"\n", getNames(), thing, newPlace);
    strategy.pullOut(thing, house, newPlace);
  }
  
  public void sayToOne(Character character, String message) {
    System.out.printf("%s сказали персонажу %s %s\n", this.getNames(), character, message);   
    strategy.sayToOne(character, message);
  }
  
  public void doAction(String action) {
    System.out.printf("%s %s\n", this.getNames(), action);
  }
}
