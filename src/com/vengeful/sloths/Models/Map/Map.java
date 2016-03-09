package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Location;
import com.vengeful.sloths.Models.Observers.MapObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 2/21/2016.
 * This class now only contains the respawn point(See Utility.Location), an array of MapAreas, and the active mapArea
 * Map Area now contains all the tiles and logic for accessing them
 */
public class Map implements ModelVisitable, ViewObservable{
    /**
    * PRIVATE VARIABLES RESPAWN POINT(UTILITY.LOCATION) AND MAPAREAS(MAPAREA)
     */
    private Location respawnPoint;
    private MapArea[] MapAreas;
    private MapArea activeMapArea;


    private ArrayList<MapObserver> observers = new ArrayList<>();

    /**
    *CONSTRUCTORS INCLUDING THE DEFAULT CONSTRUCTOR
     */

    private static Map ourInstance = new Map();
    public static Map getInstance(){return ourInstance;}

    private Map(){

    }

    private Map(Location respawnPoint, MapArea[] MapAreas){
        this.respawnPoint = respawnPoint;
        this.MapAreas  = MapAreas;
    }


    /**
     *Allows us to call getTile on map
     * The request is forwarded to active maparea
     */
    public void addEntity(Coord c, Entity entity) {
        getTile(c).addEntity(entity);
        entity.setLocation(c);
    }

    public Tile getTile(Coord c){
        return activeMapArea.getTile(c);
    }

    public Coord getCoord(Tile t){return activeMapArea.getCoord(t);}

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
        Iterator<MapObserver> iter = observers.iterator();
        while (iter.hasNext()) {
            iter.next().alertMapAreaChange(activeMapArea);
        }
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitMap(this);
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        observers.add((MapObserver) modelObserver);
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        observers.remove((MapObserver) modelObserver);
    }
}
