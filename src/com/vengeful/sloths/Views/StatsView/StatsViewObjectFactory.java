package com.vengeful.sloths.Views.StatsView;

import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by lenovo on 3/6/2016.
 */
public class StatsViewObjectFactory {

    public ArrayList<StatsViewObject> generateStatsViewObjects(Stats stats){
        ArrayList<StatsViewObject> tmpList = new ArrayList<>();
            StatsViewObject tmp;

            tmp = new StatsViewObject("Level", stats.getLevel());
            tmpList.add(tmp);

            tmp = new StatsViewObject("Max Health", stats.getMaxHealth());
            tmpList.add(tmp);

            tmp = new StatsViewObject("Max Mana", stats.getMaxMana());
            tmpList.add(tmp);
            //base stats

            //strength
            tmp = new StatsViewObject("Strength", stats.getStrength());
            tmpList.add(tmp);
            //agility
            tmp = new StatsViewObject("Agility", stats.getAgility());
            tmpList.add(tmp);
            //
            tmp = new StatsViewObject("Intellect", stats.getIntellect());
            tmpList.add(tmp);
            //

            tmp = new StatsViewObject("Hardiness", stats.getHardiness());
            tmpList.add(tmp);

            tmp = new StatsViewObject("Movement", stats.getMovement());
            tmpList.add(tmp);

            tmp = new StatsViewObject("Offensive Rating", stats.getOffensiveRating());
            tmpList.add(tmp);
            //defensive rating
            tmp = new StatsViewObject("Defensive Rating", stats.getDefensiveRating());
            tmpList.add(tmp);
            //Armor rating
            tmp = new StatsViewObject("Armor Rating", stats.getArmorRating());
            tmpList.add(tmp);
            //life

        return tmpList;
    }


}
