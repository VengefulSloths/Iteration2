package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 3/12/16.
 */
public class AbilityItem extends InventoryItem {
    private Ability ability;

    public AbilityItem(Ability ability){
        super();
        this.ability = ability;
        this.setItemName(this.ability.getItemName());
    }

    @Override
    public void accept(ModelVisitor mv) {
        mv.visitAbilityItem(this);
    }

    @Override
    public void interact() {
        System.out.println("Learning ability!");
        Avatar.getInstance().getAbilityManager().addAbility(this.ability);

    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}
