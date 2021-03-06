package com.vengeful.sloths.Models.DialogueTrade;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityEntityInteractionCommands.EntityInitiateTradeCommand;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 3/12/2016.
 */
public class TradeDialogContainer extends DialogContainer {

    public TradeDialogContainer(String name) {
        super(name);
    }
    public TradeDialogContainer(Entity speaker){
        super(speaker);
    }

    @Override
    protected void terminalAction() {
        new EntityInitiateTradeCommand(getSpeaker()).execute();
    }

    public void accept(ModelVisitor mv){
        mv.visitTradeDialogueContainer(this);
    }
}
