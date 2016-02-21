package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by luluding on 2/6/16.
 */
public class AECreationCommand extends EffectCommand{
    AreaEffect ae;
    Coord coord;
    Map map;

    public AECreationCommand(AreaEffect ae, Coord coord, Map map){
        this.ae = ae;
        this.coord = coord;
        this.map = map;
    }


    @Override
    public void execute() {
        Tile tile = this.map.getTile(this.coord);
        tile.addAreaEffect(this.ae); //Will this break the iterator?

        //TODO: to add AreaEffect to the view, need to alert view
    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "AECreationCommand");
//        super.saveMe(sm, ws);
//    }
}
