package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Utility.Coord;
import org.w3c.dom.Element;

/**
 * Created by Ian on 2/21/2016.
 * This holds all the tile holding and accessing logic
 * Has int maxR, maxS, and Tile[][] tiles
 * Access to a coord with a value >= maxR | maxS throws an error
 */
public class MapArea implements ModelVisitable{
    /**
     * All private variables
     */
    private int maxR;
    private int maxS;
    private Tile[][] tiles;
    private String name;

    /**
     * Contructors including default
     */
    public MapArea(){

    }

    public MapArea(Tile[][] tiles){
        this.tiles = tiles;
        this.maxR = tiles.length-1;
        this.maxS = tiles[0].length-1;
    }

    public MapArea(Tile[][] tiles, String name){
        this.tiles = tiles;
        this.maxR = tiles.length-1;
        this.maxS = tiles[0].length-1;
        this.name = name;
    }

    public MapArea(Tile[][] tiles, int maxR, int maxS, String name){
        this.maxR = maxR;
        this.maxS = maxS;
        this.tiles = tiles;
        this.name = name;
    }

    public MapArea(Tile[][] tiles, int maxR, int maxS){
        this.maxR = maxR;
        this.maxS = maxS;
        this.tiles = tiles;
    }

    public MapArea(int maxR, int maxS){
        this.maxS = maxS;
        this.maxR = maxR;
        this.tiles = new Tile[maxR][maxS];
    }


    /**
     * Getter and Setter for single tiles
     * both of these accept a Coord object, and can throw an OutOfBoundsException
     */

    public void addTile(Coord coord, Tile t) throws IndexOutOfBoundsException {
        if (coord.getR() < 0 ||
                coord.getS() < 0 ||
                coord.getR() >= maxR ||
                coord.getS() >= maxS) {
            throw new IndexOutOfBoundsException();
        }
        tiles[coord.getR()][coord.getS()] = t;
    }

    public Tile getTile(Coord coord) throws IndexOutOfBoundsException{
        //System.out.Println(coord);
        if (coord.getR() < 0 ||
                coord.getS() < 0 ||
                coord.getR() >= maxR ||
                coord.getS() >= maxS) {
            throw new IndexOutOfBoundsException();
        }
        Tile tile = tiles[coord.getR()][coord.getS()];
        return tile;
    }

    /**
     *Standard Getter/Setters for: maxS, maxR, tiles
     */

    public Tile[][] getTiles()
    {
        return tiles;
    }

    public void setTiles(Tile[][] tiles){
        this.tiles = tiles;

    }

    public int getMaxR() {
        return maxR;
    }

    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getMaxS() {
        return maxS;
    }

    public void setMaxS(int maxS) {
        this.maxS = maxS;
    }

    public void accept(ModelVisitor v) {
        v.visitMapArea(this);
    }
}
