package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by alexs on 2/29/2016.
 */
public class ProxyDestoyableObserver extends ProxyObserver implements DestroyableObserver{
    private ViewObservable subject;
    private DestroyableObserver target;

    public ProxyDestoyableObserver(DestroyableObserver target, ViewObservable subject) {
        this.subject = subject;
        subject.registerObserver(this);
        this.target = target;
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
