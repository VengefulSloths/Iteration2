package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.PermanantBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;

/**
 * Created by Alex on 3/10/2016.
 */
public class Mount extends EquippableItems {
    private String name;
    private Buff mountBuff;

    public Mount(String name, int moveSpeed) {
        this.mountBuff = new PermanantBuff(new MovementAddable(moveSpeed));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ability getMountAbility(Entity entity) {
        return AbilityFactory.getInstance().createSelfBuffAbility(entity, mountBuff, 30, 32);
    }

    public Ability getDemountAbility(Entity entity) {
        return AbilityFactory.getInstance().createRemoveBuffAbility(mountBuff, entity.getBuffManager(), entity);
    }

    @Override
    public void addToEquipped(Equipped equipped) {
        equipped.addMount(this);
    }

    @Override
    public void removeFromEquipped(Equipped equipped) {

    }

    @Override
    public void accept(ModelVisitor mv) {

    }
}
