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
    @Override
    public boolean equals(Object c) {
        Integer r1 = new Integer(r);
        Integer r2 = new Integer(((Coord) c).getR());
        Integer s2 = new Integer(((Coord) c).getS());
        Integer s1 = new Integer(s);
        //System.out.println("doing equals");
        return (r1.equals(r2) && s1.equals(s2));
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
