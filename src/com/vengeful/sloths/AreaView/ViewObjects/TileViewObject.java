package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.AreaView;
import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.AreaView.Visibility;

import java.awt.*;
import java.awt.image.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */

//TODO: Give tile a clean operation that will clear any VOs on it that wont persist
public class TileViewObject extends ViewObject implements DestroyVOObserver{
    private ArrayList<ViewObject> children;
    private ArrayList<NonVisibleViewObject> nonVisibleChildren = new ArrayList<>();
    private DynamicImage unknownImage = DynamicImageFactory.getInstance().loadDynamicImage("resources/terrain/disapearing_cloud.xml");

    private BufferedImage nonVisibleImage;

    private static LookupOp darkenOp;


    public TileViewObject(int r, int s, CoordinateStrategy cs, LocationStrategy ls) {
        super(r, s, cs, ls);
        children = new ArrayList<>();

        short[] data = new short[256];
        for (short i = 0; i < 256; i++) {
            data[(int)i] = (short)(i/2);
        }

        LookupTable lookupTable = new ShortLookupTable(0, data);

        TileViewObject.darkenOp = new LookupOp(lookupTable, null);

        nonVisibleImage = new BufferedImage(78, 128, BufferedImage.TYPE_4BYTE_ABGR_PRE);

    }

    private int getWeirdXOffset() {
        return this.getXPixels() + this.getLocationXOffset() -39;
    }
    private int getWeirdYOffset() {
        return this.getYPixels() + this.getLocationYOffset() -100;
    }


    public ArrayList<ViewObject> getChildren() {
        return children;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        switch(visibility) {
            case NONVISIBLE:
                if (this.visibility == Visibility.VISIBLE) {
                    preCalcNonVisibleImage();
                    this.visibility = Visibility.NONVISIBLE;
                }
                break;
            case UNKNOWN:
                this.visibility = Visibility.UNKNOWN;
                break;
            case VISIBLE:
                if (this.visibility == Visibility.UNKNOWN) {
                    ((DynamicTimedImage) unknownImage).start(300);
                }
                this.visibility = Visibility.VISIBLE;
                nonVisibleChildren.clear();
                break;
        }
    }

    public void preCalcNonVisibleImage() {
        nonVisibleChildren.clear();
        for (ViewObject child: children) {
            nonVisibleChildren.add(child.getNonVisibleSnapShot());
        }
    }

    private Visibility visibility = Visibility.UNKNOWN;


    public void addChild(ViewObject child) {
        children.add(child);
        children.sort(new Comparator<ViewObject>() {
            VOSorter sorter = new VOSorter();
            @Override
            public int compare(ViewObject o1, ViewObject o2) {
                o1.accept(sorter);
                int z1 = sorter.getZLevel();
                o2.accept(sorter);
                int z2 = sorter.getZLevel();

                return z1 - z2;
            }
        });
    }

    public void removeChild(ViewObject child) {
        if (children.contains(child)) {
            children.remove(child);
        }
    }

    public void reallyRemoveChild(ViewObject child) {
        if (children.contains(child)) {
            children.remove(child);
        } else {
            ViewTime.getInstance().registerAlert(0, () -> reallyRemoveChild(child));
        }
    }

    public void paintComponent(Graphics2D g) {
        if (visibility == Visibility.UNKNOWN) {
            g.drawImage(unknownImage.getImage(),
                    this.getXPixels() + unknownImage.getXOffset() + getLocationXOffset(),
                    this.getYPixels() + unknownImage.getYOffset() + getLocationYOffset(),
                    this);
        } else if (visibility == Visibility.NONVISIBLE) {
           for (NonVisibleViewObject nonVisibleViewObject: nonVisibleChildren) {
               nonVisibleViewObject.paintComponent(g);
           }
        } else {


            for (int i =0; i<children.size(); i++) {
                try {
                    if ((children.get(i)) != null) {
                        children.get(i).paintComponent(g);
                    }
                } catch (Exception e) {
                    //do nothing its ok
                }
            }
            g.drawImage(unknownImage.getImage(),
                    this.getXPixels() + unknownImage.getXOffset() + getLocationXOffset(),
                    this.getYPixels() + unknownImage.getYOffset() + getLocationYOffset(),
                    this);
        }
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void alertDestroyVO(ViewObject vo) {
        this.removeChild(vo);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitTile(this);
    }

}
