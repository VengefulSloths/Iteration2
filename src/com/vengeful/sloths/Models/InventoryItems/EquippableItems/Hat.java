package com.vengeful.sloths.Models.InventoryItems.EquippableItems;


import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import sun.security.x509.AVA;

/**
 * Created by qianwen on 1/30/16.
 */

public class Hat extends EquippableItems implements ModelVisitable {

    public Hat(String name, StatsAddable stats){
        super(name, stats);
    }
    public Hat () {}

    @Override
    public void addToEquipped(Equipped equipped) {
        equipped.addHat(this);
    }

    @Override
    public void removeFromEquipped(Equipped equipped) {
        equipped.removeHat(this);
        System.out.println("TRYING TO REMOVE A HAT FROM Equipped");

    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitHat(this);
    }

    @Override
    public void interact() {
        this.addToEquipped(Avatar.getInstance().getEquipped());
    }
}
