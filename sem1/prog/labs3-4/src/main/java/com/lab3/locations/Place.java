package com.lab3.locations;

import java.util.ArrayList;

import com.lab3.enums.Thing;
import com.lab3.interfaces.AbleToContainThings;

public class Place implements AbleToContainThings {
	protected ArrayList<Thing> things;
	protected String name;

	protected Place(String name) { 
		this.name = name; 
		things = new ArrayList<Thing>();
	}

	public boolean containsThing(Thing thing) {
		for(Thing e : things){
			if(e.equals(thing)){
				return true;
			}
		}
		return false;
	}

	public void addThing(Thing thing) {
		things.add(thing);
	}

	public void addThings(Thing ... thingsToAdd) {
		for (Thing t: thingsToAdd){
			things.add(t);
		}
	}

	public ArrayList<Thing> getThings() {
		return things;
	}

	public boolean removeThing(Thing thing) {
		for(Thing e : things){
			if(e.equals(thing)){
				things.remove(e);
				return true;
			}
		}
		return false;
	}
	
	@Override
  public String toString() {
      return name;
	}  
}
