package com.vengeful.sloths.Models.DialogueTrade;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/12/2016.
 */
public abstract class DialogObservable {
    private ArrayList<DialogObserver> observers = new ArrayList<>();
    public void registerObserver(DialogObserver observer){
        this.observers.add(observer);
    }
    public void deregisterObserver(DialogObserver observer){
        this.observers.remove(observer);
    }

    public Iterator<DialogObserver> getObserversIterator(){
        return observers.iterator();
    }
    public ArrayList<DialogObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<DialogObserver> observers) {
        this.observers = observers;
    }
}
