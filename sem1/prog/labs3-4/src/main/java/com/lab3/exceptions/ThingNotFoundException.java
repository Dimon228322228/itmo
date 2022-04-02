package com.lab3.exceptions;

import com.lab3.enums.Thing;
import com.lab3.locations.Place;

public class  ThingNotFoundException extends RuntimeException {
  public ThingNotFoundException(Thing thing, Place place) {
    super("Предмет " + thing + " не найден в локации " + place);
  }
}
