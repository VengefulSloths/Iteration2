package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by John on 2/3/2016.
 */
public class ProxyStatsObserver extends ProxyObserver implements StatsObserver{
    @Override
    public void alertStatChanged(Stats stat) {
        ViewTime.getInstance().registerAlert(0, () -> target.alertStatChanged(stat));
    }

    private StatsObserver target;

    public ProxyStatsObserver(StatsObserver entityObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = entityObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }
}
