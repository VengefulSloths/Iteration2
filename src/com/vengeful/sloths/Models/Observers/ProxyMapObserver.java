package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by alexs on 3/2/2016.
 */
public class ProxyMapObserver extends ProxyObserver implements MapObserver {
    private MapObserver target;

    public ProxyMapObserver(MapObserver entityObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = entityObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }

    @Override
    public void alertMapAreaChange(MapArea m) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertMapAreaChange(m));
        }
    }
}
