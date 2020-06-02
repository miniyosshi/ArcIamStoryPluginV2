package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;
import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.World;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DesignatedSquareArea extends DesignatedArea {
	
	private Location cornerA;
	private Location cornerB;
	
	public DesignatedSquareArea(String name, Location cornerA, Location cornerB) {
		super(name);
		if(Objects.equals(cornerA.getWorld(), cornerB.getWorld())) {
			this.cornerA = cornerA;
			this.cornerB = cornerB;
		}
		else {
			System.out.println("Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
		}
	}
	
	@JsonCreator
	public DesignatedSquareArea(@JsonProperty("name")String name, @JsonProperty("serializedCornerA")Map<String, Object> serializedCornerA, @JsonProperty("serializedCornerB")Map<String, Object> serializedCornerB) {
		super(name);
		Location cA = SerializableLocation.deserialize(serializedCornerA).getLocation();
		Location cB = SerializableLocation.deserialize(serializedCornerB).getLocation();
		if(Objects.equals(cA.getWorld(), cB.getWorld())) {
			this.cornerA = cA;
			this.cornerB = cB;
		}
		else {
			System.out.println("Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
		}
	}
	@JsonProperty
	public Map<String, Object> getSerializedCornerA() {
		return cornerA.serialize();
	}
	@JsonProperty
	public Map<String, Object> getSerializedCornerB() {
		return cornerB.serialize();
	}
	
	@Override
	public Location getCenter() {
		Location vector = cornerB.subtract(cornerA);
		vector.multiply(0.5);
		return cornerA.add(vector);
	}
	@JsonIgnore
	public boolean setCornerA(Location location) {
		if(Objects.equals(location.getWorld(), cornerB.getWorld())) {
			cornerA = location;
			return true;
		} else {
			System.out.println("Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
			return false;
		}
	}
	@JsonIgnore
	public boolean setCornerB(Location location) {
		if(Objects.equals(location.getWorld(), cornerA.getWorld())) {
			cornerB = location;
			return true;
		} else {
			System.out.println("Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
			return false;
		}
	}
	
	@Override
	public boolean isIn(World world) {
		return cornerA.getWorld().equals(world);
		//これで良い？
	}
	
	@Override
	public boolean contains(Location location) {
		boolean bX = ArcIamMath.rIsBetweenPandQ(location.getX(), cornerA.getX(), cornerB.getX());
		boolean bZ = ArcIamMath.rIsBetweenPandQ(location.getZ(), cornerA.getZ(), cornerB.getZ());
		return bX&&bZ;
	}

	@Override
	public Location getRandomLocation() {
		double x = ArcIamMath.randomDoubleBetween(cornerA.getX(), cornerB.getX());
		double y = ArcIamMath.randomDoubleBetween(cornerA.getY(), cornerB.getY());
		double z = ArcIamMath.randomDoubleBetween(cornerA.getZ(), cornerB.getZ());
		
		return new Location(cornerA.getWorld(), x, y, z);
	}

}
