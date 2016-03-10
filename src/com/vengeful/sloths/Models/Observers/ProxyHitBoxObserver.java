package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by luluding on 3/9/16.
 */
public class ProxyHitBoxObserver extends ProxyObserver implements HitBoxObserver {

    private ViewObservable subject;
    private HitBoxObserver target;

    public ProxyHitBoxObserver(HitBoxObserver target, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = target;
    }


    @Override
    public void alertMove(int r, int s, long duration) {
        if(!deleteFlag){
            ViewTime.getInstance().registerAlert(0, () -> target.alertMove(r, s, duration));
        }
    }

    @Override
    public void alertDestroyed() {
        ViewTime.getInstance().registerAlert(0, () -> target.alertDestroyed());
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }
}
