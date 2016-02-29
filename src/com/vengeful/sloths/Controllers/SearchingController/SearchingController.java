package com.vengeful.sloths.Controllers.SearchingController;

import com.vengeful.sloths.Controllers.Target.Target;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;
import java.util.ArrayList;

/**
 * Created by zach on 2/22/16.
 *
 * desc: Aids in searching for various targets
 */
public abstract class SearchingController implements ModelVisitor {
    private Map map;
    private boolean isSearching = true;
    private Entity entity;
    private Target highestPriorityTarget;

    public SearchingController(Map map, Entity entity) {
        this.map = map;
        this.entity = entity;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
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

    protected Tile getTile(Coord coord){
        Tile tile;

        try {
            tile = map.getTile(coord);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return tile;
    }

    protected ArrayList<Tile> getTileRing(Coord centerCord, int ringNumber) {
        ArrayList<Tile> ringTiles = new ArrayList<>();

        if (ringNumber == 0) {
            ringTiles.add(this.getTile(centerCord));
            return ringTiles;
        }

        int r = centerCord.getR(), s = centerCord.getS() - ringNumber;

        // Get top right and bottom left slabs
        for (int n = 0; n < ringNumber; n++) {
            ringTiles.add(this.getTile(new Coord(r + n, s)));
            ringTiles.add(this.getTile(new Coord((r + n) * (-1), s * (-1))));
        }

        r = centerCord.getR()+ringNumber;
        s = centerCord.getS();

        // Get right and left slabs
        for (int n = 0; n < ringNumber; n++) {
            int tmpS = s - ringNumber + n;
            ringTiles.add(this.getTile(new Coord(r, tmpS)));
            ringTiles.add(this.getTile(new Coord(r * (-1), tmpS * (-1))));
        }

        r = centerCord.getR() + ringNumber;
        s = centerCord.getS();

        // Get bottom right and top left slabs
        for (int n = 0; n < ringNumber; n++) {
            int tmpR = r + ringNumber - n;
            ringTiles.add(this.getTile(new Coord(tmpR, (s + n))));
            ringTiles.add(this.getTile(new Coord(tmpR * (-1), (s + n) * (-1))));
        }

        return ringTiles;
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

        ArrayList<Tile> tiles;

        while (currRing <= searchRadius) {
            tiles = this.getTileRing(currCoord, currRing);
            for (Tile tile : tiles) {
                tile.accept(this);
            }
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
