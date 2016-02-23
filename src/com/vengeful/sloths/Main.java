package com.vengeful.sloths;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //highly dank test code
        ModelEngine engine = new ModelEngine();
        StrengthAddable cat = new StrengthAddable(5);
        System.out.println("this is the str: " + cat.getStrength());
        Stats stats = new Stats();
        Avatar blob = Avatar.getInstance();
        blob.avatarInit("Summoner", new AbilityManager(), stats);

        engine.start();

    }
}
