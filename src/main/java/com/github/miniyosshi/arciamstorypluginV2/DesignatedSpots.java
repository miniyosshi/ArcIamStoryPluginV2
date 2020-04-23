package com.github.miniyosshi.arciamstorypluginV2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DesignatedSpots extends MapDataRepository<DesignatedSpot> {
	private static final DesignatedSpots INSTANCE = new DesignatedSpots();
	
	private DesignatedSpots() {}
	
	public static DesignatedSpots getInstance() {
		return INSTANCE;
	}
	
	public Optional<Map<String, DesignatedSpot>> getElementsBy(DesignatedArea da){
		Map<String, DesignatedSpot> result = new HashMap<String, DesignatedSpot>();
		for (MapElement element : map.values()) {
			DesignatedSpot ds = (DesignatedSpot) element;
			if(ds.isIn(da)) {
				result.put(ds.getName(), ds);
			}
		}
		return Optional.ofNullable(result); 
	}
	
}
