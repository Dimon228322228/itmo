package com.lab3.locations;

import java.util.ArrayList;
import java.util.Random;

import com.lab3.interfaces.AbleToContainCharacters;
import com.lab3.entities.Character;
import com.lab3.entities.CharactersGroup;

public class InhabitedPlace extends Place implements AbleToContainCharacters {
	protected ArrayList<Character> characters;
	public InhabitedPlace(String name) { 
		super(name);
		characters = new ArrayList<Character>();
	}
	
	public void describe() {
		if (characters.isEmpty()) {
			System.out.println("На местности \"" + name + "\" никого нет.");
			return;
		}

		String output = "На местности \"" + name + "\" собрались";
		ArrayList<Character> characters = getCharacters();
		for (Character e : characters) {
			output += " \"" + e.getName() + "\"";
		}
		System.out.println(output);
	}

	public boolean containsCharacter(Character character) {
		for (Character e : characters) {
			if (e.equals(character)) {
				return true;
			}
		}
		return false;
	}

	public void addCharacters(Character ... new_characters) {
		for (Character c : new_characters) {
			this.moveCharacter(c);
		}
	}
	
	private void moveCharacter(Character c) {
			if (c.getPlace() != null) {
				InhabitedPlace prev_place = c.getPlace();
				prev_place.removeCharacter(c);
			}
			characters.add(c);
			c.setPlace(this);
	}
	
	public void moveWithMessage(Character character, String message) {
		System.out.printf("%s %s\n", character, message);
		this.moveCharacter(character);
	}

	public void moveWithMessage(CharactersGroup group, String message) { 
		System.out.printf("%s %s %s\n", group.getNames(), message, this.name);
		for (Character character : group.getCharacters()) {
			this.moveCharacter(character);
		}
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public void removeCharacter(Character character) {
		for (Character e : characters) {
			if (e.equals(character)) {
				characters.remove(e);
				return;
			}
		}
	}
	
	public Character getRandomCharacter() { 
		return characters.get(new Random().nextInt(characters.size()));
	}
}
