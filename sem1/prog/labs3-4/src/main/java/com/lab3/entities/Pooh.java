package com.lab3.entities;


import com.lab3.enums.Thing;
import com.lab3.locations.House;
import com.lab3.locations.Place;
import com.lab3.misc.Song;
import com.lab3.strategies.InteractionStrategy;

public class Pooh extends Character {
	private String actionCharacteristic = "думая о мёде";
  
  public Pooh(String name, InteractionStrategy strategy) {
    super(name, strategy);
  }
  
  public void lookFor(Character c) {
    do {
      System.out.printf("%s ищет персонажа с именем \"%s\"...\n", this.name, c.getName());
    } while (Math.random() > 0.5);
    System.out.printf("%s нашёл персонажа с именем \"%s\"!!!\n", this.name, c.getName());
  }
  
  public Song makeupNewSong(String title, int stanzasCount) { 
    Song newSong = new Song(this, title, stanzasCount);
    newSong.addImportantStanza("Славился, славился на века!");
    newSong.addImportantStanza("О Храбрый, Храбрый Пятачок");
    return newSong;
  }
  
  public void singSong(Song song, Character toCharacter) {
    System.out.printf("%s поёт %s персонажу %s\n", this.name, song.getTitle(), toCharacter.getName());
    super.sayToOne(toCharacter, song.getTitle());
  }

  public void singSongToAll(Song song) {
    System.out.printf("%s поёт %s всем\n", this.name, song.getTitle());
    super.sayToAll(song.getTitle());
  }

  @Override
  public void doAction(String action) {
    System.out.printf("%s %s %s\n", name, actionCharacteristic, action);
  }
 
  @Override
public void sayToAll( String message) {
    System.out.printf("%s сказал всем: \"%s\"\n", name, message);
    super.sayToAll(message);
  }
  
  @Override
  public void sayToOne(Character character, String message) {
    System.out.printf("%s сказал персонажу %s: \"%s\"\n", name, character, message);
    super.sayToOne(character, message);
  }
  
  @Override
  public void pullOut(Thing thing, House house, Place newPlace) {
    System.out.printf("%s %s вытаскивает предмет \"%s\" из Дома.\n", name, actionCharacteristic, house);
    super.pullOut(thing, house, newPlace);
  }
}