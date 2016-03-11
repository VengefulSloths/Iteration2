package com.vengeful.sloths.Models.SaveLoad;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.InternalError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.vengeful.sloths.Controllers.ControllerManagers.AggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.PiggyControllerManager;
import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.FireBallAbility;
import com.vengeful.sloths.Models.Ability.Abilities.MeleeAttackAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Knuckle;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.Quest;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Occupation.*;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
import java.io.IOException;

/**
 * Created by Ian on 3/7/2016.
 */
public class Loader {
    String fileName;
    Document doc;

    public Loader(String fileName) throws ParserConfigurationException, IOException, SAXException {
        this.fileName = fileName;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(fileName);
    }

    public void loadAreas(MapArea[] areas){
        Node root = doc.getDocumentElement();
        NodeList mapAreas = root.getChildNodes();
        if(root.hasChildNodes()){
            for(int i = 0; i != mapAreas.getLength(); ++i){
                Node currMA = mapAreas.item(i);
                if(currMA.getNodeType() == Node.ELEMENT_NODE && currMA.getNodeName().equals("MapArea")){
                    if(currMA.hasChildNodes()){
                        String maName = currMA.getAttributes().item(0).getNodeValue();
                        MapArea loading = null;
                        for(MapArea ma : areas){
                            if(ma.getName().equals(maName)){
                                loading = ma;
                            }
                        }
                        NodeList tileObjects = currMA.getChildNodes();
                        for(int j = 0; j != tileObjects.getLength(); ++j){
                            Node currObject = tileObjects.item(j);
                            if(currObject.getNodeType() != Node.ELEMENT_NODE){
                                System.out.print("Expected an element node to be a child of map area");
                                continue;
                            }
                            switch (currObject.getNodeName()){
                                case "Avatar":
                                    Avatar a = processAvatar(currObject);
                                    loading.getTile(a.getLocation()).addEntity(a);
                                    break;
                                case "TakeableItem" :
                                    TakeableItem t = processTakeable(currObject);
                                    loading.getTile(t.getLocation()).addTakeableItem(t);
                                    break;
                                case "Obstacle" :
                                    Obstacle o = processObstacle(currObject);
                                    loading.getTile(o.getLocation()).addObstacle(o);
                                    break;
                                case "OneShotItem" :
                                    OneShotItem osi = processOneShotItem(currObject);
                                    loading.getTile(osi.getLocation()).addOneShotItem(osi);
                                    break;
                                case "Piggy" :
                                    Piggy p = processPiggy(currObject);
                                    PiggyControllerManager pcm = new PiggyControllerManager(loading,p);
                                    loading.getTile(p.getLocation()).addEntity(p);
                                    break;
                                case "AggressiveNPC" :
                                    AggressiveNPC aNPC = processAggressiveNPC(currObject);
                                    AggressiveNPCControllerManager ancm = new AggressiveNPCControllerManager(loading, aNPC);
                                    loading.getTile(aNPC.getLocation()).addEntity(aNPC);
                                    break;
                                case "InteractiveItem" :
                                    InteractiveItem ii = processInteractiveItem(currObject);
                                default: System.out.println(currObject.getNodeName() + " doesn't have a case to handle it");
                            }
                        }

                    }
                }
                else{
                    System.out.println("MapArea expected got " + currMA.getNodeName() + " instead" );
                }
            }
        }
    }
//untested
    private InteractiveItem processInteractiveItem(Node currObject) {
        InteractiveItem ii = new InteractiveItem();
        Element current = (Element) currObject;
        ii.setItemName(current.getAttribute("itemName"));
        ii.setLocation(processLocation(currObject.getChildNodes().item(0)));
        ii.setQuest(processQuest(current.getChildNodes().item(1)));
        return ii;
    }
//untested
    private Quest processQuest(Node item) {
        Element current = (Element) item;
        Quest q = null;
        switch (current.getNodeName()){
            case "HasItemQuest" :
                Quest q2 = processQuest(current.getChildNodes().item(0));
                q = new HasItemQuest(q2, current.getAttribute("itemName"));
                break;
            case"DoDestroyObstacleQuest":
                q = new DoDestroyObstacleQuest(processLocation(current.getChildNodes().item(0)));
                break;
            default:
                System.out.println(current.getNodeName() + "isn't a supported quest type");
        }
        return q;
    }

    private Piggy processPiggy(Node currObject) {
        Piggy p = new Piggy();
        NamedNodeMap avatarAttributes = currObject.getAttributes();
        for(int i = 0; i != avatarAttributes.getLength(); ++i){
            Node attribute = avatarAttributes.item(i);
            String attributeName = attribute.getNodeName();
            String value = attribute.getNodeValue();
            switch (attributeName){
                case "Direction":
                    Direction dir = Direction.N;
                    switch(value){
                        case "N":
                            dir = Direction.N;
                            break;
                        case "S":
                            dir = Direction.S;
                            break;
                        case "NE":
                            dir = Direction.NE;
                            break;
                        case "Nw":
                            dir = Direction.NW;
                            break;
                        case "SE":
                            dir = Direction.SE;
                            break;
                        case "SW":
                            dir = Direction.SW;
                            break;
                        default:
                            System.out.println(value + "is not a supporterd direction");
                    }
                    p.setFacingDirection(dir);
                    break;
                case "Name":
                    p.setName(value);
                    break;
                default:
                    System.out.println(attributeName + "is not a supported avatar attribute");
            }
        }
        NodeList avatarObjects = currObject.getChildNodes();
        for(int i = 0; i != avatarObjects.getLength(); ++i){
            Node avatarObject = avatarObjects.item(i);
            if(avatarObject.getNodeType() != Node.ELEMENT_NODE){
                System.out.println("Avatar object isn't an element type");
                continue;
            }
            String objectName = avatarObject.getNodeName();
            switch (objectName){
                case "Location" :
                    Coord c = processLocation(avatarObject);
                    p.setLocation(c);
                    break;
                case "Sneak" :
                    Sneak sn = new Sneak();
                    p.setOccupation(sn);
                    break;
                case "Smasher" :
                    Smasher sm = new Smasher();
                    p.setOccupation(sm);
                    break;
                case "Summoner" :
                    Summoner sr = new Summoner();
                    p.setOccupation(sr);
                    break;
                case "DummyOccupation" :
                    DummyOccupation d = new DummyOccupation();
                    p.setOccupation(d);
                    break;
                case "Stats" :
                    Stats s = processStats(avatarObject);
                    p.setStats(s);
                    break;
                case "AbilityManager" :
                    AbilityManager abm = processAbilityManager(avatarObject,p);
                    p.setAbilityManager(abm);
                    break;
                case "BuffManager" :
                    break;
                case "Inventory" :
                    Inventory inv = processInventory(avatarObject);
                    p.setInventory(inv);
                    break;
                case "Equipped" :
                    Equipped eq = processEquipped(avatarObject,p);
                    p.setEquipped(eq);
//                    eq.init(a);
                    break;
                case "SkillManager" :
                    SkillManager sk = processSkillManager(avatarObject);
                    p.setSkillManager(sk);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return p;
    }
    private AggressiveNPC processAggressiveNPC(Node currObject){
        AggressiveNPC p = new AggressiveNPC();
        NamedNodeMap avatarAttributes = currObject.getAttributes();
        for(int i = 0; i != avatarAttributes.getLength(); ++i){
            Node attribute = avatarAttributes.item(i);
            String attributeName = attribute.getNodeName();
            String value = attribute.getNodeValue();
            switch (attributeName){
                case "Direction":
                    Direction dir = Direction.N;
                    switch(value){
                        case "N":
                            dir = Direction.N;
                            break;
                        case "S":
                            dir = Direction.S;
                            break;
                        case "NE":
                            dir = Direction.NE;
                            break;
                        case "Nw":
                            dir = Direction.NW;
                            break;
                        case "SE":
                            dir = Direction.SE;
                            break;
                        case "SW":
                            dir = Direction.SW;
                            break;
                        default:
                            System.out.println(value + "is not a supporterd direction");
                    }
                    p.setFacingDirection(dir);
                    break;
                case "Name":
                    p.setName(value);
                    break;
                default:
                    System.out.println(attributeName + "is not a supported avatar attribute");
            }
        }
        NodeList avatarObjects = currObject.getChildNodes();
        for(int i = 0; i != avatarObjects.getLength(); ++i){
            Node avatarObject = avatarObjects.item(i);
            if(avatarObject.getNodeType() != Node.ELEMENT_NODE){
                System.out.println("Avatar object isn't an element type");
                continue;
            }
            String objectName = avatarObject.getNodeName();
            switch (objectName){
                case "Location" :
                    Coord c = processLocation(avatarObject);
                    p.setLocation(c);
                    break;
                case "Sneak" :
                    Sneak sn = new Sneak();
                    p.setOccupation(sn);
                    break;
                case "Smasher" :
                    Smasher sm = new Smasher();
                    p.setOccupation(sm);
                    break;
                case "Summoner" :
                    Summoner sr = new Summoner();
                    p.setOccupation(sr);
                    break;
                case "DummyOccupation" :
                    DummyOccupation d = new DummyOccupation();
                    p.setOccupation(d);
                    break;
                case "Stats" :
                    Stats s = processStats(avatarObject);
                    p.setStats(s);
                    break;
                case "AbilityManager" :
                    AbilityManager abm = processAbilityManager(avatarObject,p);
                    p.setAbilityManager(abm);
                    break;
                case "BuffManager" :
                    break;
                case "Inventory" :
                    Inventory inv = processInventory(avatarObject);
                    p.setInventory(inv);
                    break;
                case "Equipped" :
                    Equipped eq = processEquipped(avatarObject,p);
                    p.setEquipped(eq);
//                    eq.init(a);
                    break;
                case "SkillManager" :
                    SkillManager sk = processSkillManager(avatarObject);
                    p.setSkillManager(sk);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return p;
    }
    private OneShotItem processOneShotItem(Node currObject) {
        OneShotItem osi = new OneShotItem();
        Element current = (Element) currObject;
        osi.setItemName(current.getAttribute("itemName"));

        Node locatioNode = current.getChildNodes().item(0);
        osi.setLocation(processLocation(locatioNode));
        return osi;
    }
    private Obstacle processObstacle(Node currObject) {
        Obstacle o = new Obstacle();
        Element current = (Element) currObject;
        o.setItemName(current.getAttribute("itemName"));

        Node locatioNode = current.getChildNodes().item(0);
        o.setLocation(processLocation(locatioNode));
        return o;
    }
    private TakeableItem processTakeable(Node currObject) {
        TakeableItem ti = new TakeableItem();
        Element current = (Element) currObject;
        ti.setItemName(current.getAttribute("itemName"));

        Node locatioNode = current.getChildNodes().item(0);
        ti.setLocation(processLocation(locatioNode));

        Element invItemElement = (Element) current.getChildNodes().item(1);
        String invItemName = invItemElement.getNodeName();
        switch(invItemName){
            case "Potion" :
                Potion p = processPotion(invItemElement);
                ti.setInventorpRep(p);
                break;
            case "Hat":
                Hat h = processHat(invItemElement);
                ti.setInventorpRep(h);
                break;
//            case "Knuckle" :
//                Knuckle k = processKnuckle(invItemElement);
//                ti.setInventorpRep(p);
//                break;
            case "OneHandedWeapon" :
                OneHandedWeapon ohw = processOneHandedWeapon(invItemElement);
                ti.setInventorpRep(ohw);
                break;
            case "TwoHandedWeapon" :
                TwoHandedWeapon thw = processTwoHandedWeapon(invItemElement);
                ti.setInventorpRep(thw);
            default:
                System.out.println(invItemName + "isn't a supported inventoryItem representation");
        }
        return ti;
    }
    private Avatar processAvatar(Node currObject) {
        Avatar a = Avatar.getInstance();
        NamedNodeMap avatarAttributes = currObject.getAttributes();
        for(int i = 0; i != avatarAttributes.getLength(); ++i){
            Node attribute = avatarAttributes.item(i);
            String attributeName = attribute.getNodeName();
            String value = attribute.getNodeValue();
            switch (attributeName){
                case "Direction":
                    Direction dir = Direction.N;
                    switch(value){
                        case "N":
                            dir = Direction.N;
                            break;
                        case "S":
                            dir = Direction.S;
                            break;
                        case "NE":
                            dir = Direction.NE;
                            break;
                        case "Nw":
                            dir = Direction.NW;
                            break;
                        case "SE":
                            dir = Direction.SE;
                            break;
                        case "SW":
                            dir = Direction.SW;
                            break;
                        default:
                            System.out.println(value + "is not a supporterd direction");
                    }
                    a.setFacingDirection(dir);
                    break;
                case "Name":
                    a.setName(value);
                    break;
                default:
                    System.out.println(attributeName + "is not a supported avatar attribute");
            }
        }
        NodeList avatarObjects = currObject.getChildNodes();
        for(int i = 0; i != avatarObjects.getLength(); ++i){
            Node avatarObject = avatarObjects.item(i);
            if(avatarObject.getNodeType() != Node.ELEMENT_NODE){
                System.out.println("Avatar object isn't an element type");
                continue;
            }
            String objectName = avatarObject.getNodeName();
            switch (objectName){
                case "Location" :
                    Coord c = processLocation(avatarObject);
                    a.setLocation(c);
                    break;
                case "Sneak" :
                    Sneak sn = new Sneak();
                    a.setOccupation(sn);
                    break;
                case "Smasher" :
                    Smasher sm = new Smasher();
                    a.setOccupation(sm);
                    break;
                case "Summoner" :
                    Summoner sr = new Summoner();
                    a.setOccupation(sr);
                    break;
                case "Stats" :
                    Stats s = processStats(avatarObject);
                    a.setStats(s);
                    break;
                case "AbilityManager" :
                    AbilityManager abm = processAbilityManager(avatarObject,a);
                    a.setAbilityManager(abm);
                    break;
                case "BuffManager" :
                    break;
                case "Inventory" :
                    Inventory inv = processInventory(avatarObject);
                    a.setInventory(inv);
                    break;
                case "Equipped" :
                    Equipped eq = processEquipped(avatarObject,a);
                    a.setEquipped(eq);
//                    eq.init(a);
                    break;
                case "SkillManager" :
                    SkillManager sk = processSkillManager(avatarObject);
                    a.setSkillManager(sk);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return a;
    }
    private AbilityManager processAbilityManager(Node avatarObject, Entity e) {
        AbilityManager abm = new AbilityManager(e);
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE){
            NodeList abilityContainerNodes = avatarObject.getChildNodes();
            Element abilitiesElement = (Element) abilityContainerNodes.item(0);
            NodeList allAbilityNodes = abilitiesElement.getChildNodes();
            for(int i = 0; i != allAbilityNodes.getLength(); ++i){
                Element currAbility = (Element)allAbilityNodes.item(i);
                switch (currAbility.getNodeName()){
                    case "BindWoundsAbility" :
                        BindWoundsAbility bwa = new BindWoundsAbility();
                        bwa.setEntity(e);
                        bwa.setSkillManager(e.getSkillManager());
                        abm.addAbility(bwa);
                        break;
                    case "MeleeAttackAbility" :
                        int wind = Integer.valueOf(currAbility.getAttribute("windTicks"));
                        int cool = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                        MeleeAttackAbility maa = new MeleeAttackAbility(e,wind,cool);
                        maa.setStats(e.getStats());
                        abm.addAbility(maa);
                        break;
                    case "FireBallAbility" :
                        int start = Integer.valueOf(currAbility.getAttribute("startUpTicks"));
                        int coolDown = Integer.valueOf(currAbility.getAttribute("coolDownTicks"));
                        int travelDistance = Integer.valueOf(currAbility.getAttribute("travelDistance"));
                        int traveTime = Integer.valueOf(currAbility.getAttribute("travelTime"));
                        int manaCost = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        FireBallAbility fba = new FireBallAbility(e, traveTime, travelDistance, start, coolDown);
                        fba.setManaCost(manaCost);
                        abm.addAbility(fba);
                    default:
                        System.out.println(currAbility.getNodeName() + "isn't a supported ability to load");
                }
            }
            Element activeAbilitiesElement = (Element) abilityContainerNodes.item(1);
            NodeList activeAbilityNodes = activeAbilitiesElement.getChildNodes();
            for(int i = 0; i != activeAbilityNodes.getLength(); ++i){
                Element currAbility = (Element)activeAbilityNodes.item(i);
                Ability ab = abm.getAbility(currAbility.getNodeName());
                if(ab != null){
                    abm.equipAbility(ab, i);
                }
            }
        }
        return abm;
    }
    private SkillManager processSkillManager(Node avatarObject) {
        SkillManager sk = new SkillManager();
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE) {
            Element skElement = (Element) avatarObject;
            sk.setAvailableSkillPoint(Integer.valueOf(skElement.getAttribute("availableSkillPoints")));
            NodeList skillNodes = skElement.getChildNodes();
            if(skillNodes.getLength() == 0){
                System.out.println("no skills in skill manager");
            }
            for(int i = 0; i != skillNodes.getLength(); ++i){
                if(skillNodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Skill skill = new Skill();
                    Element skillElement = (Element) skillNodes.item(i);
                    skill.setName(skillElement.getAttribute("name"));
                    skill.setLevel(Integer.valueOf(skillElement.getAttribute("level")));
                    sk.addSkill(skill);
                }
            }
        }
        return sk;
    }
    private Equipped processEquipped(Node avatarObject, Entity e) {
        Equipped eq = new Equipped();
        eq.init(e);
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE) {
            Element eqElement = (Element) avatarObject;
            if(avatarObject.hasChildNodes()){
                NodeList eqItems = avatarObject.getChildNodes();
                for(int i = 0; i != eqItems.getLength();++i){
                    Node eqItem = eqItems.item(i);
                    if(eqItem.getNodeType() == Node.ELEMENT_NODE){
                        Element eqItemElement = (Element) eqItem;
                        String eqItemName = eqItemElement.getNodeName();
                        switch(eqItemName){
                            case "Hat":
                                Hat h = processHat(eqItemElement);
                                eq.addHat(h);
                                break;
                            case "Knuckle" :
                                //do nothing for knuckle equipped makes its own on creation
//                                Knuckle k = processKnuckle(eqItemElement);
//                                eq.addWeapon(k);
                                break;
                            case "OneHandedWeapon" :
                                OneHandedWeapon ohw = processOneHandedWeapon(eqItemElement);
                                eq.addWeapon(ohw);
                                break;
                            case "TwoHandedWeapon" :
                                TwoHandedWeapon thw = processTwoHandedWeapon(eqItemElement);
                                eq.addWeapon(thw);
                            default:
                                System.out.println(eqItemName + "isn't a supported equipped item element");
                        }
                    }
                    else{
                        System.out.println("the eqItem node isn't an element");
                    }
                }
            }
            else{
                System.out.println("Equipped element has no child nodes- its empty");
            }
        }
        return eq;
    }
    private Inventory processInventory(Node avatarObject) {
        Inventory inv = new Inventory();
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE){
            Element invElement = (Element) avatarObject;
//            inv.setCurrentSize(Integer.valueOf(invElement.getAttribute("currentSize")));
            inv.setMaxSize(Integer.valueOf(invElement.getAttribute("maxSize")));
            if(avatarObject.hasChildNodes()){
                NodeList invItems = avatarObject.getChildNodes();
                for(int i = 0; i != invItems.getLength();++i){
                    Node invItem = invItems.item(i);
                    if(invItem.getNodeType() == Node.ELEMENT_NODE){
                        Element invItemElement = (Element) invItem;
                        String invItemName = invItemElement.getNodeName();
                        switch(invItemName){
                            case "Potion" :
                                Potion p = processPotion(invItemElement);
                                inv.addItem(p);
                                break;
                            case "Hat":
                                Hat h = processHat(invItemElement);
                                inv.addItem(h);
                                break;
                            case "Knuckle" :
                                Knuckle k = processKnuckle(invItemElement);
                                inv.addItem(k);
                                break;
                            case "OneHandedWeapon" :
                                OneHandedWeapon ohw = processOneHandedWeapon(invItemElement);
                                inv.addItem(ohw);
                                break;
                            case "TwoHandedWeapon" :
                                TwoHandedWeapon thw = processTwoHandedWeapon(invItemElement);
                                inv.addItem(thw);
                            default:
                                System.out.println(invItemName + "isn't a supported inventory item element");
                        }
                    }
                    else{
                        System.out.println("the invItem node isn't an element");
                    }
                }
            }
            else{
                System.out.println("Inventory element has no child nodes- its empty");
            }
        }
        else{
            System.out.println("Node passed to processInventory isn't an element");
        }
        return inv;
    }
    private Knuckle processKnuckle(Element invItemElement) {
        Knuckle k = new Knuckle();
        k.setItemName(invItemElement.getAttribute("itemName"));
        k.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        k.setItemStats(processStatsAddable(statsAddNode));
        return k;
    }
    private OneHandedWeapon processOneHandedWeapon(Element invItemElement) {
        OneHandedWeapon ohw = new OneHandedWeapon();
        ohw.setItemName(invItemElement.getAttribute("itemName"));
        ohw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        ohw.setItemStats(processStatsAddable(statsAddNode));
        return ohw;
    }
    private TwoHandedWeapon processTwoHandedWeapon(Element invItemElement) {
        TwoHandedWeapon thw = new TwoHandedWeapon();
        thw.setItemName(invItemElement.getAttribute("itemName"));
        thw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        thw.setItemStats(processStatsAddable(statsAddNode));
        return thw;
    }
    private Hat processHat(Element invItemElement) {
        Hat h = new Hat();
        h.setItemName(invItemElement.getAttribute("itemName"));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        h.setItemStats(processStatsAddable(statsAddNode));
        return h;
    }
    private Potion processPotion(Element invItemElement) {
        Potion p  = new Potion();
        p.setItemName(invItemElement.getAttribute("itemName"));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        StatsAddable sa = processStatsAddable(statsAddNode);
        p.setItemStats(sa);
        return p;
    }
    private StatsAddable processStatsAddable(Node statsAddNode) {
        StatsAddable sa = new GenericStatsAddable();
        if(statsAddNode.getNodeType() == Node.ELEMENT_NODE){
            Element statsAddElement = (Element) statsAddNode;
            sa.setAgility(Integer.valueOf(statsAddElement.getAttribute("agility")));
            sa.setBonusHealth(Integer.valueOf(statsAddElement.getAttribute("bonusHealth")));
            sa.setBonusMana(Integer.valueOf(statsAddElement.getAttribute("bonusMana")));
            sa.setCurrentExperience(Integer.valueOf(statsAddElement.getAttribute("currentExperience")));
            sa.setCurrentHealth(Integer.valueOf(statsAddElement.getAttribute("currentHealth")));
            sa.setCurrentMana(Integer.valueOf(statsAddElement.getAttribute("currentMana")));
            sa.setHardiness(Integer.valueOf(statsAddElement.getAttribute("hardiness")));
            sa.setIntellect(Integer.valueOf(statsAddElement.getAttribute("intellect")));
            sa.setStrength(Integer.valueOf(statsAddElement.getAttribute("strength")));
            sa.setMovement(Integer.valueOf(statsAddElement.getAttribute("movement")));
        }
        return sa;
    }
    private Stats processStats(Node avatarObject) {
        Stats s = new Stats();
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE){
            Element sElement = (Element) avatarObject;
            s.setAgility(Integer.valueOf(sElement.getAttribute("agility")));
            s.setCurrentExperience(Integer.valueOf(sElement.getAttribute("currentExperience")));
            s.setCurrentHealth(Integer.valueOf(sElement.getAttribute("currentHealth")));
            s.setCurrentMana(Integer.valueOf(sElement.getAttribute("currentMana")));
            s.setHardiness(Integer.valueOf(sElement.getAttribute("hardiness")));
            s.setIntellect(Integer.valueOf(sElement.getAttribute("intellect")));
            s.setLevel(Integer.valueOf(sElement.getAttribute("level")));
            s.setMovement(Integer.valueOf(sElement.getAttribute("movement")));
            s.setStrength(Integer.valueOf(sElement.getAttribute("strength")));
            s.calculateStats();
        }
        else{
            System.out.println("Node passed to process processStats isn't an element");
        }
        return s;
    }
    private Coord processLocation(Node object){
        int s;
        int r;
        Coord c = new Coord();
        if(object.getNodeType() == Node.ELEMENT_NODE){
            Element locElement = (Element) object;
            s = Integer.valueOf(locElement.getAttribute("s"));
            r = Integer.valueOf(locElement.getAttribute("r"));
            c.setR(r);
            c.setS(s);
        }
        else{
            System.out.println("node passed to process location wasn't an element");
        }
        return c;
    }
}
