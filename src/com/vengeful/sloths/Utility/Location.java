package com.vengeful.sloths.Utility;

import com.vengeful.sloths.Models.Map.MapArea;

/**
 * Created by Ian on 2/21/2016.
 * This class was originally design to hold respawn point in the map class.
 * This clas simply combines coord with a reference to the assoicated MapArea.
 */
public class Location {
    /*
    *THE PRIVATE VARIABLES: COORD AND MAPAREA
     */
    private Coord coord;
    private MapArea mapArea;

    /*
    *CONSTRUCTORS INCLUDING THE DEFAULT CONSTRUCTOR
     */
    public Location(){

    }

    public Location(MapArea ma, Coord c ){
        mapArea = ma;
        coord = c;
    }

    /*
    *GETTER/SETTERS FOR COORD AND MAPAREA
     */
    public MapArea getMapArea() {
        return mapArea;
    }

    public void setMapArea(MapArea mapArea) {
        this.mapArea = mapArea;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

}
