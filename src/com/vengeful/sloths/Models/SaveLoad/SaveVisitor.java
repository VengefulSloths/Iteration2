package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.BuffOverTime;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.*;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Ian on 2/21/2016.
 * This class extends teh abstract visitor class(defined in utilities...needs to be refactored out of that)
 * The visit calls here need to know the currently being saved element in the xml
 * For this reason there is a private stack, after each doc.append there should be a currParent.push
 * at the end of each visit call, the stack should be peeked and popped for what was pushed to it
 * these parameters are for properly formatting the save file
 */
public class SaveVisitor implements ModelVisitor {
    /**
     * Private variables
     * These don't have getter/setters, will only be changed via the constructor or in method calls
     * The stack is used to know what the current parent is thats having parameters saved under it
     * The coord is to know if the current object being saved is at a coordinate what they are
     */
    private String fileName;
    private Document doc;
    private Stack<Element> currentParent;
    private Coord currCoord;

    /**
     * Constructors including the default constructor
     * note they both throw a ParserConfigException
     */
    public SaveVisitor(String fileName) throws ParserConfigurationException {
        this.fileName = fileName + ".xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        currentParent = new Stack<>();
    }
    public SaveVisitor() throws ParserConfigurationException {
        this.fileName = "save.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        currentParent = new Stack<>();
    }

    /**
     *The visit functions
     * See comments on class for more specifics on how these visits work
     */
    public void visitMap(Map map) {
        //visit Map should be the first thing called so it should also be the root element in the xml
        Element mapElement = doc.createElement("Map");
        doc.appendChild(mapElement);
        //map is the root element in the xml, so should be the last element pooped from stack
        currentParent.push(mapElement);
        MapArea[] mas = map.getMapAreas();
        for(MapArea ma : mas){
            ma.accept(this);
        }
        if(currentParent.peek().equals(mapElement)){
            System.out.println("stack cleared");
            currentParent.pop();
        }else {
            System.out.println("stack not cleared at end of save game");
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File saved!");

    }

    @Override
    public void visitAvatar(Avatar avatar) {
        Element entityElement = doc.createElement("Avatar");
        currentParent.peek().appendChild(entityElement);
        currentParent.push(entityElement);
        visitEntityAttributesAndElements(avatar, entityElement);
        if(currentParent.peek().equals(entityElement)){
            System.out.println("map area saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving maparea, stack not at the proper element");
        }
    }
    private void visitEntityAttributesAndElements(Entity e, Element entityElement){
        String name = e.getName();
        if(name == null){
            name = "nameNotSet";
        }
        entityElement.setAttribute("Name", name);
        /* ALL OTHER ENTITY ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        //inv/equipped visit, stats visit, occupation visit, etc...
        appendDirectionAttribute(entityElement, e.getFacingDirection());
        appendCoordElement(entityElement, currCoord);
        e.getOccupation().accept(this);
        e.getStats().accept(this);
        e.getAbilityManager().accept(this);
        e.getBuffManager().accept(this);
        e.getInventory().accept(this);
        e.getEquipped().accept(this);
    }
    @Override
    public void visitPiggy(Piggy piggy) {

    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {

    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {

    }

    @Override
    public void visitStats(Stats s) {

    }

    @Override
    public void visitBuffManager(BuffManager bm) {

    }

    @Override
    public void visitBuff(Buff b) {

    }

    @Override
    public void visitBuffOverTime(BuffOverTime buffOverTime) {

    }

    @Override
    public void visitAbilityManager(AbilityManager am) {

    }

    @Override
    public void visitAbility(Ability ability) {

    }

    @Override
    public void visitSummoner(Summoner s) {
        Element occElement = doc.createElement("Summoner");
        currentParent.peek().appendChild(occElement);
        //right now occupation doesn't hold anything add additional save logic here when it does
    }

    @Override
    public void visitSneak(Sneak s) {
        Element occElement = doc.createElement("Sneak");
        currentParent.peek().appendChild(occElement);
        //right now occupation doesn't hold anything add additional save logic here when it does
    }

    @Override
    public void visitSmasher(Smasher s) {
        Element occElement = doc.createElement("Sneak");
        currentParent.peek().appendChild(occElement);
        //right now occupation doesn't hold anything add additional save logic here when it does
    }

    @Override
    public void visitDummyOcc(DummyOccupation dummyO) {

    }

    @Override
    public void visitInventory(Inventory i) {

    }

    @Override
    public void visitPotion(Potion p) {

    }

    @Override
    public void vistUsableItem(UsableItems ui) {

    }

    @Override
    public void visitEquipped(Equipped e) {

    }

    @Override
    public void visitHat(Hat h) {

    }

    @Override
    public void visitOneHandedWeapon(OneHandedWeapon ohw) {

    }

    @Override
    public void visitTwoHandedWeapon(TwoHandedWeapon thw) {

    }

    @Override
    public void visitStatsAddable(StatsAddable sa) {

    }

    public void visitMapArea(MapArea ma){
        Element maElement = doc.createElement("MapArea");
        currentParent.peek().appendChild(maElement);
        currentParent.push(maElement);
        maElement.setAttribute("Name", ma.getName());
        Tile[][] tiles= ma.getTiles();
        for(int r = 0; r != ma.getMaxR(); ++r){
            for(int s = 0; s != ma.getMaxS(); ++s){
                currCoord = new Coord(r,s);
                tiles[r][s].accept(this);
            }
        }
        if(currentParent.peek().equals(maElement)){
            System.out.println("map area saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving maparea, stack not at the proper element");
        }
    }

    public void visitTile(Tile t){
        Iterator<Entity> entityIterator = t.getEntityIterator();
        while (entityIterator.hasNext())
            entityIterator.next().accept(this);

        Entity[] nonCollide = t.getNonCollideableEntities();
        MapItem[] mapItems = t.getMapItems();
        for(Entity nonColE : nonCollide){
            nonColE.accept(this);
        }
        for(MapItem ma : mapItems){
            ma.accept(this);
        }
    }

    /**
     * needs to be refactored for each type of entity
     * this is currently unused
     */
    public void visitEntity(Entity e){
        Element entityElement = doc.createElement("Entity");
        currentParent.peek().appendChild(entityElement);
        currentParent.push(entityElement);
        String name = e.getName();
        if(name.equals(null)){
            name = "nameNotSet";
        }
        entityElement.setAttribute("Name", name);

        appendCoordElement(entityElement, currCoord);
        if(currentParent.peek().equals(entityElement)){
            System.out.println("map area saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving maparea, stack not at the proper element");
        }
    }

    /**
     *This will probably be replaced by a visit for each type of map item
     */
    public void visitMapItem(MapItem mi){
        Element miElement = doc.createElement("MapItem");
        currentParent.peek().appendChild(miElement);
        currentParent.push(miElement);
        miElement.setAttribute("Name", mi.getItemName());
        /* ALL OTHER MapItem ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        appendCoordElement(miElement, currCoord);
        if(currentParent.peek().equals(miElement)){
            System.out.println("map area saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving maparea, stack not at the proper element");
        }
    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {

    }

    @Override
    public void visitOneShotItem(OneShotItem osi) {

    }

    /**
     *Don't need to visit terrain in save visitor, the levelFactory handles all of this being recorded
     */
    @Override
    public void visitGrass(Grass grass) {
    }

    @Override
    public void visitMountain(Mountain mountain) {

    }

    @Override
    public void visitWater(Water water) {

    }

    /**
     * This appends a coord element to each element that would appear on a tile
     * When loading note that none of them except avatar hold their own coordinates
     * its used to place the object back onto the correct tile
     */
    private void appendCoordElement(Element parent, Coord c){
        Element coordElement = doc.createElement("Location");
        parent.appendChild(coordElement);
        coordElement.setAttribute("s", "" + c.getS());
        coordElement.setAttribute("r", "" + c.getR());
    }

    private void appendDirectionAttribute(Element parent, Direction d){
        parent.setAttribute("Direction", d + "");
    }
}
