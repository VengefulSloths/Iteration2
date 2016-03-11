package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.Hands.HandsCoordinator;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;

import java.awt.*;

/**
 * Created by zach on 3/9/16.
 */
public class HominidViewObject extends EntityViewObject {

    private boolean isMounted;
    private DynamicImage mountImage;

    private HandsCoordinator hands;
    private HatViewObject hat;

    public HominidViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);

        this.hat = new HatViewObject(r, s, coordinateStrategy, locationStrategy, direction);
        this.hands = new HandsCoordinator(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    public HominidViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, String defaultHatName) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);

        if (this.hands == null)
            this.hands = new HandsCoordinator(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);

        if (this.hat == null)
            this.hat = new HatViewObject(r, s, coordinateStrategy, locationStrategy, direction);

        this.hat.setDefaultHatPath("resources/equipment/" + defaultHatName + "/");
    }

    @Override
    public void paintComponent(Graphics2D g) {



        if (!dead) {
            if (isMounted) {
                g.drawImage(mountImage.getImage(),
                        getXPixels() + getLocationXOffset() + mountImage.getXOffset(),
                        getYPixels() + getLocationYOffset() + mountImage.getYOffset(),
                        this);
            }
            hands.paintBack(g);

        } else {}

        super.paintComponent(g);

        if (!dead) {
            hat.paintComponent(g);
            hands.paintFront(g);
        }


    }

    @Override
    public void alertDirectionChange(Direction d) {
        super.alertDirectionChange(d);

        hands.changeDirection(d);
        hat.changeDirection(d);
    }

    @Override
    public void setLocation(int r, int s) {
        super.setLocation(r, s);
        hands.setLocation(r, s);
        hat.setLocation(r, s);
    }

    @Override
    public void movementHook(int r, int s, long duration) {
        super.movementHook(r, s, duration);

        hands.alertMove(r, s, duration);
        hat.alertMove(r, s, duration);
    }

    @Override
    public void alertAttack(int r, int s, long windUpTime, long coolDownTime) {
        hands.attack(r, s, windUpTime, coolDownTime);
    }

    @Override
    public void alertEquipWeapon(String name, WeaponClass weaponClass) {
        WeaponImageContainer weapon = new WeaponImageContainer("resources/weapons/" + name + "/", direction);
        hands.equipWeapon(weapon, weaponClass);
    }

    @Override
    public void alertMount(String mountName) {
        mountImage = DynamicImageFactory.getInstance().loadDynamicImage("resources/mount/" + mountName);
        this.isMounted = true;
    }

    @Override
    public void alertUnequipWeapon() {
        hands.unequip();
    }

    @Override
    public void alertEquipHat(String name) {
        this.hat.equip("resources/equipment/" + name + "/");
    }

    @Override
    public void alertUnequipHat() {
        this.hat.unequip();
    }

    @Override
    public void alertCast(long windUpTime, long coolDownTime) {
        hands.cast(windUpTime, coolDownTime);
    }
}
