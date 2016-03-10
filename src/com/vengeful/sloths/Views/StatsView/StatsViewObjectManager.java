package com.vengeful.sloths.Views.StatsView;

import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class StatsViewObjectManager {

    private ArrayList<StatsViewObject> statsList;

    private Stats stats;

    //InventoryItemViewObject GodSwordItemViewObject = new InventoryItemViewObject(new Sword("GodSword"));
    //InventoryItemViewObject PartyHatItemViewObject = new InventoryItemViewObject(new Hat("Blue Partyhat"));

    public StatsViewObjectManager() {
        statsList = new ArrayList<StatsViewObject>();
    }

    public StatsViewObjectManager(Stats stats) {
        //statsList = new ArrayList<StatsViewObject>();
        this.stats = stats;
        StatsViewObjectFactory factory = new StatsViewObjectFactory();
        statsList = factory.generateStatsViewObjects(stats);
    }

    public void setStats(Stats stats){
        this.stats = stats;
        StatsViewObjectFactory factory = new StatsViewObjectFactory();
        statsList = factory.generateStatsViewObjects(stats);
    }

    public void addStatsViewObject(StatsViewObject stat) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        statsList.add(stat);
    }

    private void generateStatViewObjects(){
        statsList.clear();
        //generate and add to statsview objects
    }


    public int getItemListSize() {
        return statsList.size();
    }

    public Iterator<StatsViewObject> iterator() {
        return statsList.iterator();
    }

}

