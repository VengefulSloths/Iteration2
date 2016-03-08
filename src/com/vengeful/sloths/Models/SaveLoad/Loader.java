package com.vengeful.sloths.Models.SaveLoad;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.MapArea;
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
        NodeList mapAreas = doc.getChildNodes();
        if(doc.hasChildNodes()){
            for(int i = 0; i != mapAreas.getLength(); ++i){
                Node currMA = mapAreas.item(i);
                if(currMA.getNodeType() == Node.ELEMENT_NODE && currMA.getNodeName().equals("MapArea")){
                    if(currMA.hasChildNodes()){
                        String maName = currMA.getAttributes().item(0).getNodeValue();
                        MapArea loading;
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
            if(avatarObject != NodeType.Element){
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
                    break;
                case "AbilityManager" :
                    break;
                case "BuffManager" :
                    break;
                case "Inventory" :
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

    Coord processLocation(Node object){
        int s;
        int r;
        Coord c = new Coord();
        if(object == NodeType.Element){
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
