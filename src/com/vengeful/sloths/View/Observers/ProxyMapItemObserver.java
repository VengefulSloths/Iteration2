package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.Map.MapItems.MapItem;

/**
 * Created by Alex on 2/1/2016.
 */
public class ProxyMapItemObserver extends ProxyObserver
        implements MapItemObserver {

    private MapItemObserver target;
    public ProxyMapItemObserver(MapItemObserver target, MapItem subject) {
        ////System.out.Println("ProxyMapItemObserver constructor: ");
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = target;

        ////System.out.Println("PROXY REGISTERED OBSERVER: " + subject.getClass().getSimpleName() + " =-= " + target.toString());
    }
    @Override
    public ModelObserver getModelObserver() {
        return target;
    }

    @Override
    public void alertDestroyed() {
        target.alertDestroyed();
    }

    @Override
    public void alertActivated() {
        target.alertActivated();
    }

    @Override
    public void alertDeactivated() {
        target.alertDeactivated();
    }
}
