package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Location;
import com.vengeful.sloths.Utility.Visitor;

/**
 * Created by John on 2/21/2016.
 * This class now only contains the respawn point(See Utility.Location) and an array of MapAreas
 * Map Area now contains all the tiles and logic for accessing them
 */
public class Map {
    /**
    * PRIVATE VARIABLES RESPAWN POINT(UTILITY.LOCATION) AND MAPAREAS(MAPAREA)
     */
    private Location respawnPoint;
    private MapArea[] MapAreas;
    /**
    *CONSTRUCTORS INCLUDING THE DEFAULT CONSTRUCTOR
     */
    public Map(){

    }

    public Map(Location respawnPoint, MapArea[] MapAreas){
        this.respawnPoint = respawnPoint;
        this.MapAreas  = MapAreas;
    }

    /**
     * Visitor is abstract defined in Utilities.Visitor
     */
    public void visit(Visitor v){
        v.visitMap(this);
    }
    /**
    *GETTER AND SETTERS FOR ALL PRIVATE VARIABLES
     */
    public void setRespawnPoint(Location coord){

        this.respawnPoint = coord;
    }

    public Location getRespawnPoint(){

        return this.respawnPoint;
    }

    public MapArea[] getMapAreas() {
        return MapAreas;
    }

    public void setMapAreas(MapArea[] mapAreas) {
        MapAreas = mapAreas;
    }

}
