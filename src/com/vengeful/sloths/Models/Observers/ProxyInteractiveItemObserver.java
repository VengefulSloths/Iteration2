package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by Alex on 3/9/2016.
 */
public class ProxyInteractiveItemObserver extends ProxyObserver implements InteractiveItemObserver {

    private InteractiveItemObserver target;

    public ProxyInteractiveItemObserver(InteractiveItemObserver interactiveItemObserver, ViewObservable subject) {
        System.out.println("    .A");
        this.subject = subject;
        System.out.println("    .B");
        this.subject.registerObserver(this);
        System.out.println("    .C");
        this.target = interactiveItemObserver;
        System.out.println("    .D");
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
