package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by Alex on 3/9/2016.
 */
public class ProxyInteractiveItemObserver extends ProxyObserver implements InteractiveItemObserver {

    private InteractiveItemObserver target;

    public ProxyInteractiveItemObserver(InteractiveItemObserver interactiveItemObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = interactiveItemObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }

    @Override
    public void alertActivate() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertActivate());
        }
    }

    @Override
    public void alertDeactivate() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertDeactivate());
        }
    }
}
