package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by alexs on 2/25/2016.
 */
public class WeaponImageContainer {
    private DynamicImage weaponFrontN;
    private DynamicImage weaponFrontNE;
    private DynamicImage weaponFrontNW;
    private DynamicImage weaponFrontSE;
    private DynamicImage weaponFrontSW;
    private DynamicImage weaponFrontS;


    private DynamicImage weaponBackN;
    private DynamicImage weaponBackNE;
    private DynamicImage weaponBackNW;
    private DynamicImage weaponBackSE;
    private DynamicImage weaponBackSW;
    private DynamicImage weaponBackS;

    private DynamicImage currentBack;
    private DynamicImage currentFront;


    public WeaponImageContainer(String resourcePath, Direction dir) {
        DynamicImageFactory dif = DynamicImageFactory.getInstance();

        System.out.println(resourcePath + "weapon_back_N.xml");
        this.weaponBackN = dif.loadDynamicImage(resourcePath + "weapon_back_N.xml");
        this.weaponBackS = dif.loadDynamicImage(resourcePath + "weapon_back_S.xml");
        this.weaponBackNW = dif.loadDynamicImage(resourcePath + "weapon_back_NW.xml");
        this.weaponBackNE = dif.loadDynamicImage(resourcePath + "weapon_back_NE.xml");
        this.weaponBackSW = dif.loadDynamicImage(resourcePath + "weapon_back_SW.xml");
        this.weaponBackSE = dif.loadDynamicImage(resourcePath + "weapon_back_SE.xml");

        this.weaponFrontN = dif.loadDynamicImage(resourcePath + "weapon_front_N.xml");
        this.weaponFrontS = dif.loadDynamicImage(resourcePath + "weapon_front_S.xml");
        this.weaponFrontNW = dif.loadDynamicImage(resourcePath + "weapon_front_NW.xml");
        this.weaponFrontNE = dif.loadDynamicImage(resourcePath + "weapon_front_NE.xml");
        this.weaponFrontSW = dif.loadDynamicImage(resourcePath + "weapon_front_SW.xml");
        this.weaponFrontSE = dif.loadDynamicImage(resourcePath + "weapon_front_SE.xml");

        this.alertDirectionChange(dir);
    }

    public void alertDirectionChange(Direction direction) {
        switch (direction) {
            case N:
                this.currentFront = weaponFrontN;
                this.currentBack = weaponBackN;
                break;
            case S:
                this.currentFront = weaponFrontS;
                this.currentBack = weaponBackS;
                break;
            case NE:
                this.currentFront = weaponFrontNE;
                this.currentBack = weaponBackNE;
                break;
            case NW:
                this.currentFront = weaponFrontNW;
                this.currentBack = weaponBackNW;
                break;
            case SE:
                this.currentFront = weaponFrontSE;
                this.currentBack = weaponBackSE;
                break;
            case SW:
                this.currentFront = weaponFrontSW;
                this.currentBack = weaponBackSW;
                break;
        }
    }

    public DynamicImage getWeaponBack() {
        return this.currentBack;
    }


    public DynamicImage getWeaponFront() {
        return this.currentFront;
    }
}
