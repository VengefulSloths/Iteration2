package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 2/21/2016.
 */
public class Tile{

    private Entity entity = null;
    private boolean canBeMovedOn;
    private ArrayList<MapItem> mapItems;
    private ArrayList<AreaEffect> areaEffect;
    private Terrain terrain;
    private boolean cleaningup = false;



    public Tile(){
        canBeMovedOn = true;
        mapItems = new ArrayList<MapItem>();
        areaEffect = new ArrayList<AreaEffect>();
        terrain = new Grass();
        this.cleaningup = false;

    }

    public Tile(Terrain terrain){
        canBeMovedOn = true;
        mapItems = new ArrayList<MapItem>();
        areaEffect = new ArrayList<AreaEffect>();
        this.terrain = terrain;
        this.cleaningup = false;

    }

    public void execute(){

    }

    public boolean canMove(){
        if(mapItems.size() <= 0) {
            return terrain.canMove();
        }else{
            boolean canMove = true;

            for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
                MapItem item = iter.next();
                canMove = canMove && item.canMove();
            }
            return (canMove && terrain.canMove());
        }
    }

    public void interact(Entity entity)
    {
        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            MapItem item = iter.next();
            item.interact(entity);
        }


        //Create AEs
        Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
        while(aeIter.hasNext()){
            AreaEffect ae = aeIter.next();
            EffectCommand effect = ae.createEffectCommand(this.entity);
            effect.execute();
            //System.out.Println("AE: " + ae);
        }


        cleanUp();
    }

    public void addEntity(Entity entity){
        //may need to check for an entity already being on the tile
        this.entity = entity;

        //For some reason check hasmapItem, and check hasAE logic can't be here
        //Have to put the checking logic in movement command or the Pickup/drop, AE commands would not work
    }


    public boolean hasEntity() { return this.entity != null; }

    public Entity getEntity() {
        return this.entity;
    }

    public Entity removeEntity(){
        Entity entity = this.entity;
        this.entity = null;

        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            MapItem item = iter.next();
            //item.interact(entity);
            if(item instanceof InteractiveItem)
                item.getObserver().alertDeactivated();
        }


        return entity;
    }

    public Terrain getTerrain() { return this.terrain; }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    public Iterator<MapItem> getMapItemIterator() {
        return mapItems.iterator();
    }

    public Iterator<AreaEffect> getAreaEffectIterator() {
        return areaEffect.iterator();
    }

    private void cleanUp(){
        //cleaningup = true;
        ArrayList<MapItem> toDestroy = new ArrayList<MapItem>();
        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            MapItem item = iter.next();
            if(item.destroyFlag()){
                toDestroy.add(item);
            }
        }
        for (MapItem td : toDestroy) {
            try {
                td.destroy();
            }catch (Exception e){}
            mapItems.remove(td);

        }

        //Remove destroyed AE
        ArrayList<AreaEffect> toDestroyAE = new ArrayList<AreaEffect>();
        Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
        while(aeIter.hasNext()){
            AreaEffect ae = aeIter.next();
            if(ae.destroyFlag())
                toDestroyAE.add(ae);
        }

        for(AreaEffect ae : toDestroyAE){
            ae.destroy();
            areaEffect.remove(ae);
        }
    }

    public void addMapItem(MapItem mapItem) {
        mapItems.add(mapItem);
    }

    public void removeMapItem(MapItem item){
        mapItems.remove(item);
        item.destroy(); //tell observer
    }

    public MapItem getMapItem(int index){
        if(index < 0 || index >= mapItems.size())
            return null;

        return mapItems.get(index);
    }

    public void addAreaEffect(AreaEffect ae){
        areaEffect.add(ae);
    }


    public InteractiveItem getInteractiveItem(){

        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            MapItem item = iter.next();
            if(item instanceof InteractiveItem)
                return (InteractiveItem) item;
        }
        System.out.println("shit got fucked up you aint supposed to call ths here!!!");
        return null;
    }

}
