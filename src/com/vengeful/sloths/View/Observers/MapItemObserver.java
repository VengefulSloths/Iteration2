package com.vengeful.sloths.View.Observers;

/**
 * Created by Alex on 2/1/2016.
 */
public interface MapItemObserver extends ModelObserver {
    void alertDestroyed();
    void alertActivated();
    void alertDeactivated();
}
