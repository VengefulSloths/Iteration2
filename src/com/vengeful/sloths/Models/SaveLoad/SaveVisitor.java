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
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Knuckle;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.*;
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
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
            System.out.println("Avatar saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Avatar, stack not at the proper element");
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
        Element npcElement = doc.createElement("Piggy");
        currentParent.peek().appendChild(npcElement);
        currentParent.push(npcElement);
        visitEntityAttributesAndElements(piggy, npcElement);
        if(currentParent.peek().equals(npcElement)){
            System.out.println("piggy saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving piggy, stack not at the proper element");
        }
    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        Element npcElement = doc.createElement("AggressiveNPC");
        currentParent.peek().appendChild(npcElement);
        currentParent.push(npcElement);
        visitEntityAttributesAndElements(aNPC, npcElement);
        if(currentParent.peek().equals(npcElement)){
            System.out.println("aggressiveNPC saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving aggressiveNPC, stack not at the proper element");
        }
    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {
        Element npcElement = doc.createElement("NonAggressiveNPC");
        currentParent.peek().appendChild(npcElement);
        currentParent.push(npcElement);
        visitEntityAttributesAndElements(nonANPC, npcElement);
        if(currentParent.peek().equals(npcElement)){
            System.out.println("nonaggressiveNPC saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving nonaggressiveNPC, stack not at the proper element");
        }
    }

    @Override
    public void visitStats(Stats s) {
        Element statsElement = doc.createElement("Stats");
        currentParent.peek().appendChild(statsElement);
        currentParent.push(statsElement);
        statsElement.setAttribute("strength", s.getStrength() + "");
        statsElement.setAttribute("agility", s.getAgility() + "");
        statsElement.setAttribute("intellect", s.getIntellect() + "");
        statsElement.setAttribute("hardiness", s.getHardiness() + "");
        statsElement.setAttribute("movement", s.getMovement() + "");
        statsElement.setAttribute("level", s.getLevel() + "");
        statsElement.setAttribute("currentHealth", s.getCurrentHealth() + "");
        statsElement.setAttribute("currentMana", s.getCurrentMana() + "");
        statsElement.setAttribute("currentExperience", s.getCurrentExperience() + "");
        currentParent.pop();
    }

    @Override
    public void visitBuffManager(BuffManager bm) {
        Element bmElement = doc.createElement("BuffManager");
        currentParent.peek().appendChild(bmElement);
        currentParent.push(bmElement);
        //right now it just holds abilites so put other logic here when it gets added
        Buff[] buffs = bm.getBuffs();
        for(Buff b : buffs){
            b.accept(this);
        }
        if(currentParent.peek().equals(bmElement)){
            System.out.println("visited buffs successfully, stack at proper element");
        }
        else{
            System.out.println("didn't visit buffs properly stack at wrong element");
        }
        currentParent.pop();
    }

    @Override
    public void visitBuff(Buff b) {
        Element bElement = doc.createElement("Buff");
        currentParent.peek().appendChild(bElement);
        currentParent.push(bElement);
        bElement.setAttribute("duration", b.getDuration() + "");
        b.getBuff().accept(this);
        if(currentParent.peek().equals(bElement)){
            System.out.println("stack at proper element after recording buff");
        }else{
            System.out.println("stack not at proper element after recording buff");
        }
        currentParent.pop();
    }

    @Override
    public void visitBuffOverTime(BuffOverTime buffOverTime) {
        Element bElement = doc.createElement("BuffOverTime");
        currentParent.peek().appendChild(bElement);
        currentParent.push(bElement);
        bElement.setAttribute("duration", buffOverTime.getDuration() + "");
        buffOverTime.getBuff().accept(this);
        if(currentParent.peek().equals(bElement)){
            System.out.println("stack at proper element after recording buffovertime");
        }else{
            System.out.println("stack not at proper element after recording buffovertime");
        }
        currentParent.pop();
    }

    @Override
    public void visitAbilityManager(AbilityManager am) {
        Element abmElement = doc.createElement("AbilityManager");
        currentParent.peek().appendChild(abmElement);
        currentParent.push(abmElement);
        //right now it just holds abilites so put other logic here when it gets added
        Ability[] abilities = am.getAbilities();
        for(Ability ab : abilities){
            ab.accept(this);
        }
        if(currentParent.peek().equals(abmElement)){
            System.out.println("visited abilites successfully, stack at proper element");
        }
        else{
            System.out.println("didn't visit abilites properly stack at wrong element");
        }
        currentParent.pop();
    }

    @Override
    public void visitAbility(Ability ability) {
        //doesn't do anything right now
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
        Element iElement = doc.createElement("Inventory");
        currentParent.peek().appendChild(iElement);
        currentParent.push(iElement);
        iElement.setAttribute("maxSize", i.getMaxSize() +"");
        iElement.setAttribute("currentSize", i.getCurrentSize() +"");
        InventoryItem[] arr = i.getArrayofItems();
        for(InventoryItem ii : arr){
            ii.accept(this);
        }
        if(currentParent.peek().equals(iElement)){
            System.out.println("Inventory saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Inventory, stack not at the proper element");
        }
    }

    @Override
    public void visitPotion(Potion p) {
        Element eElement = doc.createElement("Potion");
        currentParent.peek().appendChild(eElement);
        currentParent.push(eElement);
        eElement.setAttribute("itemName", p.getItemName());
        p.getItemStats().accept(this);
        if(currentParent.peek().equals(eElement)){
            System.out.println("Potion saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving UsableItem, stack not at the proper element");
        }
    }

    @Override
    public void vistUsableItem(UsableItems ui) {
        Element eElement = doc.createElement("UsableItem");
        currentParent.peek().appendChild(eElement);
        currentParent.push(eElement);
        eElement.setAttribute("itemName", ui.getItemName());
        if(currentParent.peek().equals(eElement)){
            System.out.println("UsableItem saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving UsableItem, stack not at the proper element");
        }
    }

    @Override
    public void visitEquipped(Equipped e) {
        Element eElement = doc.createElement("Equipped");
        currentParent.peek().appendChild(eElement);
        currentParent.push(eElement);
        if(e.getHat() != null){
            e.getHat().accept(this);
        }
        if(e.getWeapon() != null){
            e.getWeapon().accept(this);
        }
        //not saving entity stats here...will be passed to it in load
        if(currentParent.peek().equals(eElement)){
            System.out.println("Equipped saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Equipped, stack not at the proper element");
        }
    }

    @Override
    public void visitHat(Hat h) {
        Element hElement = doc.createElement("Hat");
        currentParent.peek().appendChild(hElement);
        currentParent.push(hElement);
        hElement.setAttribute("itemName", h.getItemName());
        h.getItemStats().accept(this);
        if(currentParent.peek().equals(hElement)){
            System.out.println("Hat saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Hat, stack not at the proper element");
        }
    }

    @Override
    public void visitOneHandedWeapon(OneHandedWeapon ohw) {
        Element ohwElement = doc.createElement("OneHandedWeapon");
        currentParent.peek().appendChild(ohwElement);
        currentParent.push(ohwElement);
        ohwElement.setAttribute("itemName", ohw.getItemName());
        ohwElement.setAttribute("baseDamage", ohw.getBaseDamage() +"");
        ohw.getItemStats().accept(this);
        if(currentParent.peek().equals(ohwElement)){
            System.out.println("OneHandedWeap saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving OneHandedWeap, stack not at the proper element");
        }
    }

    @Override
    public void visitTwoHandedWeapon(TwoHandedWeapon thw) {
        Element thwElement = doc.createElement("TwoHandedWeapon");
        currentParent.peek().appendChild(thwElement);
        currentParent.push(thwElement);
        thwElement.setAttribute("itemName", thw.getItemName());
        thwElement.setAttribute("baseDamage", thw.getBaseDamage() +"");
        thw.getItemStats().accept(this);
        if(currentParent.peek().equals(thwElement)){
            System.out.println("TwoHandedWeap saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving TwoHandedWeap, stack not at the proper element");
        }
    }

    @Override
    public void visitKnuckle(Knuckle thw) {
        Element thwElement = doc.createElement("Knuckle");
        currentParent.peek().appendChild(thwElement);
        currentParent.push(thwElement);
        thwElement.setAttribute("itemName", thw.getItemName());
        thwElement.setAttribute("baseDamage", thw.getBaseDamage() +"");
        thw.getItemStats().accept(this);
        if(currentParent.peek().equals(thwElement)){
            System.out.println("Knuckle saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Knuckle, stack not at the proper element");
        }
    }

    public void visitStatsAddable(StatsAddable sa) {
        Element saElement = doc.createElement("StatsAddable");
        currentParent.peek().appendChild(saElement);
        saElement.setAttribute("strength", sa.getStrength()+ "");
        saElement.setAttribute("agility", sa.getAgility()+ "");
        saElement.setAttribute("intellect", sa.getIntellect()+ "");
        saElement.setAttribute("hardiness", sa.getHardiness()+ "");
        saElement.setAttribute("movement", sa.getMovement()+ "");
        saElement.setAttribute("currentHealth", sa.getCurrentHealth()+ "");
        saElement.setAttribute("bonusHealth", sa.getBonusHealth()+ "");
        saElement.setAttribute("currentMana", sa.getCurrentMana()+ "");
        saElement.setAttribute("bonusMana", sa.getBonusMana()+ "");
        saElement.setAttribute("currentExperience", sa.getCurrentExperience()+ "");
    }

//    public void visitAgilityAddable(StatsAddable sa){
//        Element saElement = doc.createElement("AgilityStatsAddable");
//        currentParent.peek().appendChild(saElement);
//        saElement.setAttribute("agility", sa.getAgility()+ "");
//    }
//
//    public void visitBaseStatsAddable(StatsAddable sa){
//        Element saElement = doc.createElement("GenericStatsAddable");
//        currentParent.peek().appendChild(saElement);
//        saElement.setAttribute("strength", sa.getStrength()+ "");
//        saElement.setAttribute("agility", sa.getAgility()+ "");
//        saElement.setAttribute("intellect", sa.getIntellect()+ "");
//        saElement.setAttribute("hardiness", sa.getHardiness()+ "");
//        saElement.setAttribute("movement", sa.getMovement()+ "");
//    }
//
//    public void visitCurrentHealthAddable(StatsAddable sa){
//        Element saElement = doc.createElement("CurrentHealthAddable");
//        currentParent.peek().appendChild(saElement);
//        saElement.setAttribute("strength", sa.getStrength()+ "");
//    }
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
        /*
        Iterator<Entity> entityIterator = t.getEntityIterator();
        while (entityIterator.hasNext())
            entityIterator.next().accept(this);*/
        Entity[] eArr = t.getEntities();
        if(eArr.length > 0){
            System.out.println("Entity Found");
        }
        //not being used anymore
        MapItem[] mapItems = t.getMapItems();
        if(mapItems.length > 0){
            System.out.println("MapItems Found");
        }
        for(Entity e: eArr){
            e.accept(this);
        }
        for(MapItem ma : mapItems){
            ma.accept(this);
        }
    }

    /**
     * needs to be refactored for each type of entity
     * this is currently unused and in process of being refactored
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
    public void visitTeleportSenderTile(TeleportSenderTile t) {

    }

    @Override
    public void visitTeleportDestinationTile(TeleportDestinationTile t) {

    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {
        Element tiElement = doc.createElement("TakeableItem");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("Name", takeableItem.getItemName());
        appendCoordElement(tiElement, currCoord);
        takeableItem.getInventorpRep().accept(this);
        if(currentParent.peek().equals(tiElement)){
            System.out.println("takeable saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving takeable, stack not at the proper element");
        }
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        Element tiElement = doc.createElement("OneShotItem");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("Name", obstacle.getItemName());
        appendCoordElement(tiElement, currCoord);
        if(currentParent.peek().equals(tiElement)){
            System.out.println("Obstacle saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Obstacle, stack not at the proper element");
        }
    }

    @Override
    public void visitOneShotItem(OneShotItem osi) {
        Element tiElement = doc.createElement("OneShotItem");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("Name", osi.getItemName());
        appendCoordElement(tiElement, currCoord);
        if(currentParent.peek().equals(tiElement)){
            System.out.println("OneShot saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving OneShot, stack not at the proper element");
        }
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

    @Override
    public void visitSkillManager(SkillManager skillManager) {
        
    }

    @Override
    public void visitSkill(Skill skill) {

    }

    @Override
    public void visitHitBox(HitBox hitBox) {

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
