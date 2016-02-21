package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by John on 2/3/2016.
 */
public interface StatsObserver extends ModelObserver {
    public void alertStatChanged(Stats stat);
}
