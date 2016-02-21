package com.vengeful.sloths.Model.SaveLoad;

import com.vengeful.sloths.Model.Map.Map;
import com.vengeful.sloths.Utility.Coord;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ian on 2/21/2016.
 */
public class SaveVisitor {
    private String fileName;
    private Document doc;

    public SaveVisitor(String fileName) throws ParserConfigurationException {
        this.fileName = fileName;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
    }
    public saveVisitor() throws ParserConfigurationException {
        this.fileName = "save.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
    }

    public void visitMap(Map map){
        //visit Map should be the first thing called so it should also be the root element in the xml
        Element mapElement = doc.createElement("Map");
        doc.appendChild(mapElement);
        MapAreas[] mas = map.getMapAreas();
        for(MapArea ma : mas){
            ma.visit(this, mapElement);
        }
    }

    public void visitMapArea(MapArea ma, Element parent){
        Element maElement = doc.createElement("MapArea");
        parent.appendChild(maElement);
        Tiles[][] tiles= ma.getTiles();
        for(int r = 0; r != ma.maxR; ++r){
            for(int s = 0; s != ma.maxS; ++s){
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

    public void visitEntity(Entity e, Element parent, Coord c){
        Element entityElement = doc.createElement("Entity");
        parent.appendChild(entityElement);
        entityElement.setAttribute("Name", e.getName());
        /* ALL OTHER ENTITY ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        //inv/equipped visit, stats visit, occupation visit, etc...

        appendCoordElement(entityElement, c);
    }

    public void visitMapItem(MapItem mi, Element parent, Coord c){
        Element miElement = doc.createElement("MapItem");
        parent.appendChild(miElement);
        miElement.setAttribute("Name", mi.getName());
        /* ALL OTHER MapItem ATTRIBUTES/AGGREGATE OBJECTS ASSOCIATED WITH IT*/
        appendCoordElement(miElement, c);
    }

    public void appendCoordElement(Element parent, Coord c){
        Element coordElement = doc.createElement("Coord");
        parent.appendChild(coordElement);
        coordElement.setAttribute("s", c.getS());
        coordElement.setAttribute("r", c.getR());
    }
}
