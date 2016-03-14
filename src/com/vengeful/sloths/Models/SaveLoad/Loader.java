package com.vengeful.sloths.Models.SaveLoad;

import com.sun.org.apache.regexp.internal.RE;
import com.vengeful.sloths.Controllers.ControllerManagers.AggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.NonAggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.PiggyControllerManager;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SelfBuffAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.*;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.DialogueTrade.DialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TerminalDialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TradeDialogContainer;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.PiggyTotem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.Quest;
import com.vengeful.sloths.Models.Map.Tile;
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
        NodeList mapAreas = root.getChildNodes().item(0).getChildNodes();
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
                                    MainController.getInstance().setInputStrategy(processInputStrategy((Element)root.getChildNodes().item(1)));
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
                                case "NonAggressiveNPC" :
                                    NonAggressiveNPC n = processNonAggressiveNPC(currObject);
                                    NonAggressiveNPCControllerManager non = new NonAggressiveNPCControllerManager(loading,n,n.getFacingDirection(),1);
                                    loading.getTile(n.getLocation()).addEntity(n);
                                    break;
                                case "AggressiveNPC" :
                                    AggressiveNPC aNPC = processAggressiveNPC(currObject);
                                    AggressiveNPCControllerManager ancm = new AggressiveNPCControllerManager(loading, aNPC);
                                    loading.getTile(aNPC.getLocation()).addEntity(aNPC);
                                    break;
                                case "InteractiveItem" :
                                    InteractiveItem ii = processInteractiveItem(currObject);
                                    loading.getTile(ii.getLocation()).addInteractiveItem(ii);
                                    break;
                                case "Gold" :
                                    Gold g = processGold(currObject);
                                    loading.getTile(g.getLocation()).addGold(g);
                                    break;
                                case "Trap" :
                                    Trap trap = processsTrap(currObject);
                                    loading.getTile(trap.getLocation()).addTrap(trap);
                                    break;
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
        setActiveMapArea();
    }

    //MAP ITEMS
    private InteractiveItem processInteractiveItem(Node currObject) {
        InteractiveItem ii = new InteractiveItem();
        Element current = (Element) currObject;
        ii.setItemName(current.getAttribute("itemName"));
        ii.setLocation(processLocation(currObject.getChildNodes().item(0)));
        ii.setQuest(processQuest(current.getChildNodes().item(1)));
        return ii;
    }
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
            case "Knuckle" :
                Knuckle k = processKnuckle(invItemElement);
                ti.setInventorpRep(k);
                break;
            case "OneHandedWeapon" :
                OneHandedWeapon ohw = processOneHandedWeapon(invItemElement);
                ti.setInventorpRep(ohw);
                break;
            case "TwoHandedWeapon" :
                TwoHandedWeapon thw = processTwoHandedWeapon(invItemElement);
                ti.setInventorpRep(thw);
                break;
            case "Staff" :
                Staff staff = processStaff(invItemElement);
                ti.setInventorpRep(staff);
                break;
            case "PiggyTotem" :
                Piggy piggy = processPiggy(invItemElement.getFirstChild());
                PiggyTotem pt = new PiggyTotem(invItemElement.getAttribute("itemName"), piggy);
                ti.setInventorpRep(pt);
                break;
            case "Bow" :
                Bow b = processBow(invItemElement);
                ti.setInventorpRep(b);
                break;
            case "AbilityItem" :
                AbilityItem ab= processAbilityItem(invItemElement);
                ti.setInventorpRep(ab);
                break;
            default:
                System.out.println(invItemName + "isn't a supported inventoryItem representation");
        }
        return ti;
    }
    private Trap processsTrap(Node currObject) {
        Element currElement = (Element) currObject;
        int damageTaken = Integer.valueOf(currElement.getAttribute("damageTaken"));
        boolean isVisible;
        if(currElement.getAttribute("isVisible").equals("true")){
            isVisible = true;
        }
        else {
            isVisible = false;
        }
        String itemName = currElement.getAttribute("itemName");
        Coord c = processLocation(currElement.getFirstChild());
        Trap t = new Trap(c, damageTaken);
        t.setItemName(itemName);
        t.setVisible(isVisible);
        return t;
    }
    private Gold processGold(Node currObject) {
        Gold g = new Gold();
        Element gElement = (Element) currObject;
        g.setItemName(gElement.getAttribute("itemName"));
        g.setValue(Integer.valueOf(gElement.getAttribute("value")));
        g.setLocation(processLocation(gElement.getFirstChild()));
        return g;
    }
//ENTITYS
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
                case "TradeDialogueContainer" :
                    TradeDialogContainer tradeDC = processTradeDialogContainer(avatarObject, a);
                    a.setDialogContainer(tradeDC);
                    break;
                case "TerminalDialogContainer" :
                    TerminalDialogContainer terminalDC = processTerminalDialogContainer(avatarObject , a);
                    a.setDialogContainer(terminalDC);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return a;
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
                    p.setBuffManager(new BuffManager(p));
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
                case "TradeDialogueContainer" :
                    TradeDialogContainer tradeDC = processTradeDialogContainer(avatarObject, p);
                    p.setDialogContainer(tradeDC);
                    break;
                case "TerminalDialogContainer" :
                    TerminalDialogContainer terminalDC = processTerminalDialogContainer(avatarObject , p);
                    p.setDialogContainer(terminalDC);
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
                    p.setBuffManager(new BuffManager(p));
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
                case "TradeDialogueContainer" :
                    TradeDialogContainer tradeDC = processTradeDialogContainer(avatarObject, p);
                    p.setDialogContainer(tradeDC);
                    break;
                case "TerminalDialogContainer" :
                    TerminalDialogContainer terminalDC = processTerminalDialogContainer(avatarObject , p);
                    p.setDialogContainer(terminalDC);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return p;
    }
    private NonAggressiveNPC processNonAggressiveNPC(Node currObject) {
        NonAggressiveNPC p = new NonAggressiveNPC();
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
                        case "NW":
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
                    p.setBuffManager(new BuffManager(p));
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
                case "TradeDialogueContainer" :
                    TradeDialogContainer tradeDC = processTradeDialogContainer(avatarObject, p);
                    p.setDialogContainer(tradeDC);
                    break;
                case "TerminalDialogContainer" :
                    TerminalDialogContainer terminalDC = processTerminalDialogContainer(avatarObject , p);
                    p.setDialogContainer(terminalDC);
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return p;
    }
//DIALOG
    private TerminalDialogContainer processTerminalDialogContainer(Node avatarObject, Entity e) {
        TerminalDialogContainer tdc = new TerminalDialogContainer(e.getName());
        Element objectElement = (Element) avatarObject;
        NamedNodeMap dialogLines = objectElement.getAttributes();
        for(int i  = 0; i != dialogLines.getLength(); ++i){
            tdc.appendDialog(dialogLines.item(i).getNodeValue());
        }
        return tdc;
    }
    private TradeDialogContainer processTradeDialogContainer(Node avatarObject,Entity e) {
        TradeDialogContainer tdc = new TradeDialogContainer(e);
        Element objectElement = (Element) avatarObject;
        NamedNodeMap dialogLines = objectElement.getAttributes();
        for(int i  = 0; i != dialogLines.getLength(); ++i){
            tdc.appendDialog(dialogLines.item(i).getNodeValue());
        }

        return tdc;
    }
//ENTITY SKILL / ABILITY MANAGERS
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
                        BindWoundsAbility bwa = AbilityFactory.getInstance().createBindWoundsAbility(Avatar.getInstance());
                        bwa.setEntity(e);
                        bwa.setSkillManager(e.getSkillManager());
                        abm.addAbility(bwa);
                        break;
//                    case "MeleeAttackAbility" :
//                        int wind = Integer.valueOf(currAbility.getAttribute("windTicks"));
//                        int cool = Integer.valueOf(currAbility.getAttribute("coolTicks"));
//                        MeleeAttackAbility maa = new MeleeAttackAbility(e,wind,cool);
//                        maa.setStats(e.getStats());
//                        abm.addAbility(maa);
//                        break;
                    case "FireBallAbility" :
                        int start = Integer.valueOf(currAbility.getAttribute("startUpTicks"));
                        int coolDown = Integer.valueOf(currAbility.getAttribute("coolDownTicks"));
                        int travelDistance = Integer.valueOf(currAbility.getAttribute("travelDistance"));
                        int traveTime = Integer.valueOf(currAbility.getAttribute("travelTime"));
                        int manaCost = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        FireBallAbility fba = new FireBallAbility(e, traveTime, travelDistance, start, coolDown);
                        fba.setManaCost(manaCost);
                        abm.addAbility(fba);
                        break;
                    case "ExplosionAbility":
                        int windExA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                        int coolExA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                        int expandingTime = Integer.valueOf(currAbility.getAttribute("expandingTime"));
                        int expandingDistance = Integer.valueOf(currAbility.getAttribute("expandingDistance"));
                        int manaCostExA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        ExplosionAbility exA = new ExplosionAbility(e,expandingTime,expandingDistance,windExA,coolExA);
                        exA.setManaCost(manaCostExA);
                        abm.addAbility(exA);
                        break;
                    case "FlameThrowerAbility" :
                        int windASA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                        int coolASA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                        int expandingTimeASA = Integer.valueOf(currAbility.getAttribute("expandingTime"));
                        int expandingDistanceASA = Integer.valueOf(currAbility.getAttribute("expandingDistance"));
                        int manaCostASA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        FlameThrowerAbility asa = new FlameThrowerAbility(e,expandingTimeASA,expandingDistanceASA,windASA,coolASA);
                        asa.setManaCost(manaCostASA);
                        abm.addAbility(asa);
                        break;
                    case "RemoveTrapAbility" :
                        int windRTA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                        int coolRTA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                        int manaCostRTA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        RemoveTrapAbility rta = new RemoveTrapAbility(e, windRTA, coolRTA);
                        rta.setManaCost(manaCostRTA);
                        abm.addAbility(rta);
                        break;
                    case "StealthAbility" :
                        StealthAbility sa = AbilityFactory.getInstance().createStealthAbility(e);
                        abm.addAbility(sa);
                        break;
                    case "PickPocket" :
                        PickPocketAbility ppa = new PickPocketAbility();
                        abm.addAbility(ppa);
                    case "Protect From Evil" :
                        BoonBuffAbility protect = (BoonBuffAbility) AbilityFactory.getInstance().createProtectFromEvil(e);
                        abm.addAbility(protect);
                        break;
                    case "Roids" :
                        BoonBuffAbility roids= (BoonBuffAbility) AbilityFactory.getInstance().createDamageBoost(e);
                        abm.addAbility(roids);
                        break;
                    case "Rejuvenation":
                        BoonBuffAbility Rejuv = (BoonBuffAbility) AbilityFactory.getInstance().createHealOverTime(e);
                        abm.addAbility(Rejuv);
                        break;
                    case "NPCFallAsleepAbility" :
                        int windFAA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                        int coolFAA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                        int manaCostFAA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                        int sleepTime = Integer.valueOf(currAbility.getAttribute("sleepTime"));
                        NPCFallAsleepAbility nfaa = AbilityFactory.getInstance().createNPCFallAsleepAbility(e);
                        abm.addAbility(nfaa);
                        break;
                    case "PoisonNPCAbility" :
                        abm.addAbility(AbilityFactory.getInstance().createPoisonNPCAbility(e));
                        break;
                    case "WeakenNPCAbility" :
                        abm.addAbility(AbilityFactory.getInstance().createWeakenNPCAbility(e));
                        break;
                    case "MountAbility" :
                        break;
                    case "DemountAbility" :
                        break;
                    case "NullAbility" :
                        break;
                    case "RemoveBuffAbility" :
                        break;
                    case "SelfBuffAbility" :
                        break;
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
//INVENTORY AND INVENTORY SPECIFIC ITEMS
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
                                break;
                            case "Bow" :
                                Bow b = processBow(invItemElement);
                                inv.addItem(b);
                                break;
                            case "Staff" :
                                Staff s  = processStaff(invItemElement);
                                inv.addItem(s);
                                break;
                            case "Mount":
                                Mount m = processMount(invItemElement);
                                inv.addItem(m);
                                break;
                            case "PiggyTotem" :
                                Piggy piggy = processPiggy(invItemElement.getFirstChild());
                                PiggyTotem pt = new PiggyTotem(invItemElement.getAttribute("itemName"), piggy);
                                inv.addItem(pt);
                                break;
                            case "AbilityItem" :
                                AbilityItem ab= processAbilityItem(invItemElement);
                                inv.addItem(ab);
                                break;
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
    private Potion processPotion(Element invItemElement) {
        Potion p  = new Potion();
        p.setItemName(invItemElement.getAttribute("itemName"));
        p.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        StatsAddable sa = processStatsAddable(statsAddNode);
        p.setItemStats(sa);
        return p;
    }
    private AbilityItem processAbilityItem(Element invItemElement) {
        String itemName = invItemElement.getAttribute("itemName");
        int value = Integer.valueOf(invItemElement.getAttribute("value"));
        Element currAbility = (Element) invItemElement.getFirstChild();
        Ability ab = null;
        Avatar a = Avatar.getInstance();
        switch (currAbility.getNodeName()){
            case "BindWoundsAbility" :
                BindWoundsAbility bwa = AbilityFactory.getInstance().createBindWoundsAbility(Avatar.getInstance());
                bwa.setEntity(a);
                bwa.setSkillManager(a.getSkillManager());
                ab = bwa;
                break;
//                    case "MeleeAttackAbility" :
//                        int wind = Integer.valueOf(currAbility.getAttribute("windTicks"));
//                        int cool = Integer.valueOf(currAbility.getAttribute("coolTicks"));
//                        MeleeAttackAbility maa = new MeleeAttackAbility(e,wind,cool);
//                        maa.setStats(e.getStats());
//                        abm.addAbility(maa);
//                        break;
            case "FireBallAbility" :
                int start = Integer.valueOf(currAbility.getAttribute("startUpTicks"));
                int coolDown = Integer.valueOf(currAbility.getAttribute("coolDownTicks"));
                int travelDistance = Integer.valueOf(currAbility.getAttribute("travelDistance"));
                int traveTime = Integer.valueOf(currAbility.getAttribute("travelTime"));
                int manaCost = Integer.valueOf(currAbility.getAttribute("manaCost"));
                FireBallAbility fba = new FireBallAbility(a, traveTime, travelDistance, start, coolDown);
                fba.setManaCost(manaCost);
                ab = fba;
                break;
            case "ExplosionAbility":
                int windExA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                int coolExA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                int expandingTime = Integer.valueOf(currAbility.getAttribute("expandingTime"));
                int expandingDistance = Integer.valueOf(currAbility.getAttribute("expandingDistance"));
                int manaCostExA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                ExplosionAbility exA = new ExplosionAbility(a,expandingTime,expandingDistance,windExA,coolExA);
                exA.setManaCost(manaCostExA);
                ab = exA;
                break;
            case "FlameThrowerAbility" :
                int windASA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                int coolASA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                int expandingTimeASA = Integer.valueOf(currAbility.getAttribute("expandingTime"));
                int expandingDistanceASA = Integer.valueOf(currAbility.getAttribute("expandingDistance"));
                int manaCostASA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                FlameThrowerAbility asa = new FlameThrowerAbility(a,expandingTimeASA,expandingDistanceASA,windASA,coolASA);
                asa.setManaCost(manaCostASA);
                ab = asa;
                break;
            case "RemoveTrapAbility" :
                int windRTA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                int coolRTA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                int manaCostRTA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                RemoveTrapAbility rta = new RemoveTrapAbility(a, windRTA, coolRTA);
                rta.setManaCost(manaCostRTA);
                ab = rta;
                break;
            case "StealthAbility" :
                StealthAbility sa = AbilityFactory.getInstance().createStealthAbility(a);
                ab = sa;
                break;
            case "PickPocket" :
                PickPocketAbility ppa = new PickPocketAbility();
                ab = ppa;
            case "Protect From Evil" :
                BoonBuffAbility protect = (BoonBuffAbility) AbilityFactory.getInstance().createProtectFromEvil(a);
                ab = protect;
                break;
            case "damageBoost":
            case "Roids" :
                BoonBuffAbility roids= (BoonBuffAbility) AbilityFactory.getInstance().createDamageBoost(a);
                ab = roids;
                break;
            case "Rejuvenation":
                BoonBuffAbility rejuv = (BoonBuffAbility) AbilityFactory.getInstance().createHealOverTime(a);
                ab = rejuv;
                break;
            case "NPCFallAsleepAbility" :
                int windFAA = Integer.valueOf(currAbility.getAttribute("windTicks"));
                int coolFAA = Integer.valueOf(currAbility.getAttribute("coolTicks"));
                int manaCostFAA = Integer.valueOf(currAbility.getAttribute("manaCost"));
                int sleepTime = Integer.valueOf(currAbility.getAttribute("sleepTime"));
                NPCFallAsleepAbility nfaa = AbilityFactory.getInstance().createNPCFallAsleepAbility(a);
                ab = nfaa;
                break;
            case "PoisonNPCAbility" :
                ab = (AbilityFactory.getInstance().createPoisonNPCAbility(a));
                break;
            case "WeakenNPCAbility" :
                ab = (AbilityFactory.getInstance().createWeakenNPCAbility(a));
                break;
            case "MountAbility" :
                break;
            case "DemountAbility" :
                break;
            case "NullAbility" :
                break;
            case "RemoveBuffAbility" :
                break;
            case "SelfBuffAbility" :
                break;
            default:
                System.out.println(currAbility.getNodeName() + "isn't a supported ability to load");
        }
        AbilityItem abItem = new AbilityItem(ab);
        abItem.setValue(value);
        abItem.setItemName(itemName);
        return abItem;
    }
//EQUIPPED AND ITEMS
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
                                Knuckle k = processKnuckle(eqItemElement);
                                eq.addWeapon(k);
                                break;
                            case "OneHandedWeapon" :
                                OneHandedWeapon ohw = processOneHandedWeapon(eqItemElement);
                                eq.addWeapon(ohw);
                                break;
                            case "TwoHandedWeapon" :
                                TwoHandedWeapon thw = processTwoHandedWeapon(eqItemElement);
                                eq.addWeapon(thw);
                                break;
                            case "Mount":
                                Mount m = processMount(eqItemElement);
                                eq.addMount(m);
                                break;
                            case "Bow" :
                                Bow b = processBow(eqItemElement);
                                eq.addWeapon(b);
                                break;
                            case "Staff" :
                                Staff s = processStaff(eqItemElement);
                                eq.addWeapon(s);
                                break;
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
    private Bow processBow(Element invItemElement) {
        Bow thw = new Bow();
        thw.setItemName(invItemElement.getAttribute("itemName"));
        thw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        thw.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        thw.setItemStats(processStatsAddable(statsAddNode));
        return thw;
    }
    private Staff processStaff(Element invItemElement){
        Staff thw = new Staff();
        thw.setItemName(invItemElement.getAttribute("itemName"));
        thw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        thw.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        thw.setItemStats(processStatsAddable(statsAddNode));
        return thw;
    }
    private Hat processHat(Element invItemElement) {
        Hat h = new Hat();
        h.setItemName(invItemElement.getAttribute("itemName"));
        h.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        h.setItemStats(processStatsAddable(statsAddNode));
        return h;
    }
    private Knuckle processKnuckle(Element invItemElement) {
        Knuckle k = new Knuckle();
        k.setItemName(invItemElement.getAttribute("itemName"));
        k.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        k.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        k.setItemStats(processStatsAddable(statsAddNode));
        return k;
    }
    private OneHandedWeapon processOneHandedWeapon(Element invItemElement) {
        OneHandedWeapon ohw = new OneHandedWeapon();
        ohw.setItemName(invItemElement.getAttribute("itemName"));
        ohw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        ohw.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        ohw.setItemStats(processStatsAddable(statsAddNode));
        return ohw;
    }
    private TwoHandedWeapon processTwoHandedWeapon(Element invItemElement) {
        TwoHandedWeapon thw = new TwoHandedWeapon();
        thw.setItemName(invItemElement.getAttribute("itemName"));
        thw.setBaseDamage(Integer.valueOf(invItemElement.getAttribute("baseDamage")));
        thw.setValue(Integer.valueOf(invItemElement.getAttribute("value")));
        Node statsAddNode = invItemElement.getChildNodes().item(0);
        thw.setItemStats(processStatsAddable(statsAddNode));
        return thw;
    }
    private Mount processMount(Element eqItemElement) {
        int move = Integer.valueOf(eqItemElement.getAttribute("moveSpeed"));
        String name = eqItemElement.getAttribute("name");
        int value = Integer.valueOf(eqItemElement.getAttribute("value"));
        Mount m = new Mount(name, move);
        return m;
    }
//OTHER
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
    private AdaptableStrategy processInputStrategy(Element item) {
        boolean createFromLoad = true;
        AdaptableStrategy as = new AdaptableStrategy(createFromLoad);
//        HashMap<Integer, KeyMapping> hs= as.getKeyMappings();
        NodeList entryElements = item.getChildNodes();
        for(int i =0; i != entryElements.getLength(); ++i){
            Element entryElement = (Element)(entryElements.item(i));
            int key = Integer.valueOf(entryElement.getAttribute("key"));
            KeyMapping value = KeyMapping.fromInt(Integer.valueOf(entryElement.getAttribute("value")));
            as.setKeyMappings(key,value);
        }
        return as;
    }
    //DANK QUADRA-FOR-LOOP TRIANGLE FUNCTION
    private void setActiveMapArea() {
        Map m = Map.getInstance();
        MapArea[] mas = m.getMapAreas();
        Avatar a = Avatar.getInstance();
        for(MapArea ma : mas){
            for(Tile[] tiles : ma.getTiles()){
                for(Tile tile : tiles){
                    if(tile != null){
                        Entity[] es = tile.getEntities();
                        if(es.length != 0){
                            for(Entity e : es){
                                if(e.equals(a)){
                                    m.setActiveMapArea(ma);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
