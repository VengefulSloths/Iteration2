package com.vengeful.sloths.Model.Map;

import com.vengeful.sloths.Utility.Coord;

/**
 * Created by John on 2/21/2016.
 */
public class Map {
    private int maxX;
    private int maxY;
    private Tile[][] tiles;
    private Coord respawnPoint;

    public Map(Coord maxCoords) {

    }

    public void addTile(Coord coord, Tile t) {
        tiles[coord.getR()][coord.getS()] = t;
    }

    public Tile getTile(Coord coord) throws IndexOutOfBoundsException{
        //System.out.Println(coord);
        if (coord.getR() < 0 ||
                coord.getS() < 0 ||
                coord.getR() >= maxX ||
                coord.getS() >= maxY) {
            throw new IndexOutOfBoundsException();
        }
        Tile tile = tiles[coord.getR()][coord.getS()];
        return tile;
    }

    public void setRespawnPoint(Coord coord){
        this.respawnPoint = coord;
    }
    public Coord getRespawnPoint(){
        return this.respawnPoint;
    }

    public Tile[][] getTiles()
    {
        return tiles;
    }
}
