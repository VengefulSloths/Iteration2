package com.vengeful.sloths.Utility;


import com.vengeful.sloths.Models.EntityMapInteractionCommands.CanMoveVisitor;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.DefaultCanMoveVisitor;
import com.vengeful.sloths.Models.Map.Map;

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

    //make one that returns tiles.
    //the order that this returns rings in is N->NE->SE->S->SW->NW
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

    //this will compare 2 coords and return the best direction (of the 6 immediate directions)
    public static Direction getCoordDirection(Coord src, Coord dst){
        int rDiff = src.getR() - dst.getR();
        int sDiff = src.getS() - dst.getS();


        if(rDiff <= -1 && sDiff == 0 ){
            return Direction.SE;
        }
        else if(rDiff <= -1 && sDiff >= 1){
            return Direction.NE;
        }
        else if(rDiff == 0  && sDiff >= 1){
            return Direction.N;
        }
        else if(rDiff == 0 && sDiff <= -1){
            return Direction.S;
        }
        else if (rDiff >= 1 && sDiff <= -1){
            return Direction.SW;
        }else if(rDiff >= 1 && sDiff == 0){
            return Direction.NW;
        }else {
            //System.out.println("fail");
            return null;

            //error
        }
    }

    /**
     * desc: Searches expanding sorted rings to find the closest movable coordinate
     * params: Coord - src coordinate to search
     * return: Coord - closest coordinate found, or null
     */
    public static Coord getClosestMovableTile(Coord src) {
        CanMoveVisitor canMoveVisitor = new DefaultCanMoveVisitor();
        Map map = Map.getInstance();
        int currRadius = 1;
        boolean foundCoord = false;

        System.out.println("Src coord: " + src);

        // First check if source coordinate is ok to move on
        map.getTile(src).accept(canMoveVisitor);
        if (canMoveVisitor.canMove()) return new Coord(src.getR(), src.getS());

        Iterator<Coord> coordRingIter = sortedRing(src, currRadius);

        System.out.println("Just made coord ring: ");
        while (coordRingIter.hasNext())
            System.out.println(coordRingIter.next());

        coordRingIter = sortedRing(src, currRadius);;

        Coord currCoord = null;
        while (!foundCoord && coordRingIter.hasNext()) {
            currCoord = coordRingIter.next();

            try {
                map.getActiveMapArea().getTile(currCoord);

                map.getTile(currCoord).accept(canMoveVisitor);
                if (canMoveVisitor.canMove()) {
                    foundCoord = true;
                    break;
                }

            } catch (IndexOutOfBoundsException e) {
                // @TODO: Need to handle a bad tile here!
                //  how do we adjust input coordinate for this?

                // USE SAFETY FUNCTION!
            }

            if (!coordRingIter.hasNext() ) {
                coordRingIter = sortedRing(src, ++currRadius);
            }
        }

        return currCoord;
    }
}
