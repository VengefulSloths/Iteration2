package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.AngleSpellAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.ExplosionAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.FireBallAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.BuffOverTime;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.AreaEffects.HealDamageAE;
import com.vengeful.sloths.Models.Map.AreaEffects.InstantDeathAE;
import com.vengeful.sloths.Models.Map.AreaEffects.LevelUpAE;
import com.vengeful.sloths.Models.Map.AreaEffects.TakeDamageAE;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.BreakBoxQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.*;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.MovableHitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
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
import java.util.*;

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
        Element mapElement = doc.createElement("Map");
        currentParent.peek().appendChild(mapElement);
        currentParent.push(mapElement);
        MapArea[] mas = map.getMapAreas();
        for(MapArea ma : mas){
            ma.accept(this);
        }
        if(currentParent.peek().equals(mapElement)){
//            System.out.println("stack cleared after map saved");
            currentParent.pop();
        }else {
            System.out.println("some error within saving Map");
        }

    }

    public void save(){
        Element root = doc.createElement("YourSavedGame");
        doc.appendChild(root);
        currentParent.push(root);
        Map.getInstance().accept(this);
        ((AdaptableStrategy)(MainController.getInstance().getInputStrategy())).accept(this);
        if(currentParent.peek().equals(currentParent)){
            System.out.println("Stack cleared at end of save");
        }
        currentParent.pop();
        completeSave();
    }

    public void completeSave(){
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
    public void visitAbilityItem(AbilityItem abilityItem) {

    }

    @Override
    public void visitAvatar(Avatar avatar) {
        Element entityElement = doc.createElement("Avatar");
        currentParent.peek().appendChild(entityElement);
        currentParent.push(entityElement);
        visitEntityAttributesAndElements(avatar, entityElement);
        if(currentParent.peek().equals(entityElement)){
//            System.out.println("Avatar saved with stack at proper element");
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
        GenericStatsAddable gsa = e.getAllEntityStatEffects();
        gsa.invert();
        e.getStats().add(gsa);
        e.getStats().accept(this);
        gsa.invert();
        e.getStats().add(gsa);
        e.getSkillManager().accept(this);
        e.getBuffManager().accept(this);
        e.getAbilityManager().accept(this);
        e.getOccupation().accept(this);
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
//            System.out.println("piggy saved with stack at proper element");
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
//            System.out.println("aggressiveNPC saved with stack at proper element");
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
//            System.out.println("nonaggressiveNPC saved with stack at proper element");
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
//            System.out.println("visited buffs successfully, stack at proper element");
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
        //bElement.setAttribute("duration", b.getDuration() + "");
        b.getBuff().accept(this);
        if(currentParent.peek().equals(bElement)){
//            System.out.println("stack at proper element after recording buff");
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
        //bElement.setAttribute("duration", buffOverTime.getDuration() + "");
        buffOverTime.getBuff().accept(this);
        if(currentParent.peek().equals(bElement)){
//            System.out.println("stack at proper element after recording buffovertime");
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
        Element absElement = doc.createElement("Abilities");
        currentParent.peek().appendChild(absElement);
        currentParent.push(absElement);
        Ability[] abilities = am.getAbilities();
        for(Ability ab : abilities){
            ab.accept(this);
        }
        currentParent.pop();
        Element actAbsElement = doc.createElement("ActiveAbilities");
        currentParent.peek().appendChild(actAbsElement);
        currentParent.push(actAbsElement);
        Ability[] activeAbilities = am.getActiveAbilities();
        for(Ability ab : activeAbilities){
            if(ab != null){
                ab.accept(this);
            }
        }
        currentParent.pop();
        if(currentParent.peek().equals(abmElement)){
//            System.out.println("visited abilites successfully, stack at proper element");
        }
        else{
            System.out.println("didn't visit abilites properly stack at wrong element");
        }
        currentParent.pop();
    }

    @Override
    public void visitAbility(Ability ability) {
        //this high ofa lelve doesnt do anything
    }
    public void visitMeleeAttackAbility(MeleeAttackAbility meleeAttackAbility) {
        //will get stats and entity when loading
        Element abElement = doc.createElement("MeleeAttackAbility");
        currentParent.peek().appendChild(abElement);
        abElement.setAttribute("windTicks", meleeAttackAbility.getWindTicks() +"");
        abElement.setAttribute("coolTicks", meleeAttackAbility.getCoolTicks() +"");
    }

    public void visitBindWounds(BindWoundsAbility bindWoundsAbility) {
        //will entity and skillmanager when loading
        Element abElement = doc.createElement("BindWoundsAbility");
        currentParent.peek().appendChild(abElement);
    }


    public void visitFireBallAbility(FireBallAbility fireBallAbility) {
        Element fbaElement = doc.createElement("FireBallAbility");
        currentParent.peek().appendChild(fbaElement);
        fbaElement.setAttribute("startUpTicks", fireBallAbility.getStartupTicks() +"");
        fbaElement.setAttribute("coolDownTicks", fireBallAbility.getCoolDownTicks() +"");
        fbaElement.setAttribute("travelDistance", fireBallAbility.getTravelDistance() +"");
        fbaElement.setAttribute("travelTime", fireBallAbility.getTravelTime() +"");
        fbaElement.setAttribute("manaCost", fireBallAbility.getManaCost() +"");
    }

    public void visitExplosionAbility(ExplosionAbility explosionAbility) {
        Element exA = doc.createElement("ExplosionAbility");
        currentParent.peek().appendChild(exA);
        exA.setAttribute("windTicks", explosionAbility.getWindTicks() + "");
        exA.setAttribute("coolTicks", explosionAbility.getCoolTicks() + "");
        exA.setAttribute("expandingTime", explosionAbility.getExpandingTime() +"");
        exA.setAttribute("expandingDistance", explosionAbility.getExpandingDistance() +"");
        exA.setAttribute("manaCost", explosionAbility.getManaCost() +"");
    }

    @Override
    public void visitAngleSpellAbility(AngleSpellAbility angleSpellAbility) {
        //TODO: new ability. Make sure to save, thanks!
    }

    @Override
    public void visitRemoveTrapAbility(RemoveTrapAbility removeTrapAbility) {
        //TODO: new ability. Make sure to save, thanks!
    }

    @Override
    public void visitStealthAbility(StealthAbility stealthAbility) {
        //TODO: save this thing
    }


    public void visitBreakBoxQuest(BreakBoxQuest breakBoxQuest) {
//        Element bbqElement = doc.createElement("BreakBoxQuest");
//        currentParent.peek().appendChild(bbqElement);
//        Element tilesElement = doc.createElement("tiles");
//        Tile[] tiles = breakBoxQuest.getTiles();
//        int i = 0;
//        for(Tile t : tiles){
////            tilesElement.setAttribute("tile: " + i, t.);
//            for(int)
//        }
        System.out.println("Not supposed to be using this quest");
    }

    @Override
    public void visitDoDestroyObstacleQuest(DoDestroyObstacleQuest doDestroyObstacleQuest) {
        Element ddoqElement = doc.createElement("DoDestroyObstacleQuest");
        currentParent.peek().appendChild(ddoqElement);
        Element targetElement = doc.createElement("target");
        ddoqElement.appendChild(targetElement);
        targetElement.setAttribute("s", "" + doDestroyObstacleQuest.getTarget().getS());
        targetElement.setAttribute("r", "" + doDestroyObstacleQuest.getTarget().getR());
    }

    @Override
    public void visitHasItemQuest(HasItemQuest hasItemQuest) {
        Element hiq = doc.createElement("HasItemQuest");
        currentParent.peek().appendChild(hiq);
        currentParent.push(hiq);
        hiq.setAttribute("itemName", hasItemQuest.getItemName());
        hasItemQuest.getNextStep().accept(this);
        if(!currentParent.peek().equals(hiq)){
            System.out.println("error saving hasItemQuest");
        }
        currentParent.pop();
    }

    @Override
    public void visitSelfBuffAbility(SelfBuffAbility selfBuffAbility) {
        Element sba = doc.createElement("SelfBuffAbility");
        currentParent.peek().appendChild(sba);
        currentParent.push(sba);
        sba.setAttribute("windTicks", selfBuffAbility.getWindTicks() + "");
        sba.setAttribute("coolTicks", selfBuffAbility.getCoolTicks() + "");
        selfBuffAbility.getBuff().accept(this);
        if(!currentParent.peek().equals(sba)){
            System.out.println("error saving selfBuffAbility");
        }
        currentParent.pop();
    }
    //mount ability needs to get observers from avatar once its loaded
    //save buff here
    @Override
    public void visitMountAbility(MountAbility mountAbility) {
//        Element ma = doc.createElement("MountAbility");
//        currentParent.peek().appendChild(ma);
//        currentParent.push(ma);
//        ma.setAttribute("windTicks", mountAbility.getWindTicks() + "");
//        ma.setAttribute("coolTicks", mountAbility.getCoolTicks() + "");
//        ma.setAttribute("mountName", mountAbility.getMountName());
//        mountAbility.getBuff().accept(this);
//        if(!currentParent.peek().equals(ma)){
//            System.out.println("error saving mountAbilit");
//        }
//        currentParent.pop();
    }

    @Override
    public void visitRemoveBuffAbility(RemoveBuffAbility removeBuffAbility) {
    //not needed atm just need demount which extends from this
    }

    @Override
    public void visitNullAbility(NullAbility nullAbility) {
        //not needed, creating abilityManager initialized active abilities to null
    }
    //demount ability needs to get observers from avatar once its loaded
    //needs to get buff from mount ability...
    //loading sequence will have to be hardcoded into loader
    @Override
    public void visitDemountAbility(DemountAbility demountAbility) {
//        Element ma = doc.createElement("MountAbility");
//        currentParent.peek().appendChild(ma);
//        currentParent.push(ma);
//        ma.setAttribute("windTicks", demountAbility.getWindTicks() + "");
//        ma.setAttribute("coolTicks", demountAbility.getCoolTicks() + "");
//        demountAbility.getBuff().accept(this);
//        if(!currentParent.peek().equals(ma)){
//            System.out.println("error saving demountAbilit");
//        }
//        currentParent.pop();
    }

    @Override
    public void visitMount(Mount mount) {
        Element mE = doc.createElement("Mount");
        currentParent.peek().appendChild(mE);
        mE.setAttribute("name", mount.getName());
        mE.setAttribute("moveSpeed", mount.getMoveSpeed() + "");
    }

    @Override

    public void visitTrap(Trap trap) {
        //TODO: newly added mapItem, make sure to save as well. Thanks!
    }

    public void visitAdaptableStrategy(AdaptableStrategy adaptableStrategy) {
        Element asElement = doc.createElement("AdaptableStrategy");
        currentParent.peek().appendChild(asElement);
        HashMap<Integer, KeyMapping> hm = adaptableStrategy.getKeyMappings();
        for(java.util.Map.Entry<Integer, KeyMapping> entry : hm.entrySet()){
            Element entryElement = doc.createElement("Entry");
            asElement.appendChild(entryElement);
            entryElement.setAttribute("key", entry.getKey() +"");
            entryElement.setAttribute("value", entry.getValue().getValue() + "");
        }
        System.out.println("keyMappingsSaved");
    }

    @Override
    public void visitGold(Gold gold) {
        Element gElement = doc.createElement("Gold");
        currentParent.peek().appendChild(gElement);
        currentParent.push(gElement);
        gElement.setAttribute("itemName", gold.getItemName());
        gElement.setAttribute("value", gold.getValue() + "");
        appendCoordElement(gElement, currCoord);
        if(currentParent.peek().equals(gElement)){
//            System.out.println("takeable saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving gold, stack not at the proper element");
        }
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
        Element occElement = doc.createElement("DummyOccupation");
        currentParent.peek().appendChild(occElement);
    }

    @Override
    public void visitInventory(Inventory i) {
        Element iElement = doc.createElement("Inventory");
        currentParent.peek().appendChild(iElement);
        currentParent.push(iElement);
        iElement.setAttribute("maxSize", i.getMaxSize() +"");
        iElement.setAttribute("currentSize", i.getCurrentSize() +"");
        iElement.setAttribute("gold", i.getGold() + "");
        InventoryItem[] arr = i.getArrayofItems();
        for(InventoryItem ii : arr){
            ii.accept(this);
        }
        if(currentParent.peek().equals(iElement)){
//            System.out.println("Inventory saved with stack at proper element");
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
//            System.out.println("Potion saved with stack at proper element");
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
//            System.out.println("UsableItem saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving UsableItem, stack not at the proper element");
        }
    }

    @Override
    public void visitTakeDamageAE(TakeDamageAE t) {

    }

    @Override
    public void visitHealDamageAE(HealDamageAE h) {

    }

    @Override
    public void visitInstantDeathAE(InstantDeathAE i) {

    }

    @Override
    public void visitLevelUpAE(LevelUpAE ae) {

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
        if(e.getMount() != null){
            e.getMount().accept(this);
        }
        //gets stats, ability and skill manager from entity in load
        if(currentParent.peek().equals(eElement)){
//            System.out.println("Equipped saved with stack at proper element");
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
//            System.out.println("Hat saved with stack at proper element");
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
//            System.out.println("OneHandedWeap saved with stack at proper element");
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
//            System.out.println("TwoHandedWeap saved with stack at proper element");
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
//            System.out.println("Knuckle saved with stack at proper element");
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
                if(tiles[r][s] != null){
                    tiles[r][s].accept(this);
                }
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
//        if(eArr.length > 0){
//            System.out.println("Entity Found");
//        }
        MapItem[] mapItems = t.getMapItems();
//        if(mapItems.length > 0){
//            System.out.println("MapItems Found");
//        }
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
//            System.out.println("map area saved with stack at proper element");
            currentParent.pop();
        }else{
//            System.out.println("some error saving maparea, stack not at the proper element");
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
//            System.out.println("map area saved with stack at proper element");
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
        tiElement.setAttribute("itemName", takeableItem.getItemName());
        appendCoordElement(tiElement, currCoord);
        takeableItem.getInventorpRep().accept(this);
        if(currentParent.peek().equals(tiElement)){
//            System.out.println("takeable saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving takeable, stack not at the proper element");
        }
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        Element tiElement = doc.createElement("Obstacle");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("Name", obstacle.getItemName());
        appendCoordElement(tiElement, currCoord);
        if(currentParent.peek().equals(tiElement)){
//            System.out.println("Obstacle saved with stack at proper element");
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
        tiElement.setAttribute("itemName", osi.getItemName());
        appendCoordElement(tiElement, currCoord);
        if(currentParent.peek().equals(tiElement)){
//            System.out.println("OneShot saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving OneShot, stack not at the proper element");
        }
    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {
        Element tiElement = doc.createElement("InteractiveItem");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("itemName", item.getItemName());
        appendCoordElement(tiElement, currCoord);
        item.getQuest().accept(this);
        if(currentParent.peek().equals(tiElement)){
//            System.out.println("OneShot saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving Interactive, stack not at the proper element");
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
        Element tiElement = doc.createElement("SkillManager");
        currentParent.peek().appendChild(tiElement);
        currentParent.push(tiElement);
        tiElement.setAttribute("availableSkillPoints", skillManager.getAvailableSkillPoints() +"");
        Iterator<Skill> skills = skillManager.getSkillsIter();
        while(skills.hasNext()){
            Skill curr = skills.next();
            curr.accept(this);
        }
        if(currentParent.peek().equals(tiElement)){
//            System.out.println("SkillManager saved with stack at proper element");
            currentParent.pop();
        }else{
            System.out.println("some error saving SkillManager, stack not at the proper element");
        }

    }

    @Override
    public void visitSkill(Skill skill) {
        Element tiElement = doc.createElement("Skill");
        currentParent.peek().appendChild(tiElement);
        tiElement.setAttribute("name", skill.getName());
        tiElement.setAttribute("level", skill.getLevel() +"");
    }

    @Override
    public void visitMovableHitBox(MovableHitBox movableHitBox) {

    }

    @Override
    public void visitImmovableHitBox(ImmovableHitBox immovableHitBox) {

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
