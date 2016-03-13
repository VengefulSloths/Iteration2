package com.vengeful.sloths.Models.InventoryItems.ConsumableItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/22/16.
 */
public class Potion extends ConsumableItems implements ModelVisitable{

    public Potion(String name, StatsAddable statsAddable){
        super(name, statsAddable);

    }
    public Potion(){
    }

    @Override
    public void use(Stats stats) {
        stats.add(this.getItemStats());
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPotion(this);
    }

    @Override
    public void interact() {
        Avatar.getInstance().consumeItem(this);
    }
}
