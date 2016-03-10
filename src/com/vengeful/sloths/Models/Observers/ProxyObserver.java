package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by alexs on 1/31/2016.
 */
public abstract class ProxyObserver implements ModelObserver {
    protected boolean deleteFlag;

    protected ViewObservable subject;
    public abstract ModelObserver getModelObserver();
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public boolean getDeleteFlag() {
        return this.deleteFlag;
    }
    public void deregister() {
        subject.deregisterObserver(this);
    }
}
