package com.vengeful.sloths.Models.SaveLoad;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.InternalError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.Map.MapArea;
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
                    break;
                case "Smasher" :
                    break;
                case "Summoner" :
                    break;
                case "Stats" :
                    Stats s = processStats(avatarObject);
                    a.setStats(s);
                    break;
                case "AbilityManager" :
                    break;
                case "BuffManager" :
                    break;
                case "Inventory" :
                    Inventory inv = processInventory(avatarObject);
                    break;
                case "Equipped" :
                    break;
                case "SkillManager" :
                    break;
                default:
                    System.out.println(objectName + "is not a supported avatar object");
            }
        }
        return a;
    }

    private Inventory processInventory(Node avatarObject) {
        Inventory inv = new Inventory();
        if(avatarObject.getNodeType() == Node.ELEMENT_NODE){
            Element invElement = (Element) avatarObject;
            inv.setCurrentSize(Integer.valueOf(invElement.getAttribute("currentSize")));
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
            sa.setBonusMana(Integer.valueOf(statsAddElement.getAttribute("bounusMana")));
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
