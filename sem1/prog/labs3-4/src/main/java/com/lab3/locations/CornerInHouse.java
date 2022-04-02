package com.lab3.locations;

import com.lab3.enums.Cleanliness;
import com.lab3.interfaces.AbleGetDirty;

public class CornerInHouse extends Place implements AbleGetDirty {
  private Cleanliness cleanlinessState;
  private Type cornerType;

  public CornerInHouse(String name, Cleanliness cleanliness) { 
    super(name);
    this.cleanlinessState = cleanliness;
    cornerType = new Type("деревянный");
  }

  public static class Type {
    private String type;
    public Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
  }
  
  public void setCornerType(String type) { 
    cornerType = new Type(type);
  }
  public Cleanliness getCleanliness() { return cleanlinessState; }
  public void setCleanliness(Cleanliness cleanliness) { this.cleanlinessState = cleanliness; }
  
  @Override 
  public String toString() {
    return cleanlinessState + " " + cornerType + " " + name;
  }

}
