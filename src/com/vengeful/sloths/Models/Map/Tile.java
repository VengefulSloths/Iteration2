package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 2/21/2016.
 */
public class Tile implements ModelVisitable {
    /**
     * Private variables, everything that can be held on a tile
     * Note that the arrayList nonCollideableEntites will need to have well coded accessors so there aren't null slots between entities listed
     * add and remove MapItem should probably be updated in this fashion
     * right now it(nonCollideableEntities) just has a standard getter/setter nothing that adds or removes a specific entity
     */
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> nonCollideableEntities;
    private boolean canBeMovedOn;

    private ArrayList<MapItem> mapItems;

    private OneShotItem oneShotItem = null;
    private Obstacle obstacle = null;
    private InteractiveItem interactiveItem = null;


    private ArrayList<AreaEffect> areaEffect;
    private Terrain terrain;
    private boolean cleaningup = false;



    public Tile() {
        canBeMovedOn = true;
        mapItems = new ArrayList<MapItem>();
        areaEffect = new ArrayList<AreaEffect>();
        terrain = new Grass();
        this.cleaningup = false;
        this.nonCollideableEntities = new ArrayList<Entity>();
    }

    public Tile(Terrain terrain){
        canBeMovedOn = true;
        mapItems = new ArrayList<MapItem>();
        areaEffect = new ArrayList<AreaEffect>();
        this.terrain = terrain;
        this.cleaningup = false;
        this.nonCollideableEntities = new ArrayList<Entity>();

    }

    public void execute(){

    }

    public void interact(Entity entity)
    {
        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            System.out.println("interacting w/ a map item");
            iter.next().interact(entity);
        }



        //Create AEs
        Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
        while(aeIter.hasNext()){
            AreaEffect ae = aeIter.next();

            Iterator<Entity> entityIterator = this.getEntityIterator();
            EffectCommand effect;

            while (entityIterator.hasNext()) {
                effect = ae.createEffectCommand(entityIterator.next());
                effect.execute();
            }
            //System.out.Println("AE: " + ae);
        }


        cleanUp();

    }

    public void addEntity(Entity entity){
        //may need to check for an entity already being on the tile

        this.entities.add(entity);
        this.interact(entity);

        //For some reason check hasmapItem, and check hasAE logic can't be here
        //Have to put the checking logic in movement command or the Pickup/drop, AE commands would not work
    }


    public boolean hasEntity() { return this.entities.size() > 0; }

    public Iterator<Entity> getEntityIterator() {
        return this.entities.iterator();
    }

    public Entity removeEntity(Entity e){
        if (this.entities.contains(e)) {
            this.entities.remove(e);
        }

//        Entity entity = this.entity;
//        this.entity = null;

        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
            MapItem item = iter.next();
            //item.interact(entity);

            //TODO: this is really weird code
//            if(item instanceof InteractiveItem)
//                item.getObserver().alertDeactivated();
        }


        return e;
    }

    public Entity[] getEntities(){
        Entity[] entityArr = new Entity[entities.size()];
        int i = 0;
        for(Entity e : entities){
            entityArr[i] = e;
            ++i;
        }
        return entityArr;
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

    public void addTakeableItem(TakeableItem takeableItem) {
        mapItems.add(takeableItem);
    }

    public void addObstacle(Obstacle obstacle) {
        if (this.obstacle == null) {
            mapItems.add(obstacle);
            this.obstacle = obstacle;
        } else {
            throw new InvalidParameterException("you cannot add two obstacles");
        }
    }

    public void addInteractiveItem(InteractiveItem interactiveItem) {
        if (this.interactiveItem == null) {
            mapItems.add(interactiveItem);
            this.interactiveItem = interactiveItem;
        } else {
            throw new InvalidParameterException("you cannot add two obstacles");
        }
    }

    public void addOneShotItem(OneShotItem oneShotItem) {
        if (this.oneShotItem == null) {
            mapItems.add(oneShotItem);
            this.oneShotItem = oneShotItem;
        } else {
            throw new InvalidParameterException("you cannot add two obstacles");
        }
    }

//    public void addMapItem(MapItem mapItem) {
//        mapItems.add(mapItem);
//    }

    public void removeTakeableItem(TakeableItem takeableItem) {
        removeMapItem(takeableItem);
    }

    public void removeObstacle(Obstacle item) {
        removeMapItem(item);
        this.obstacle = null;
    }

    public void removeInteractiveItem(InteractiveItem item) {
        removeMapItem(item);
        this.interactiveItem = null;
    }

    public void removeOneShotItem(OneShotItem item) {
        removeMapItem(item);
        this.oneShotItem = null;
    }

    private void removeMapItem(MapItem item){
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

    /**
     *Below are getter/setters for the MapItems ( not individual mapItem accessors)
     * I've edited the getter so it returns an array rather than an array list
     */
    public MapItem[] getMapItems() {
//        List<MapItem> mapItemList= mapItems;
//        MapItem[] mapItemArray = (MapItem[]) mapItemList.toArray();
        MapItem[] mapItemArray = new MapItem[mapItems.size()];
        int i = 0;
        for(MapItem mi : mapItems){
            mapItemArray[i] = mi;
            ++i;
        }
        return mapItemArray;
    }

    public void accept(ModelVisitor visitor) {
        visitor.visitTile(this);
    }
}
