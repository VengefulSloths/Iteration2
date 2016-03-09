package com.vengeful.sloths.Models.Observers;

/**
 * Created by Alex on 3/9/2016.
 */
public class ProxyInteractiveItemObserver extends ProxyObserver implements InteractiveItemObserver {
    @Override
    public ModelObserver getModelObserver() {
        return null;
    }

    @Override
    public void alertActivate() {

    }

    @Override
    public void alertDeactivate() {

    }
}
