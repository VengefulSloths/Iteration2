package com.vengeful.sloths.AreaView.Observers;

import com.vengeful.sloths.Utility.Direction;

/**
 * Created by Alex on 2/21/2016.
 */
public interface EntityObserver {
    void alertMove(int r, int s, long duration);
    void alertDirectionChange(Direction d);
}
