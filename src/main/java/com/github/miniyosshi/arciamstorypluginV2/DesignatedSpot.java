package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DesignatedSpot extends DesignatedPosition {
	
	private Location location;
	
	@JsonCreator
	public DesignatedSpot(@JsonProperty("name") String name, @JsonProperty("serializedLocation")Map<String, Object> serializedLocation) {
		this.name = name;
		this.location = Location.deserialize(serializedLocation);
		
		DesignatedSpots dss = DesignatedSpots.getInstance();
		dss.add(this);
	}
	
	public Location getLocation() {
		return location;
	}
	
	@Override
	public boolean isIn(World world) {
		return location.getWorld().equals(world);
	}
	
	public boolean isIn(DesignatedArea designatedArea) {
		return designatedArea.contains(location);
	}

}
