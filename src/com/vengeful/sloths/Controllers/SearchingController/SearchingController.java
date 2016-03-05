package com.vengeful.sloths.Controllers.SearchingController;

import com.vengeful.sloths.Controllers.Target.Target;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zach on 2/22/16.
 *
 * desc: Aids in searching for various targets
 */
public abstract class SearchingController implements ModelVisitor {
    private MapArea mapArea;
    private boolean isSearching = true;
    private Entity entity;
    private Target highestPriorityTarget;
    private Coord currentCoord;

    public SearchingController(MapArea mapArea, Entity entity) {
        this.mapArea = mapArea;
        this.entity = entity;
    }

    public MapArea getMapArea() {
        return this.mapArea;
    }

    public void setMapArea(MapArea mapArea) {
        this.mapArea = mapArea;
    }

    public boolean isSearching() {
        return isSearching;
    }

    public void setSearching(boolean searching) {
        isSearching = searching;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Target getHighestPriorityTarget() {
        return highestPriorityTarget;
    }

    public void setHighestPriorityTarget(Target highestPriorityTarget) {
        this.highestPriorityTarget = highestPriorityTarget;
    }

    public Coord getCurrentCoord() {
        return currentCoord;
    }

    public void setCurrentCoord(Coord currentCoord) {
        this.currentCoord = currentCoord;
    }

    protected Tile getTile(Coord coord){
        Tile tile;

        try {
            tile = mapArea.getTile(coord);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return tile;
    }



    /**
     * Get target result of search
     * Depending on target:
     *  visitGold
     *  visitAvatar
     *  etc.
     */
    public void search(int searchRadius) {
        this.highestPriorityTarget = null;//set it null at the beginning of each search so it doesnt keep seeing old searches
        int currRing = 0;

        Coord currCoord = entity.getLocation();

        //ArrayList<Tile> tiles;
        Iterator<Coord> iter;
        while (currRing <= searchRadius) {
            //tiles = this.getTileRing(currCoord, currRing);
            iter = HexMath.ring(currCoord, currRing);
            //System.out.println("getting ring " + currRing);

            while(iter.hasNext()){
                Coord current = iter.next();
                this.setCurrentCoord(current);
                //Tile currTile = null;
                try {
                    Tile currTile = this.getTile(current);
                    currTile.accept(this);
                }catch(Exception e){
                    //whoops tried to lok outside the map probably
                }

            }
            ++currRing;
        }
        doneSearching();
    }

    protected abstract void doneSearching();

    protected Target getMaxTarget(Target target1, Target target2){
        if (target1 == null && target2 == null) {
            return null;
        }

        if (target1 == null) {
            return target2;
        }

        if (target2 == null) {
            return target1;
        }

        if (target1.getPriority() > target2.getPriority()) {
            return target1;
        } else {
            return target2;
        }

    }

    public void startSearching() {
        //Target target = this.search();

//        if (highestPriorityTarget != null && (target.getPriority() > highestPriorityTarget.getPriority()))
//            highestPriorityTarget = target;
//        else if (highestPriorityTarget == null)
//            highestPriorityTarget = target;


    }

    public void stopSearching() {

    }

    //implement visitors in subclasses
}
