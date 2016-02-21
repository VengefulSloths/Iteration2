package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Visitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ian on 2/21/2016.
 * This class extends teh abstract visitor class(defined in utilities)
 * note that the visit calls defined here take parameters and requires that the visited objects have visits that take parameters as well
 * this means many visited objects will have 2 visit methods
 * these parameters are for properly formatting the save file
 * THIS CLASS IS CURRENTLY UNTESTED
 */
public class SaveVisitor extends Visitor {
    /**
     * Private variables
     * These don't have getter/setters, will only be changed via the constructor
     */
    private String fileName;
    private Document doc;

    /**
     * Constructors including the default constructor
     * note they both throw a ParserConfigException
     */
    public SaveVisitor(String fileName) throws ParserConfigurationException {
        this.fileName = fileName;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
    }
    public SaveVisitor() throws ParserConfigurationException {
        this.fileName = "save.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
    }

    /**
     *The visit functions
     * becuase this visitor is for making a save file the visits have parameters to aid in creating it correctly
     * the only standard visit function is map because it is saved as the root element of the xml
     */
    public void visitMap(Map map){
        //visit Map should be the first thing called so it should also be the root element in the xml
        Element mapElement = doc.createElement("Map");
        doc.appendChild(mapElement);
        MapArea[] mas = map.getMapAreas();
        for(MapArea ma : mas){
            ma.visit(this, mapElement);
        }
    }

    public void visitMapArea(MapArea ma, Element parent){
        Element maElement = doc.createElement("MapArea");
        parent.appendChild(maElement);
        Tile[][] tiles= ma.getTiles();
        for(int r = 0; r != ma.getMaxR(); ++r){
            for(int s = 0; s != ma.getMaxS(); ++s){
                Coord c = new Coord(r,s);
                tiles[r][s].visit(this, parent, c);
            }
        }
    }

    public void visitTile(Tile t, Element parent, Coord c){
        Entity e = t.getEntity();
        Entity[] nonCollide = t.getNonCollideableEntities();
        MapItem[] mapItems = t.getMapItems();
        e.visit(this, parent, c);
        for(Entity nonColE : nonCollide){
            nonColE.visit(this, parent, c);
        }
        for(MapItem ma : mapItems){
            ma.visit(this, parent, c);
        }
    }

    /**
     *right now this handles visiting avatar as well
     * will probably need to add a specific visit avatar
     */
    public void visitEntity(Entity e, Element parent, Coord c){
        Element entityElement = doc.createElement("Entity");
        parent.appendChild(entityElement);
        String name = e.getName();
        if(name.equals(null)){
            name = "nameNotSet";
        }
        entityElement.setAttribute("Name", name);
        /* ALL OTHER ENTITY ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        //inv/equipped visit, stats visit, occupation visit, etc...

        appendCoordElement(entityElement, c);
    }

    /**
     *This will probably be replaced by a visit for each type of map item
     */
    public void visitMapItem(MapItem mi, Element parent, Coord c){
        Element miElement = doc.createElement("MapItem");
        parent.appendChild(miElement);
        miElement.setAttribute("Name", mi.getItemName());
        /* ALL OTHER MapItem ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        appendCoordElement(miElement, c);
    }

    /**
     * This appends a coord element to each element that would appear on a tile
     * When loading note that none of them except avatar hold their own coordinates
     * its used to place the object back onto the correct tile
     */
    public void appendCoordElement(Element parent, Coord c){
        Element coordElement = doc.createElement("Coord");
        parent.appendChild(coordElement);
        coordElement.setAttribute("s", "" + c.getS());
        coordElement.setAttribute("r", "" + c.getR());
    }
}
