package com.lab3.enums;


public enum AgeTypes {
	ANCIENT("старинный"),
	AGED("поношенный временем"),
	OLD("старый"),
	NEW("новый");
  
  private String type;
  AgeTypes(String type) { this.type = type; }

	@Override
	public String toString() {
			return type;
	}
}