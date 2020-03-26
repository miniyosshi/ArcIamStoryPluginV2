package com.github.miniyosshi.arciamstoryplugin;

public class ArcIamMath {
	
	public static boolean rIsBetweenPandQ (double r, double p, double q) {
		if ((p <= r && r <= q) || (q <= r && r <= p))
			return true;
		else
			return false;
		}
	
}
