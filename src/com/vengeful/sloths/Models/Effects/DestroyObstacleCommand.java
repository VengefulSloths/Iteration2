package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Tile;

/**
 * Created by luluding on 2/6/16.
 */
public class DestroyObstacleCommand extends EffectCommand{
    MapItem obstacle;
    Tile tile;

    public DestroyObstacleCommand(MapItem obstacle, Tile tile){
        this.obstacle = obstacle;
        this.tile = tile;
    }

    @Override
    public void execute() {

        /*
        boolean containsObstacle = false;

        Iterator<MapItem> iter = this.tile.getMapItemIterator();
        while(iter.hasNext()){
            MapItem mi = iter.next();
            if(mi.equals(this.obstacle))
                containsObstacle = true;

        }

        if(containsObstacle)
            this.tile.removeMapItem(this.obstacle);
*/

        if(!this.obstacle.destroyFlag());
            //this.tile.removeMapItem(this.obstacle);


        //flag destory?
        //or tile.remove? -> iterator issue?

    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "DestroyObstacleCommand");
//        obstacle.saveMe(sm, ws);
//        super.saveMe(sm, ws);
//    }
}
