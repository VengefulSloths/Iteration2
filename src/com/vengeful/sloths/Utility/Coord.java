package com.vengeful.sloths.Utility;

/**
 * Created by John on 1/30/2016.
 */
public class Coord {

    private int r;
    private int s;

    public Coord(){
        r = 0;
        s = 0;
    }

    public Coord(int r, int s){
        this.r = r;
        this.s = s;
    }

    public boolean equals(Coord c) {
        return c.getR() == r && c.getS() == s;
    }

    //getters and setters

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String toString() {
        return "("+r+", "+s+")";
    }
}
