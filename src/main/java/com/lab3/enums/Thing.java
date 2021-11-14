package com.lab3.enums;

import java.util.Random;

import com.lab3.interfaces.AbleGetDirty;

public enum Thing implements AbleGetDirty {
	Chair("стул"),
	Painting("картина"),
	Table("стол"),
	AbstractThing("очередная вещь"),
	Sponge("губка") {
    @Override
    public Thing looksLike() { 
      return Thing.Fungus;
    }
  },
  Shawl("шаль") {
    @Override
    public Thing looksLike() { 
      Thing towel = Thing.Towel;
      towel.setCleanliness(Cleanliness.DIRTY);
      return towel;
    }
  },
  Towel("полотенце"),
	Fungus("поганки"),
	Doormat("дырявый половик"),
  ;

	private String name;
  private Cleanliness cleanliness;

	Thing(String name) { this.name = name; }

  public Thing looksLike() { return this; }

  public String getCleanliness() { 
    if (this.cleanliness == null) return "";
    return this.cleanliness.toString() + " ";
  }

  public void setCleanliness(Cleanliness newCleanliness) { 
    this.cleanliness = newCleanliness;
  }


	@Override
	public String toString() {
			return getCleanliness() + name;
	}

}