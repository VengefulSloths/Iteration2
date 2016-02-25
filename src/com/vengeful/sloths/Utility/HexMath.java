package com.vengeful.sloths.Utility;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */


//Define all hexagon math and formulas in here for reuse.
public class HexMath {
    public static Iterator<Coord> ring(Coord center, int radius) {
        ArrayList<Coord> coords = new ArrayList<>();

        if (radius == 0) {
            coords.add(center);
        } else {
            int r = center.getR();
            int s = center.getS();

            for (int n = 0; n < radius; n++) {
                // Get top-right and bottom-left slabs
                coords.add(new Coord(r + n, s - radius));
                coords.add(new Coord(r - n, s + radius));

                //Get right and left slabs
                coords.add(new Coord(r + radius, s - radius + n));
                coords.add(new Coord(r - radius, s + radius - n));

                //Get bottom-right and top-left slabs
                coords.add(new Coord(r + radius - n, s + n));
                coords.add(new Coord(r - radius + n, s - n));
            }
        }
        return coords.iterator();
    }

    public static Iterator<Coord> sortedRing(Coord center, int radius) {
        ArrayList<Coord> coords = new ArrayList<>();

        if (radius == 0) {
            coords.add(center);
        } else {
            int r = center.getR();
            int s = center.getS();

            //Top-left
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r + n, s - radius));
            }
            //Right
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r + radius, s - radius + n));
            }
            //Bottom-right
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r + radius - n, s + n));
            }
            //Bottom-left
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r - n, s + radius));
            }
            //Left
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r - radius, s + radius - n));
            }
            //Top-right
            for (int n = 0; n < radius; n++) {
                coords.add(new Coord(r - radius + n, s - n));
            }
        }

        return coords.iterator();
    }

    public static Iterator<Coord> hexagon(Coord center, int radius) {
        ArrayList<Coord> coords = new ArrayList<>();
        for (int r=0; r<=radius; r++) {
            Iterator<Coord> currentRing = sortedRing(center, r);
            while (currentRing.hasNext()) {
                coords.add(currentRing.next());
            }
        }
        return coords.iterator();
    }

    public static Iterator<Coord> saftey(Iterator<Coord> coords, int rMax, int sMax) {
        ArrayList<Coord> safeCoords = new ArrayList<>();

        while (coords.hasNext()) {
            Coord current = coords.next();
            if (    current.getR() < rMax &&
                    current.getS() < sMax &&
                    current.getS() >= 0 &&
                    current.getR() >= 0) {
                safeCoords.add(current);
            }
        }

        return safeCoords.iterator();
    }
}
