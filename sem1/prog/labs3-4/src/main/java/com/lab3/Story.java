package com.lab3;

import java.util.ArrayList;

import com.lab3.entities.*;
import com.lab3.entities.Character;
import com.lab3.locations.*;
import com.lab3.misc.Song;
import com.lab3.strategies.*;
import com.lab3.things.Rope;
import com.lab3.enums.*;
import com.lab3.interfaces.CharactersGroupsGenerate;

public class Story {

  public static void fillAllCharacters(ArrayList<Character> allArrayList, Character ... c) {
    for (Character e : c) {
      allArrayList.add(e);
    }
  }

  public static void main(String ... args) {
    class SceneBuilder {
      public ArrayList<CornerInHouse> create4Corners() {
        ArrayList<CornerInHouse> corners = new ArrayList<CornerInHouse>();      
        for (int i = 0; i < 4; i++) {
          Cleanliness cleanState = Cleanliness.getRandomState();
          corners.add(new CornerInHouse("угол", cleanState));
        }
        return corners;
      }

      public House prepareHouse() { 
        ArrayList<CornerInHouse> corners = create4Corners();
        corners.get(0).addThing(Thing.Sponge);
        House oldOwlsHouse = new House("Дом Совы", AgeTypes.OLD, Cleanliness.getRandomState(), corners);

        oldOwlsHouse.addThings(Thing.Chair, Thing.Painting, Thing.Shawl, Thing.Sponge);
        return oldOwlsHouse;
      }
    }
    
    SceneBuilder mainScene = new SceneBuilder();

    ArrayList<Character> allCharacters = new ArrayList<Character>();
    
		House oldOwlsHouse = mainScene.prepareHouse();
		House newOwlsHouse = new House("Дом Совы", AgeTypes.NEW, Cleanliness.CLEAN, mainScene.create4Corners());

    InhabitedPlace nearOwlHouse = new InhabitedPlace("Возле бывшего дома Совы");
    InhabitedPlace nearIaHouse = new InhabitedPlace("Возле дома Кролика");
    InhabitedPlace darkForest = new InhabitedPlace("Дремучий Лес");

    InteractionStrategy strategy = new Interaction();

		Human christopherRobin = new Human("Кристофер Робин", strategy);
		IaIa iaIa = new IaIa("Иа-иа", strategy);
		Kenga kenga = new Kenga("Кенга", strategy);
		Owl owl = new Owl("Сова", strategy);
		Pooh pooh = new Pooh("Винни-Пух", strategy);
		Rabbit rabbit = new Rabbit("Кролик", strategy);
		TinyRu tinyRu = new TinyRu("Крошка Ру", strategy);
		Piglet piglet = new Piglet("Пяточок", strategy);
    
    fillAllCharacters(allCharacters, christopherRobin, iaIa, kenga, owl, pooh, rabbit, tinyRu, piglet);
    
    // Кролик попереминался и ушёл
    nearIaHouse.addCharacters(iaIa, rabbit);
    rabbit.setImpatientce(true);
    nearIaHouse.removeCharacter(rabbit);
    
    
    // Пух разыскал пяточка
    pooh.lookFor(piglet);
    CharactersGroup poohAndPig = new CharactersGroup(strategy, pooh, piglet);
    darkForest.moveWithMessage(poohAndPig, "вошли в");
    
    // Пух придумал песню
    Song poohSong = pooh.makeupNewSong("Хвалебную Песню (Кричалку)", 7);
    
    // Пяточок начинает краснеть 
    piglet.changeExcitement(true);
    piglet.cough();
    piglet.cough();

    pooh.singSong(poohSong, piglet);
    if (piglet.hasHeard()) {
      piglet.changeExcitement(true);
    }

    String importantStanzas = poohSong.getImportantStanzas().get(0);
    String importantStanzas2 = poohSong.getImportantStanzas().get(1);
    if (!piglet.hasHeard(importantStanzas)) {
      System.out.printf("Никто никогда ещё не пел Пяточку, чтобы он \"%s\"\n", importantStanzas);
    }
    piglet.hasHeard(importantStanzas2);
    
    if (piglet.hasHeard(importantStanzas2)) {
      System.out.printf("%s очень хотел попросить спеть строфу \"%s\" еще раз \n", piglet, importantStanzas2);
    }

    if (piglet.isHappy()) {
      System.out.printf("%s вздохнул от счастья\n", piglet);
    }
    
    System.out.println();
    
    // Second part of the Story
    nearOwlHouse.addCharacters(christopherRobin, kenga, owl, pooh, rabbit, tinyRu, piglet);

    // описать, кто собрался на поляне
    nearOwlHouse.describe();
    
    // Винни и Пяточoк подошли к бывшему дому Совы
    nearOwlHouse.moveWithMessage(poohAndPig, "подошёли к локации");
    // nearOwlHouse.moveWithMessage(piglet, "подошёл к бывшему дому Совы");
    
    // на поляне были все, кроме
    System.out.print("На поляне были все, кроме персонажа с именем ");
    for (Character character : allCharacters) {
      if (!nearOwlHouse.containsCharacter(character)) {
        System.out.print(character.getName() + ", ");
      }
    }
    System.out.println();

    String task = "разбирать вещи из дома";

    // Кристофер Робин всем объяснял, что делать, 
    christopherRobin.sayToAll("Всем нужно " + task + ", чтобы всё было готово к переезду в " + newOwlsHouse.toString());

    // и Кролик объяснял всем то же самое, на тот случай, если они не расслышали,
    rabbit.sayToAll("Всем нужно " + task);
    System.out.println();
    
    // Они где-то раздобыли канат и вытаскивали стулья и картины, и всякие вещи из прежнего дома Совы, чтобы все было готово для переезда в новый дом. 
    Character someOne = nearOwlHouse.getRandomCharacter();
    while (!someOne.hasHeard()) {
      someOne = nearOwlHouse.getRandomCharacter();
    }
    Rope rope = new Rope("канат");
    someOne.doAction("Раздобыл " + rope.getName());
    
    CharactersGroupsGenerate groupsGenerator = new CharactersGroupsGenerate() {
      @Override 
      public ArrayList<CharactersGroup> genGroups(InteractionStrategy strategy, Character ... characters) { 
        ArrayList<CharactersGroup> groups = new ArrayList<CharactersGroup>();
        for (int i = 0; i <= characters.length / 2 + 1; i+=2) {
          groups.add(new CharactersGroup(strategy, characters[i], characters[i + 1]));
        }
        return groups;
      }
    };
    
    ArrayList<CharactersGroup> groups = groupsGenerator.genGroups(strategy, kenga, owl, rabbit, tinyRu, piglet, pooh);
    
    for (CharactersGroup group : groups) {
      Thing someThing = group.getRandomFurniture();
      group.pullOut(someThing, oldOwlsHouse, nearOwlHouse);
    }

    System.out.println();
    
    // Кенга связывала узлы и покрикивала на Сову 
    Thing owlsShawl = Thing.Shawl;
    kenga.tieKnots();
    kenga.sayToOne(owl, owl.getName() + ", тебе не нужно " + 
              owlsShawl.looksLike().toString() + "? И " + Thing.Doormat.toString() + " не годится.");
    owl.sayToOne(kenga, "Конечно, " + Thing.Doormat.toString() + 
            " годится - надо только правильно расставить мебель!\n" + 
            "\tА это совсем не " + owlsShawl.looksLike().toString() + ", а моя " + owlsShawl.toString());

    System.out.println();
    
    // Крошка Ру поминутно то исчезал в доме, то появлялся оттуда верхом на очередном предмете, 
    tinyRu.hideInHouse(oldOwlsHouse);

    //  нервировало Кенгу, потому что она не могла за ним как следует присматривать.
    if (!tinyRu.getPlace().equals(kenga.getPlace())) {
      kenga.getNervous("не может уследить за Крошкой Ру");
    }

		Thing yetAnoterThing = someOne.getRandomFurniture();

    someOne = nearOwlHouse.getRandomCharacter();
    someOne.liftupThing(rope, yetAnoterThing);
    tinyRu.appear(nearOwlHouse, "верхом на предмете \"" + yetAnoterThing.toString() + "\"");
    
    System.out.println();
    
    // Она даже накричала на Сову
    if (kenga.isNervous()) {
      kenga.sayToOne( owl, oldOwlsHouse + " это просто позор. Он такой " + oldOwlsHouse.getCleanness() + ", удивительно, что не опрокинулся раньше!"); 

      String cornerState = oldOwlsHouse.getCornerCleanliness(0).toString();
      String thingInCornerLooksLike = oldOwlsHouse.getThingsInCorner(0).get(0).looksLike().toString();
      System.out.println("Персонаж " + kenga.getName() + " видит " + cornerState + " угол");
 
      kenga.sayToOne(owl, "Вы только посмотрите, какой он " + cornerState + "! Там " + thingInCornerLooksLike);
    }

		owl.sayToOne(kenga, "Это моя " + Thing.Sponge.toString());
    System.out.print("\tЕсли ");
		if (!kenga.distinguish(Thing.Sponge, Thing.Fungus)) {
			System.out.println(", то в хорошие времена мы живем!");
		}
    
    System.out.println();
    
    // Story part 3
    
    tinyRu.hideInHouse(oldOwlsHouse);
    kenga.sayToOne(tinyRu, "Ру, милый, не полагается так разговаривать с тем, кто умеет написать слово \"суббота\"");
    nearOwlHouse.addCharacters(christopherRobin, kenga, owl, rabbit, tinyRu);

    nearOwlHouse.moveWithMessage(poohAndPig, "пришли к локации");
    pooh.singSongToAll(poohSong);

    CharactersGroup nearHouseGroup = new CharactersGroup(strategy, nearOwlHouse.getCharacters().toArray(new Character[0]));
    nearHouseGroup.sayToOne(pooh, "что им очень понравилась песня");
    
    CharactersGroup pigletAndRu = new CharactersGroup(strategy, piglet, tinyRu);
    Thing board = Thing.Board;
    
    Character someBody = new Character("Кто-то", strategy);
    someBody.hitAnotherCharacter(owl);
    board.fallDown();
    pigletAndRu.doAction("Кинулись к предмету \"" + board + "\"");
    iaIa.payAttention("это происшествие");
    iaIa.doAction("обратился к персонажу " + christopherRobin);
  }
}
