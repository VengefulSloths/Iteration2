package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 2/1/16.
 */
public class AvatarPickUpCommand extends PickUpCommand {

    Tile tile;


    public AvatarPickUpCommand(Map map, Coord dropLoc, Entity entity){
        super(map, dropLoc, entity);
        tile = map.getTile(dropLoc);
        ////System.out.Println("DROP LOC:" + dropLoc.getX() + " " + dropLoc.getY());
    }




    @Override
    public void execute() {

        Iterator<MapItem> iter = this.tile.getMapItemIterator();

        ArrayList<MapItem> toBeRemoved = new ArrayList<MapItem>();


        while(iter.hasNext()){
            MapItem item = iter.next();
            if(item instanceof TakeableItem){
                //TODO: need to check Inventory full

                InventoryItem toBePickedUp = ((TakeableItem) item).getInvItemRep();
                entity.getInventory().addItem(toBePickedUp);
                //tile.removeMapItem(item);
                toBeRemoved.add(item);
            }
        }

        //Remove at the end
        for(int i = 0; i < toBeRemoved.size(); i++){
            ////System.out.Println("I am removing: " + toBeRemoved.get(i).getItemName());
            tile.removeMapItem(toBeRemoved.get(i));
        }


        ////System.out.Println("Picking UP! ");
    }




}
