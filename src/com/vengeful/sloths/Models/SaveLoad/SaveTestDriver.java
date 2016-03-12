package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by Ian on 2/25/2016.
 */
public class SaveTestDriver {
    public static void main(String[] args){
        //Map m = new Map();
        Map m = Map.getInstance();
        MapArea ma1 = new MapArea(2,2);
        MapArea ma2 = new MapArea(2,2);
        ma1.setName("ma1");
        Tile[][] tiles1 = new Tile[2][2];
        Tile[][] tiles2 = new Tile[2][2];
        for(int i = 0; i != 2; ++i){
            for(int j = 0; j != 2; ++j){
                tiles1[i][j] = new Tile();
                tiles2[i][j] = new Tile();
            }
        }
        m.setMapAreas( new MapArea[] {ma1, ma2});
        ma1.setTiles(tiles1);
        ma2.setTiles(tiles2);

        Avatar a = Avatar.getInstance();
        a.setFacingDirection(Direction.N);
        AbilityManager abm = new AbilityManager(a);
        a.avatarInit("Sneak", abm, new BuffManager(a), new SkillManager(5));
        tiles1[1][1].addEntity(a);
        SaveManager sm = new SaveManager();
        sm.save("testSave");

//        Loader l = null;
//        try{
//            l = new Loader("testSave.xml");
//        }
//        catch(Exception e){
//            System.out.println("error loading save data");
//        }
//        ma1.setName("ma1");
//        ma2.setName("ma2");
//        l.loadAreas(new MapArea[] {ma1,ma2});
//        Entity[] e = ma1.getTile(new Coord(1,1)).getEntities();
    }
}
