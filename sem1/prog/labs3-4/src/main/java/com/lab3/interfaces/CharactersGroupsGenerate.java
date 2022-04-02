package com.lab3.interfaces;

import java.util.ArrayList;

import com.lab3.entities.CharactersGroup;
import com.lab3.strategies.InteractionStrategy;
import com.lab3.entities.Character;

public interface CharactersGroupsGenerate {
  public ArrayList<CharactersGroup> genGroups(InteractionStrategy strategy, Character ... characters);
}
