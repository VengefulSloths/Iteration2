package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Models.Map.Map;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ian on 2/21/2016.
 * This class simply starts the saveVisitor off at the Map
 * It requires a map object right now, but could easily be changed so map is just passed into the save methods
 */
public class SaveManager {
    /**
     * Private variables: Map
     */
    private Map map;

    /**
     * Constructors including the default constructor
     */
    public SaveManager(){

    }
    public SaveManager(Map map){
        this.map = map;
    }

    /**
     *There are only two non-getter/setter methods
     * both called save, the one that takes a string specifies the savefile name and the other will call the save file "save.xml"
     * they both create a save visitor then call visit on map

     */
    public void save(String fileName){
        SaveVisitor sv = null;
        try {
            sv = new SaveVisitor(fileName);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        map.visit(sv);
    }
    public void save(Map map){
        SaveVisitor sv = null;
        try {
            sv = new SaveVisitor();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        map.visit(sv);
    }
    /**
     * Getter/Setters
     */
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
