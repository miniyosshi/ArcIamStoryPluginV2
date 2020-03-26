package com.github.miniyosshi.arciamstorypluginV2.constellation;

import ajd4jp.*;
import ajd4jp.orrery.*;
 
public class Astro {
    public static void main(String[] argv) throws Exception {
        Ephemeris.init("D:\\Documents\\DE421");
        ETD etd = new ETD(new AJD(2012, 1, 1, 15, 0, 0));
        for(Planet p: Planet.values()) {
            // 地球以外の星を表示
            if (p == Planet.EARTH) { continue; }
            if (!p.isBody()) { continue; }
 
            Zodiac  z = Zodiac.getZodiac(etd, p);
            System.out.println(p.getJpName() + "\t" + z.getJpName(true));
        }
    }
}
