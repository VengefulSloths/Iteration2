package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.Map.MapArea;

/**
 * Created by alexs on 3/2/2016.
 */
public interface MapObserver extends ModelObserver{
    void alertMapAreaChange(MapArea mapArea);
}
