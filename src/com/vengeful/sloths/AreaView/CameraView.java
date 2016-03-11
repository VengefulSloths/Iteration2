package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.*;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */
public abstract class CameraView implements MovingVOObserver{
    private TileViewObject[][] tiles;
    private int maxX;
    private int maxY;

    private int maxR;
    private int maxS;

    private ParallaxBackground parallaxBackground;
    private ViewObjectFactory factory;

    private MovingViewObject avatar;
    private MovingViewObject piggy;

    private boolean dontMoveAvatarFlag = true;

    public CameraView(ViewObjectFactory factory) {
        this.factory = factory;
    }

    public void addViewObject(ViewObject vo) {
        int     x = findX(vo.getR(), vo.getS()),
                y = findY(vo.getR(), vo.getS());
        tiles[x][y].addChild(vo);
    }

    public void init(MapArea mapArea) {
        PersistentVOCreationVisitor creator = new PersistentVOCreationVisitor(factory);

        maxR = mapArea.getMaxR();
        maxS = mapArea.getMaxS();
        maxX = findX(mapArea.getMaxR(), mapArea.getMaxS());
        maxY = findY(mapArea.getMaxR(), mapArea.getMaxS());


        //Populate tiles array with empty tiles to avoid null pointer garbage
        tiles = new TileViewObject[maxX][maxY];
        for (int i=0; i<maxX; i++) {
            for (int j=0; j<maxY; j++) {
                if ((j-i)%2 == 0) {
                    tiles[i][j] = factory.createNullTileViewObject(
                            findR(i, j),
                            findS(i, j)
                    );
                }
            }
        }

        //This will populate creator with all the tiles from hte map area
        creator.visitMapArea(mapArea);

        //add all the tiles correctly to the array
        Iterator<TileViewObject> iter = creator.getTiles();
        while (iter.hasNext()) {
            TileViewObject current = iter.next();
            int i = findX(current.getR(), current.getS());
            int j = findY(current.getR(), current.getS());
            tiles[i][j] = current;
        }
    }

    public void addAvatar(AvatarViewObject avo) {
        this.dontMoveAvatarFlag = false;
        addViewObject(avo);
        this.avatar = avo;
        parallaxBackground = new ParallaxBackground("resources/backgrounds/sky.xml", avo);
        for (int i=0; i<maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                //make everything either unknown or nonvisible
            }
        }
        Iterator<Coord> toReveal = HexMath.saftey(HexMath.hexagon(new Coord(avo.getR(), avo.getS()), 5), maxR, maxS);
        while (toReveal.hasNext()) {
            Coord current = toReveal.next();
            int r = current.getR();
            int s = current.getS();
            tiles[findX(r, s)][findY(r,s)].setVisibility(Visibility.VISIBLE);
        }
    }

    public void addPiggy(PiggyViewObject pvo) {
        addViewObject(pvo);
        this.piggy = pvo;
    }

    public void paintComponent(Graphics2D g) {
        parallaxBackground.paintComponent(g);
        for (int i=0; i<maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (tiles[j][i] != null) {
                    tiles[j][i].paintComponent(g);

                }
            }
        }
    }

    private int findX(int r, int s) {
        return r;
    }
    private int findY(int r, int s) {
        return 2*s+r;
    }
    private int findR(int x, int y) {
        return x;
    }
    private int findS(int x, int y) {
        return (y-x)/2;
    }

    @Override
    public void alertMove(int srcR, int srcS, int destR, int destS, long duration, MovingViewObject subject) {
        int     srcX = findX(srcR,srcS),
                srcY = findY(srcR,srcS),
                destX = findX(destR,destS),
                destY = findY(destR,destS);

        System.out.println("View: Going from (" + srcR +", " + srcS + ") to (" + destR + ", " + destS + ")");
        if (destY > srcY) {
            tiles[srcX][srcY].reallyRemoveChild(subject);
            tiles[destX][destY].addChild(subject);
        } else {
            ViewTime.getInstance().registerAlert(duration,
                    ()-> {

                            tiles[srcX][srcY].removeChild(subject);
                            if (!dontMoveAvatarFlag) {
                                tiles[destX][destY].addChild(subject);
                            }
                        }
                    );
        }
        if (subject == this.avatar) {
            Iterator<Coord> toBeRevealed = HexMath.saftey(HexMath.ring(new Coord(destR, destS), 5), maxR, maxS);
            while(toBeRevealed.hasNext()) {
                Coord current = toBeRevealed.next();
                int r = current.getR();
                int s = current.getS();
                tiles[findX(r, s)][findY(r,s)].setVisibility(Visibility.VISIBLE);
            }
            Iterator<Coord> toBeConcealed = HexMath.saftey(HexMath.ring(new Coord(destR, destS), 6), maxR, maxS);
            while(toBeConcealed.hasNext()) {
                Coord current = toBeConcealed.next();
                int r = current.getR();
                int s = current.getS();
                tiles[findX(r, s)][findY(r,s)].setVisibility(Visibility.NONVISIBLE);
            }
        }
    }
    public void cleanUp() {
//        for (int i=0; i<maxX; i++) {
//            for (int j = 0; j < maxY; j++) {
//                if (tiles[i][j] != null) {
//                    tiles[i][j].removeChild(avatar);
//                    tiles[i][j].removeChild(piggy);
//                }
//            }
//        }
        this.dontMoveAvatarFlag = true;
//        this.avatar.deregisterObserver(this);

        //this.piggy.deregisterObserver(this);

        Iterator<Coord> toBeConcealed = HexMath.saftey(HexMath.hexagon(new Coord(avatar.getR(), avatar.getS()), 6), maxR, maxS);
        while(toBeConcealed.hasNext()) {
            Coord current = toBeConcealed.next();
            int r = current.getR();
            int s = current.getS();
            tiles[findX(r, s)][findY(r,s)].setVisibility(Visibility.NONVISIBLE);
        }
    }

    public TileViewObject getTileVO(ViewObject vo) {
        int     x = findX(vo.getR(), vo.getS()),
                y = findY(vo.getR(), vo.getS());
        return tiles[x][y];
    }

    public ViewObjectFactory getFactory() {
        return factory;
    }

    public TileViewObject getTileVO(int r, int s) {
        return tiles[findX(r, s)][findY(r, s)];
    }

    public int getMaxR() {
        return maxR;
    }


    public int getMaxS() {
        return maxS;
    }

}
