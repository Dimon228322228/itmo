package com.lab3.entities;

import com.lab3.enums.ExcitementLevel;
import com.lab3.enums.Thing;
import com.lab3.exceptions.ExcitementOutOfRangeException;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.strategies.InteractionStrategy;

public class Piglet extends Character {
	private String actionCharacteristic = "похрюкивая";
  protected ExcitementLevel excitement;
  protected Ears ears;

  public Piglet(String name, InteractionStrategy strategy) { 
    super(name, strategy);
    this.ears = new Ears();
    this.excitement = ExcitementLevel.NORMAL;
  }
  
  public void cough() { 
    if (Math.random() < 0.1) { 
      changeExcitement(false);
    }
  }

  public void changeExcitement(boolean positive) {
    if (positive) {
      try {
        excitement = excitement.increase();
      }
      catch (ExcitementOutOfRangeException e) {
        System.out.println("Пятачок настолько воодушевился, что больше не может");
        return;
      }
    } else {
      try {
        excitement = excitement.decrease();
      }
      catch (ExcitementOutOfRangeException e) {
        System.out.println("Воодушевление Пяточка вернулось в норму");
        return;
      }
    }
    switch (excitement) {
      case NORMAL:
        ears.getNormal();
        break;
      case MEDIUM:
        happyFlag = true;
        ears.getPinky();
        break;
      case HIGH:
        ears.burnWithRed();
        break;
    }
  }
  
  public class Ears {
    private String earsColor;
    private String earsFeeling;

    public void getNormal() {
      System.out.println("Цвет ушей пяточка нормализовался\n");
      earsColor = "normalPink";
      earsFeeling = "warm";
    }

    public void getPinky() {
      System.out.println("Уши Пяточка порозовели\n");
      earsColor = "pink";
      earsColor = "hot";
    }

    public void burnWithRed() {
      System.out.println("Уши Пяточка запылали\n");
      earsColor = "red";
      earsFeeling = "burning";
    }
    
    public String getEarsColor() { return earsColor; }
    public String getEarsFeeling() { return earsFeeling; }
  }

  @Override
  public void doAction(String action) {
    System.out.printf("%s %s %s\n", name, actionCharacteristic, action);
  }

  @Override
  public void sayToAll( String message) {
    if (excitement.ordinal() > ExcitementLevel.MEDIUM.ordinal()) {
      return; // Can't say anything when excited
    }
    System.out.printf("%s сказал всем: \"%s\"\n", name, message);
    super.sayToAll(message);
  }
  
  @Override
  public void sayToOne(Character character, String message) {
    if (excitement.ordinal() > ExcitementLevel.MEDIUM.ordinal()) {
      return; // Can't say anything when excited
    }
    System.out.printf("%s сказал персонажу %s: \"%s\"\n", name, character, message);
    super.sayToOne(character, message);
  }
  
  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s %s вытаскивает предмет \"%s\" из Дома.\n", name, actionCharacteristic, house);
    super.pullOut(thing, house, newPlace);
  }
}
