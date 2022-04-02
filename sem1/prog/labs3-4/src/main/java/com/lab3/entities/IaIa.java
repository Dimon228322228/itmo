package com.lab3.entities;

import com.lab3.strategies.InteractionStrategy;

public class IaIa extends Character {
  public IaIa(String name, InteractionStrategy strategy) { super(name, strategy); }
  private boolean paysAttention = false;

  public void doAction(String action) {
    System.out.printf("%s %s\n", name, action);
  }
  
  public void payAttention(String action) {
    String res = name;
    if (!paysAttention) {res += " не";}
    System.out.printf("%s обратил внимания на %s\n", res, action);
  }
}
