package com.lab3.misc;

import java.util.ArrayList;
import com.lab3.entities.Character;

public class Song {
  private String title;
  private Character author;
  private int stanzasCount;
  private ArrayList<String> importantStanzas = new ArrayList<String>();

  public Song(Character author, String title, int stanzasCount) {
    this.title = title;
    this.stanzasCount = stanzasCount;
  }
  public Song(Character author, String title, int stanzasCount, String ... importantStanzas) {
    this.title = title;
    this.stanzasCount = stanzasCount;
    for (String stanza : importantStanzas) {
      this.importantStanzas.add(stanza);
    }
  }
  
  public void addImportantStanza(String stanza) {
      this.importantStanzas.add(stanza);
  }
  
  public String getTitle() { return this.title; }
  public Character getAuthor() { return this.author; }
  public int getStanzasCount() { return this.stanzasCount; }
  public ArrayList<String> getImportantStanzas() { return this.importantStanzas; }
}
