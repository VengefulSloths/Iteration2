package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.*;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by luluding on 2/6/16.
 */
public class ActionInteractiveItem extends InteractiveItem{

    Quest quest;

    public ActionInteractiveItem(EffectCommand command, Quest quest, Coord location){
        super(command, location);
        this.quest = quest;
    }

    @Override
    public void interact(Entity entity) {

        //TODO: create an observer just for this guy
        //System.out.Println("IS QUEST FINISHED: " + quest.finished());

        if(quest.finished()){
            this.command.execute();
        }


    }

    // @TODO: Make visitor method for this
    @Override
    public void accept(ModelVisitor vistior) {
        vistior.visitMapItem(this);
    }


    public void setQuest(Quest d) {
        this.quest = d;
    }

}
