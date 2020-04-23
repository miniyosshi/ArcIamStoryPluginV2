package com.github.miniyosshi.arciamstorypluginV2;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Location（座標）をシリアライズ・デシリアライズするためのラッパークラス
 */
public class SerializableLocation{

	/** ワールド名を保存するキー名 */
	static final private String WORLD="world";

	/** X座標の値を保存するキー名 */
	static final private String X="x";

	/** Y座標の値を保存するキー名 */
	static final private String Y="y";

	/** Z座標の値を保存するキー名 */
	static final private String Z="z";

	/** ヨーの値（向いている方角。東西南北どちらを向いているかの角度）を保存するキー名 */
	static final private String YAW="yaw";

	/** ピッチの値（向いている仰俯角。上下どちらを向いているかの角度）を保存するキー名 */
	static final private String PITCH="pitch";

	/** 座標 */
	private Location location = null;

	/**
	 * コンストラクタ
	 * 
	 * @param location
	 *            座標
	 */
	public SerializableLocation(Location location){
		this.location=location;
	}

	/**
	 * 座標を返す
	 * 
	 * @return 座標
	 */
	public Location getLocation(){
		return location;
	}

	/**
	 * 座標をシリアライズする
	 * 
	 * @return シリアライズされた座標
	 */
	public Map<String,Object> serialize(){
		return location.serialize();
	}

	/**
	 * 座標をデシリアライズする
	 * 
	 * @param map
	 *            シリアライズされた座標
	 * @return デシリアライズされた座標
	 */
	public static SerializableLocation deserialize(Map<String,Object> map){
		World world=Bukkit.getWorld(map.get(WORLD).toString());
		double x=Double.parseDouble(map.get(X).toString());
		double y=Double.parseDouble(map.get(Y).toString());
		double z=Double.parseDouble(map.get(Z).toString());
		float yaw=Float.parseFloat(map.get(YAW).toString());
		float pitch=Float.parseFloat(map.get(PITCH).toString());

		Location location=new Location(world,x,y,z,yaw,pitch);

		return new SerializableLocation(location);
	}
}
