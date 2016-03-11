package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.Quest;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.InteractiveItemObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;

import java.util.ArrayList;

/**
 * Created by John on 1/30/2016.
 */
public class InteractiveItem extends MapItem implements ViewObservable {

    private ArrayList<InteractiveItemObserver> observers = new ArrayList<>();


    public Quest quest;


    public InteractiveItem(Quest quest, Coord location){
        this.quest = quest;
        this.setLocation(location);
    }
    public InteractiveItem(){}

    public void setCommand(Quest quest){
        this.quest = quest;
    }




    public void interact(Entity entity) {
        this.quest.attempt();
        //do nothing for now
        for (InteractiveItemObserver observer: observers) {
            observer.alertActivate();
        }
    }


    public void unteract(Entity entity) {
        for (InteractiveItemObserver observer: observers) {
            observer.alertDeactivate();
        }
    }

    @Override
    public void accept(ModelVisitor v) {
        v.visitInteractiveItem(this);
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        observers.add((InteractiveItemObserver) modelObserver);
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        observers.remove(modelObserver);
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
