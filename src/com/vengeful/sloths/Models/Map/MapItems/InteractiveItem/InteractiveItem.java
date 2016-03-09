package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
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

    //used to identify the corresponding image file
    protected String name;
    protected EffectCommand command;

    public InteractiveItem(Coord location){
        this.setLocation(location);
    }


    public InteractiveItem(EffectCommand command, Coord location){
        this.command = command;
        this.setLocation(location);
    }

    public void setCommand(EffectCommand cmd){
        this.command = cmd;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void interact(Entity entity) {
        //do nothing for now

        for (InteractiveItemObserver observer: observers) {
            observer.alertActivate();
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



}
