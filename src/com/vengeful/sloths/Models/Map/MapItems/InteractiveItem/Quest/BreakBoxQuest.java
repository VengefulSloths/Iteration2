package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest;

import com.vengeful.sloths.Models.Map.MapItems.MapItem;
//import com.vengeful.sloths.Models.Map.MapItems.OneShotTest;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 2/6/16.
 */
public class BreakBoxQuest extends Quest {
    ArrayList<Tile> tiles;


    public BreakBoxQuest(Tile... tiles){
        this.tiles = new ArrayList<>();
        for(Tile t : tiles){
            this.tiles.add(t);
        }
    }


    @Override
    public boolean attempt() {

        //Need better way to do this
        for(Tile t : this.tiles){
            Iterator<MapItem> it = t.getMapItemIterator();
            while(it.hasNext()){
//                if(it.next() instanceof OneShotTest) //once entity step on a tile, all one-shot on tile gets activated
                    return false;
            }
        }

        return true;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBreakBoxQuest(this);
    }

    public Tile[] getTiles(){
        Tile[] tiles = new Tile[this.tiles.size()];
        int i = 0;
        for(Tile t : tiles){
            tiles[i] = t;
            ++i;
        }
        return tiles;
    }
}
