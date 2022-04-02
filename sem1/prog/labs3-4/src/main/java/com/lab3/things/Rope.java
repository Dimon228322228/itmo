package com.lab3.things;

import com.lab3.enums.Thing;

public class Rope extends AbstractTool {
  public Rope(String name) {
    super(name);
  }
  
  public void liftUpThing(Thing thing) {
     System.out.println(this.getName() + " поднимает " + thing.toString());
  }

  public void PickUpThingThig(Thing thing) {
     System.out.println(thing.toString() + " вытаскивают, используя " + this.getName());
  }
  
  @Override
  public String toString() {
    return this.getName();
  }
}
