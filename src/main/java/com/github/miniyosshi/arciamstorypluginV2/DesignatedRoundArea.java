package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DesignatedRoundArea extends DesignatedArea {
	
	private Location center;
	private double radius;
	
	public DesignatedRoundArea(String name, Location center, double radius) {
		super(name);
		this.center = center;
		this.radius = radius;
	}
	
	@JsonCreator
	public DesignatedRoundArea(@JsonProperty("name") String name, @JsonProperty("serializedCenter")Map<String, Object> serializedCenter, @JsonProperty("radius") double radius) {
		super(name);
		this.center = SerializableLocation.deserialize(serializedCenter).getLocation();
		this.radius = radius;
	}
	
	public Map<String, Object> getSerializedCenter() {
		return center.serialize();
	}
	
	public double getRadius() {
		return radius;
	}
	
	@Override
	public Location getCenter() {
		return center;
	}
	
	@JsonIgnore
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@JsonIgnore
	public void setCenter(Location location) {
		center = location;
	}

	@Override
	public boolean contains(Location location) {
		double distance = location.distance(center);
		return distance <= radius;
	}

	@Override
	public boolean isIn(World world) {
		return center.getWorld().equals(world);
	}

	@Override
	public Location getRandomLocation() {
		Vector v = Vector.getRandom();
		v.normalize().multiply(ArcIamMath.randomDoubleBetween(0, radius));
		return center.add(v);
	}
}
