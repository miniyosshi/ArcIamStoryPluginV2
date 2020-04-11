package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Random;

public class ArcIamMath {
	
	public static boolean rIsBetweenPandQ (double r, double p, double q) {
		if ((p <= r && r <= q) || (q <= r && r <= p)) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	public static double randomDoubleBetween(double x1, double x2) {
		Random random = new Random();
		if(x1 > x2) {
			return x2 + (x1 - x2)*random.nextDouble();
		} else {
			return x1 + (x2 - x1)*random.nextDouble();
		}
	}
}
