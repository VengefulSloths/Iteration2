package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Buff.ActionRemovableBuff;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.MountBuff;
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
    private int moveSpeed;

    public Mount(String name, int moveSpeed) {
        this.moveSpeed = moveSpeed;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ability getMountAbility(Entity entity) {
        return AbilityFactory.getInstance().createMountAbility(entity, 10, name, 30, 32);
    }


    @Override
    public void addToEquipped(Equipped equipped) {
        equipped.addMount(this);
    }

    @Override
    public void removeFromEquipped(Equipped equipped) {
        equipped.removeMount();
    }

    @Override
    public void accept(ModelVisitor mv) {
        mv.visitMount(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }



    @Override
    public void interact() {

    }
}
