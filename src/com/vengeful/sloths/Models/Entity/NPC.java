package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.AreaView.Observers.MovementObserver;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class NPC extends Entity{

    //pass in stats
    public NPC(String name, BuffManager buffManager, Stats stats){
        super(name, buffManager, stats);
    }

    public void talk(){
        //create talk command
    }

    @Override
    public void pickup(TakeableItem item) {
        //create pick up command
    }


    public Coord move(Direction dir) {

        if(!isMoving) {
            Coord dst = super.move(dir);

            //create NPC movement commmand

            //this.commandFactory.createMovementCommand(this.getLocation(), dst, dir, this, this.getStats().getMovement());

            for (MovementObserver observer: this.getObservers()) {
                //TODO: dont hardcode the movement time
                observer.alertMove(dst.getR(), dst.getS(), 500);
            }

            return dst;


        }else{
            ////System.out.Println("<<<<<<<<<<<<<<<<<<movement rejected>>>>>>>>>>>>>>>>");
            return this.getLocation();
        }
    }



}
