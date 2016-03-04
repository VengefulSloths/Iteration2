package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by alexs on 2/25/2016.
 */
public class WeaponImageContainer {

    private DynamicImage weaponN;
    private DynamicImage weaponNE;
    private DynamicImage weaponNW;
    private DynamicImage weaponSE;
    private DynamicImage weaponSW;
    private DynamicImage weaponS;



    private DynamicImage currentImage;

    public WeaponImageContainer(String resourcePath, Direction dir) {
        DynamicImageFactory dif = DynamicImageFactory.getInstance();

        this.weaponN = dif.loadDynamicImage(resourcePath + "weapon_n.xml");
        this.weaponS = dif.loadDynamicImage(resourcePath + "weapon_s.xml");
        this.weaponNW = dif.loadDynamicImage(resourcePath + "weapon_nw.xml");
        this.weaponNE = dif.loadDynamicImage(resourcePath + "weapon_ne.xml");
        this.weaponSW = dif.loadDynamicImage(resourcePath + "weapon_sw.xml");
        this.weaponSE = dif.loadDynamicImage(resourcePath + "weapon_se.xml");

        this.alertDirectionChange(dir);
    }

    public void alertDirectionChange(Direction direction) {
        switch (direction) {
            case N:
                this.currentImage = weaponN;
                break;
            case S:
                this.currentImage = weaponS;
                break;
            case NE:
                this.currentImage = weaponNE;
                break;
            case NW:
                this.currentImage = weaponNW;
                break;
            case SE:
                this.currentImage = weaponSE;
                break;
            case SW:
                this.currentImage = weaponSW;
                break;
        }
    }


    public DynamicImage getCurrentDynamicImage() {
        return this.currentImage;
    }
}
