package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.DialogueTrade.DialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TerminalDialogContainer;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.DropAllGoldCommand;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityDieCommand;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.PiggyTotem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class Piggy extends Pet {

    TakeableItem piggyTotem;

    public Piggy(String name, Stats stats){
        super(name, stats);
        DialogContainer piggyDialog = new TerminalDialogContainer(name);
        piggyDialog.appendDialog("oink oink!!");
        this.setDialogContainer(piggyDialog);
    }

    public Piggy() {}

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPiggy(this);
    }

    public TakeableItem getPiggyTotem() {
        return piggyTotem;
    }

    public void setPiggyTotem(TakeableItem piggyTotem) {
        this.piggyTotem = piggyTotem;
    }

    protected void customDeath() {
        EntityMapInteractionFactory.getInstance().createPiggyDropTotemCommand(this.getPiggyTotem(), this.getLocation(), this).execute();
        Avatar.getInstance().removePet();
    }
}
