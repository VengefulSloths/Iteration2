package com.vengeful.sloths.Utility;

/**
 * Created by alexs on 3/3/2016.
 */
public class CartesionDirection {
    public enum Direction {
        N, S, W, E, NW, NE, SW, SE, NONE
    }

    private int x=0;
    private int y=0;



    public Direction getDirection() {
        switch (3*x+y) {
            case 0:return Direction.NONE;
            case 1:return Direction.S;
            case -1: return Direction.N;
            case 3: return Direction.E;
            case -3: return Direction.W;
            case 4: return Direction.SE;
            case -4: return Direction.NW;
            case 2: return Direction.NE;
            case -2: return Direction.SW;
            default: return Direction.NONE;
        }
    }

    public void addDirection(Direction direction) {
        switch (direction) {
            case N: y-=1;
                break;
            case S: y+=1;
                break;
            case W: x-=1;
                break;
            case E: x+=1;
                break;
        }
        if (x > 1) x = 1;
        else if (x < -1) x = -1;
        if (y > 1) y = 1;
        else if (y < -1) y = -1;

    }

}
