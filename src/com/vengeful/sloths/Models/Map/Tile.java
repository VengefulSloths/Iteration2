package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;

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
    private Trap trap = null;
    private Gold gold = null;


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
        //this loop doesnt actually work, interact will remove items which breaks the traversal
        //can be fixed but is probably unnecessary
        MapItem[] itemsToInteract = new MapItem[mapItems.size()];
        for(int i = 0; i != mapItems.size(); ++i){
            itemsToInteract[i] = mapItems.get(i);
        }
        for(int i = 0; i != itemsToInteract.length; ++i){
            itemsToInteract[i].interact(entity);
        }
//        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
//            System.out.println("interacting w/ a map item");
//            iter.next().interact(entity);
//        }

        //Create AEs
        Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
        while(aeIter.hasNext()){
            AreaEffect ae = aeIter.next();

            ae.addEntity(entity);
            //System.out.Println("AE: " + ae);
        }
    }

    public void addEntity(Entity entity){
        //may need to check for an entity already being on the tile
        //the check below breaks the gameF
//        if(this.entities.size() > 0){
//        }
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
            if (interactiveItem != null) {
                interactiveItem.unteract(e);
            }
            Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
            while(aeIter.hasNext()){
                AreaEffect ae = aeIter.next();

                ae.removeEntity(e);
                //System.out.Println("AE: " + ae);
            }

            if(this.trap != null)
                this.trap.removeEntity(e);

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

//

    public void addTakeableItem(TakeableItem takeableItem) {
        mapItems.add(takeableItem);
    }

    public void addObstacle(Obstacle obstacle) {
        if (this.obstacle == null) {
            mapItems.add(obstacle);
            this.obstacle = obstacle;
        } else {
//            throw new InvalidParameterException("you cannot add two obstacles");
        }
    }

    public boolean hasObstacle() {
        return obstacle != null;
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
    public void addGold(Gold gold){
        if(this.gold == null){
            this.gold = gold;
            mapItems.add(gold);
        }
        else {
            this.gold.addGold(gold);
        }
    }

    public void addTrap(Trap trap){
        if(this.trap == null){
            mapItems.add(trap);
            this.trap = trap;
        }else{
            //throw new InvalidParameterException("you cannot add two traps");
        }
    }

//    public void addMapItem(MapItem mapItem) {
//        mapItems.add(mapItem);
//    }

    public void removeTakeableItem(TakeableItem takeableItem) {

        TimeModel.getInstance().registerAlertable(() -> removeMapItem(takeableItem), 0);
    }

    public void removeGold(Gold gold){
        removeMapItem(gold);
        this.gold = null;
    }

    public void removeObstacle() {
        removeMapItem(this.obstacle);
        this.obstacle.destroy();
        this.obstacle = null;
    }

    public void removeInteractiveItem() {
        removeMapItem(this.interactiveItem);
        this.interactiveItem = null;
    }

    public void removeOneShotItem() {
        removeMapItem(this.oneShotItem);
        this.oneShotItem = null;
    }

    public void removeTrap(){
        if(this.trap != null){
            if(this.trap.destroy()){
                removeMapItem(this.trap);
                this.trap = null;
            }
        }else{
            System.out.println("No trap to be removed");
        }
    }

    public void showTrap(){
        if(this.trap != null){
            this.trap.makeVisible();
        }
    }

    public void makeTrapUndetectable(){
        if(this.trap != null){
            this.trap.makeUndetectable();
        }
    }

    private void removeMapItem(MapItem item){
        mapItems.remove(item);
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

//    private void cleanUp(){
//        //cleaningup = true;
//        ArrayList<MapItem> toDestroy = new ArrayList<MapItem>();
//        for (Iterator<MapItem> iter = mapItems.iterator(); iter.hasNext();) {
//            MapItem item = iter.next();
//            if(item.destroyFlag()){
//                toDestroy.add(item);
//            }
//        }
//        for (MapItem td : toDestroy) {
//            try {
//                td.destroy();
//            }catch (Exception e){}
//            mapItems.remove(td);
//
//        }
//
//        //Remove destroyed AE
//        ArrayList<AreaEffect> toDestroyAE = new ArrayList<AreaEffect>();
//        Iterator<AreaEffect> aeIter = this.getAreaEffectIterator();
//        while(aeIter.hasNext()){
//            AreaEffect ae = aeIter.next();
//            if(ae.destroyFlag())
//                toDestroyAE.add(ae);
//        }
//
//        for(AreaEffect ae : toDestroyAE){
//            ae.destroy();
//            areaEffect.remove(ae);
//        }
//    }
}
