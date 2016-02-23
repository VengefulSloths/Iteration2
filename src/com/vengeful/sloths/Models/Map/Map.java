package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Location;
import com.vengeful.sloths.Utility.Visitor;

/**
 * Created by John on 2/21/2016.
 * This class now only contains the respawn point(See Utility.Location), an array of MapAreas, and the active mapArea
 * Map Area now contains all the tiles and logic for accessing them
 */
public class Map {
    /**
    * PRIVATE VARIABLES RESPAWN POINT(UTILITY.LOCATION) AND MAPAREAS(MAPAREA)
     */
    private Location respawnPoint;
    private MapArea[] MapAreas;
    private MapArea activeMapArea;
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
     *Allows us to call getTile on map
     * The request is forwarded to active maparea
     */
    public Tile getTile(Coord c){
        return activeMapArea.getTile(c);
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

    public MapArea getActiveMapArea() {
        return activeMapArea;
    }

    public void setActiveMapArea(MapArea activeMapArea) {
        this.activeMapArea = activeMapArea;
    }
}
