package com.lab3.locations;

import java.util.ArrayList;
import java.util.Objects;

import com.lab3.interfaces.AbleToAge;
import com.lab3.enums.AgeTypes;
import com.lab3.enums.Cleanliness;
import com.lab3.enums.Thing;


public class House extends InhabitedPlace implements AbleToAge {

	private String name;
	private AgeTypes houseAgeType;
	private Cleanliness houseCleanliness;
	private Character owner;
	private ArrayList<CornerInHouse> corners;
	
	public House(String name, AgeTypes houseAgeType, Cleanliness houseCleanliness, ArrayList<CornerInHouse> corners) {
		super(name);
		this.name = name;
		this.houseAgeType = houseAgeType;
		this.houseCleanliness = houseCleanliness;
		this.corners = corners;
	}

  public void setAgeType(AgeTypes ageType) { this.houseAgeType = ageType; }
  public AgeTypes getAgeType() { return this.houseAgeType; }
	public Cleanliness getCleanness() { return this.houseCleanliness; }
	public void setOwner(Character owner) { this.owner = owner; }
	public Character getOwner() { return this.owner; }
	public String getName() { return this.name; }
	public Cleanliness getCornerCleanliness(int index) { return this.corners.get(index).getCleanliness(); }
	public ArrayList<Thing> getThingsInCorner(int index) { return this.corners.get(index).getThings(); }
	
  @Override
  public String toString() {
      return  houseAgeType + " " + 
							houseCleanliness + " " +
							name;
  }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null) {
			return false;
		}
		if (!(o instanceof House))
			return false;
		House house = (House) o;
		return Objects.equals(this.name, house.getName()) 
		&& Objects.equals(this.getOwner(), house.getOwner())
		&& Objects.equals(this.getAgeType(), house.getAgeType())
		&& Objects.equals(this.getCleanness(), house.getCleanness());
	}

}
