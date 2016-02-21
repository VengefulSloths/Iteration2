package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;

import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.View.Observers.MapItemObserver;
import com.vengeful.sloths.View.Observers.ModelObserver;
import org.w3c.dom.Element;

/**
 * Created by John on 1/30/2016.
 * This class needs to be updated to follow black box inheritance
 */
public abstract class MapItem implements ViewObservable {

    protected MapItemObserver observer;
    protected boolean destroy = false;

    private String itemName;
    protected String graphicFolder;

    public abstract void interact(Entity entity);

    public boolean canMove(){
        return true;
    }

    public boolean destroyFlag()
    {
        return destroy;
    }

    /**
     * this visit is only for saveVisitor
     * this visit might be changed to abstract and each of the subclasses will implement their own
     */
    public void visit(SaveVisitor sv, Element e, Coord c){
        sv.visitMapItem(this, e, c);
    }

    //public void setDestroyFlag(boolean flag){
    //    this.destroy = flag;
    //}

    public String getItemName(){
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setGraphicFolder(String graphicFolder) {
        this.graphicFolder = graphicFolder;
    }

    public void destroy() {
        observer.alertDestroyed();
        this.destroy = true;
        //System.out.Println("This is map object, my observer is: " + observer);
    }

    public MapItemObserver getObserver(){
        return this.observer;
    }


    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observer = (MapItemObserver) modelObserver;
        ////System.out.Println("MAP ITEM OBSERVER REGISTERED: " + ((MapItemObserver)modelObserver).getClass().getSimpleName());
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observer = null;

    }

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeVariableLine(ws, "itemName", itemName, false);
//        sm.writeVariableLine(ws,"graphicFolder", graphicFolder, false);
//    }


}
