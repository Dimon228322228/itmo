package com.lab3.interfaces;

import java.util.ArrayList;

import com.lab3.entities.Character;

public interface AbleToContainCharacters { 
	public boolean containsCharacter(Character character); 
	public void addCharacters(Character ... character); 
	public ArrayList<Character> getCharacters(); 
	public void removeCharacter(Character character); 
}

