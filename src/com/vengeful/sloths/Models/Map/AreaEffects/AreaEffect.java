package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Models.Observers.AreaEffectObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;

/**
 * Created by John on 1/30/2016.
 */
public abstract class AreaEffect implements ViewObservable{

    //protected String name;
    EffectCommandFactory commandFactory;



    //Flag for whether to destory the AE after use
    protected boolean destory;
    protected AreaEffectObserver observer;

    public AreaEffect(EffectCommandFactory commandFactory){
        this.destory = false;
        this.commandFactory = commandFactory;
    }

    public abstract EffectCommand createEffectCommand(Entity affectedEntity);


    public boolean destroyFlag()
    {
        return this.destory;
    }

    public void destroy() {
        this.observer.alertDestroyed();
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observer = (AreaEffectObserver) modelObserver;
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observer = null;
    }

//    public abstract void saveMe(SaveManager sm, int ws);

}
