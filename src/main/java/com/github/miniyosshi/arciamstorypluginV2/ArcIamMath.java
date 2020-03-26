package com.github.miniyosshi.arciamstorypluginV2;

public class ArcIamMath {
	
	public static boolean rIsBetweenPandQ (double r, double p, double q) {
		if ((p <= r && r <= q) || (q <= r && r <= p))
			return true;
		else
			return false;
		}
	
}
