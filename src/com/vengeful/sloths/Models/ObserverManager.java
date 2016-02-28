package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.View.Observers.ModelObserver;
import com.vengeful.sloths.View.Observers.ProxyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexs on 1/31/2016.
 */
public class ObserverManager implements Alertable {
    private static ObserverManager instance;
    private HashMap<ModelObserver, ProxyObserver> observers;
    private ObserverManager() {
        observers = new HashMap<ModelObserver, ProxyObserver>();
    }
    static public ObserverManager instance() {
        if (instance == null) {
            instance = new ObserverManager();
            instance.mAlert();
        }
        return instance;
    }
    public void cleanUp() {
        ArrayList<ModelObserver> toDelete = new ArrayList<>();
        for (Map.Entry<ModelObserver, ProxyObserver> entry:
             observers.entrySet()) {
            ProxyObserver p = entry.getValue();
            ModelObserver m = entry.getKey();
            if (p.getDeleteFlag()) {
                //System.out.Println("removing a observer");
                p.deregister();
                toDelete.add(m);
                }
        }
        for (ModelObserver m:
             toDelete) {
            observers.remove(m);
        }
    }
    public void addProxyObserver(ProxyObserver proxyObserver) {
        observers.put(proxyObserver.getModelObserver(), proxyObserver);
    }
    public boolean flagForDelete(ModelObserver modelObserver) {
        if (observers.containsKey(modelObserver)) {
            observers.get(modelObserver).setDeleteFlag(true);
            return true;
        } else return false;
    }
    public void mAlert() {

        cleanUp();
    }
}
