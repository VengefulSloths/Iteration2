package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 2/21/2016.
 */
public class Tile implements ModelVisitable{
    /**
     * Private variables, everything that can be held on a tile
     * Note that the arrayList nonCollideableEntites will need to have well coded accessors so there aren't null slots between entities listed
     * add and remove MapItem should probably be updated in this fashion
     * right now it(nonCollideableEntities) just has a standard getter/setter nothing that adds or removes a specific entity
     */

    private Entity entity = null;
    private ArrayList<Entity> nonCollideableEntities;
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

    public boolean canMove(){

        if(this.entity != null){
            return false; //if there is a collideable entity, can't move
        }else if(mapItems.size() <= 0) {
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

            EffectCommand effect = ae.createEffectCommand(entity);
            effect.execute();
        }

        cleanUp();
    }

    public void addEntity(Entity entity){
        //may need to check for an entity already being on the tile
        this.entity = entity;

        //For some reason check hasmapItem, and check hasAE logic can't be here
        //Have to put the checking logic in movement command or the Pickup/drop, AE commands would not work
    }


    public boolean hasEntity() {
        return this.entity != null;
    }

    public Entity getEntity(){
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

    /**
     *Below are getter/setters for the nonCollideableEntities
     * I've edited teh getter so it returns an array rather than an array list
     */
    public Entity[] getNonCollideableEntities() {
        Entity[] nonColE = new Entity[nonCollideableEntities.size()];
        int i = 0;
        for(Entity e : nonCollideableEntities){
            nonColE[i] = e;
            ++i;
        }
        return nonColE;
    }

    public void setNonCollideableEntities(ArrayList<Entity> nonCollideableEntities) {
        this.nonCollideableEntities = nonCollideableEntities;
    }

    public void addNonCollideableEntity(Entity entity){
        this.nonCollideableEntities.add(entity);
    }

    public void removeNonCollideableEntity(Entity entity){
        this.nonCollideableEntities.remove(entity);
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

    public void setMapItems(ArrayList<MapItem> mapItems) {
        this.mapItems = mapItems;
    }

    public void accept(ModelVisitor visitor) {
        visitor.visitTile(this);
    }
}
