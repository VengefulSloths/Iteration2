package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;

/**
 * Created by luluding on 2/5/16.
 */
public class ProxyAreaEffectObserver extends ProxyObserver implements AreaEffectObserver{


    private AreaEffectObserver target;
    public ProxyAreaEffectObserver(AreaEffectObserver target, AreaEffect subject){
        this.target = target;
        this.subject = subject;
        this.subject.registerObserver(this);
    }


    @Override
    public void alertDestroyed() {
        target.alertDestroyed();
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }
}
