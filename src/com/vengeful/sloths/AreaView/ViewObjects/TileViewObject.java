package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Visibility;
import org.omg.CORBA.UNKNOWN;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by alexs on 2/23/2016.
 */

//TODO: Give tile a clean operation that will clear any VOs on it that wont persist
public class TileViewObject extends ViewObject{
    private ArrayList<ViewObject> children;
    private DynamicImage unknownImage = DynamicImageFactory.getInstance().loadDynamicImage("resources/terrain/disapearing_cloud.xml");

    private BufferedImage nonVisibleImage;
    private int r;
    private int s;

    private DynamicImage fog;

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        if (this.visibility == Visibility.UNKNOWN && visibility == Visibility.NONVISIBLE) {
            //illegal state transition
        } else if (this.visibility == Visibility.VISIBLE && visibility == Visibility.NONVISIBLE) {



            this.visibility = Visibility.NONVISIBLE;
        } else if (this.visibility == Visibility.UNKNOWN && visibility == Visibility.VISIBLE){
            this.visibility = Visibility.VISIBLE;
            ((DynamicTimedImage) unknownImage).start(300);
        } else {
            this.visibility = visibility;

        }
    }



    private Visibility visibility = Visibility.UNKNOWN;
    public TileViewObject(int r, int s, CoordinateStrategy cs, LocationStrategy ls) {
        super(r, s, cs, ls);
        children = new ArrayList<>();

        this.fog = DynamicImageFactory.getInstance().loadDynamicImage("resources/effects/fog_of_war.xml");
    }

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
        children.remove(child);
    }


    public void paintComponent(Graphics2D g) {

        if (visibility == Visibility.UNKNOWN) {
            g.drawImage(unknownImage.getImage(),
                    this.getXPixels() + unknownImage.getXOffset() + getLocationXOffset(),
                    this.getYPixels() + unknownImage.getYOffset() + getLocationYOffset(),
                    this);
        } else if (visibility == Visibility.NONVISIBLE) {
            for (ViewObject child: children) {
                child.paintComponent(g);
            }
            g.drawImage(fog.getImage(),
                    this.getXPixels() + fog.getXOffset() + getLocationXOffset(),
                    this.getYPixels() + fog.getYOffset() + getLocationYOffset(),
                    this);
        } else {

            for (ViewObject child: children) {
                child.paintComponent(g);
            }
            g.drawImage(unknownImage.getImage(),
                    this.getXPixels() + unknownImage.getXOffset() + getLocationXOffset(),
                    this.getYPixels() + unknownImage.getYOffset() + getLocationYOffset(),
                    this);
        }
    }
    @Override
    public void accept(VOVisitor v) {
        v.visitTile(this);
    }
}
