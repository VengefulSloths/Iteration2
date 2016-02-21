package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.SaveLoad.SaveManager;

/**
 * Created by qianwen on 1/30/16.
 */
public class Hat extends EquippableItems {

    //TODO: this needs to be uncommented so we can create Hat of different stats
    //public Hat(String hatName, BaseStats b){
    //    super(hatName, b);
    //}
     public Hat(){

     }

    public Hat(String hatName){
        super(hatName);
        //this.itemStats.setStats(0, 0, 0, 10, 0); //Once equipped, increase avatar stats by these factors. Currently causes error so used line below.
        //this.itemStats = new BaseStats(0,0,0,10,0);
        this.itemStats.setStats(0,5,0,10,0);
    }
//    public void saveMe(SaveManager sv, int ws){
//        sv.writeClassLine(ws, "Hat");
//        super.saveMe(sv, ws);
//        sv.writeCloseBracket(ws);
//    }
//
//    @Override
//    public void saveMeFromTakeable(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "Hat");
//        super.saveMeFromTakeable(sm, ws);
//    }

}
