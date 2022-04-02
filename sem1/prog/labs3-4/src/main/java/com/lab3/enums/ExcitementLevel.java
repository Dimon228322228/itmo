package com.lab3.enums;

import com.lab3.exceptions.ExcitementOutOfRangeException;

public enum ExcitementLevel {
  NORMAL {
    public ExcitementLevel decrease() throws ExcitementOutOfRangeException{  
      throw new ExcitementOutOfRangeException();
    }
  },
  MEDIUM,
  HIGH {
    public ExcitementLevel increase() throws ExcitementOutOfRangeException{
      throw new ExcitementOutOfRangeException();
    }
  };

  private ExcitementLevel excitementLevel;
  static final ExcitementLevel[] VALUES = values();
  public ExcitementLevel increase() throws ExcitementOutOfRangeException { 
    excitementLevel = VALUES[ordinal()+1]; 
    return excitementLevel;
  }
  public ExcitementLevel decrease() throws ExcitementOutOfRangeException { 
    excitementLevel = VALUES[ordinal()-1]; 
    return excitementLevel;
  }
  public ExcitementLevel getExcitementLevel() {return excitementLevel;}
}
