package com.lab3.interfaces;

import java.util.ArrayList;

import com.lab3.enums.Thing;

public interface AbleToContainThings { 
	boolean containsThing(Thing thing); 
	void addThing(Thing thing); 
	ArrayList<Thing> getThings(); 
	boolean removeThing(Thing thing); 
}

