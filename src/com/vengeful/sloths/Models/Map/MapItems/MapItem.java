package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by John on 1/30/2016.
 * This class needs to be updated to follow black box inheritance
 */
public abstract class MapItem implements  ModelVisitable {

    protected boolean destroy = false;

    private String itemName;
    protected String graphicFolder;

    private Coord location;


    public abstract void interact(Entity entity);

    public boolean canMove(){
        return true;
    }

    public boolean destroyFlag()
    {
        return destroy;
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
        //Do alerting in subclasses of map item only

        //observer.alertDestroyed();
        this.destroy = true;
        //System.out.Println("This is map object, my observer is: " + observer);
    }

    public void setLocation(Coord loc){
        this.location = loc;
    }

    public Coord getLocation(){
        return this.location;
    }



//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeVariableLine(ws, "itemName", itemName, false);
//        sm.writeVariableLine(ws,"graphicFolder", graphicFolder, false);
//    }
    public abstract void accept(ModelVisitor vistior);


}
