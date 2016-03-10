package com.vengeful.sloths.Models.Observers;

import com.sun.javafx.sg.prism.NGShape;

/**
 * Created by luluding on 3/9/16.
 */
public interface HitBoxObserver extends ModelObserver{
    void alertMove(int r, int s, long duration);
    void alertDestroyed();
}
