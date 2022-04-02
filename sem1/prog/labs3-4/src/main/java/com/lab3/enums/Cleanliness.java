package com.lab3.enums;

import java.util.Random;

public enum Cleanliness {
	DIRTY("грязный"),
	OVERGROWN("заросший"),
	FILTHY("утонувший в грязи"),
	DIRTY_NEUTRAL("грязное"),
	DUSTY_NEUTRAL("пыльное"),
	SPOTLESS("без единой пылинки"),
	CLEAN("чистый");

  private String name;
	private static Random RANDOM = new Random();

  Cleanliness(String name) { 
		this.name = name; 
	}
	
	public static Cleanliness getRandomState() {
		return Cleanliness.values()[RANDOM.nextInt(3)];
	}

	@Override
	public String toString() {
			return name;
	}
}