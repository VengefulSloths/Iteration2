package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;
import com.vengeful.sloths.Views.View;

/**
 * Created by alexs on 1/31/2016.
 */
public class ProxyEntityObserver extends ProxyObserver
        implements EntityObserver {

    private EntityObserver target;

    public ProxyEntityObserver(EntityObserver entityObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = entityObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }

    @Override
    public void alertDirectionChange(Direction d) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertDirectionChange(d));
        }
    }

    @Override
    public void alertMove(int r, int s, long animationTime) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertMove(r, s, animationTime));
        }
    }

    @Override
    public void alertAttack(int r, int s, long windUpTime, long coolDownTime) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAttack(r, s, windUpTime, coolDownTime));
        }
    }

    @Override
    public void alertEquipWeapon(String name, WeaponClass weaponClass) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertEquipWeapon(name, weaponClass));
        }
    }

    @Override
    public void alertUnequipWeapon() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertUnequipWeapon());
        }
    }


    @Override
    public void alertEquipHat(String name) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertEquipHat(name));
        }
    }

    @Override
    public void alertUnequipHat() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertUnequipHat());
        }
    }

    @Override
    public void alertLevelUp() {

        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertLevelUp());
        }
    }
    @Override
    public void alertDeath() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertDeath());
        }
    }

    @Override
    public void alertStealth() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertStealth());
        }
    }

    @Override
    public void alertUnstealth() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertUnstealth());
        }
    }

    @Override
    public void alertMount(String mountName) {
        System.out.println("Proxy mount");
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertMount(mountName));
        }
    }

    @Override
    public void alertDemount() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertDemount());
        }
    }

    @Override
    public void alertAddBuff(String buffName) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAddBuff(buffName));
        }
    }

    @Override
    public void alertRemoveBuff(String buffName) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertRemoveBuff(buffName));
        }
    }

    @Override
    public void alertObservationInfo(int damage, int speed) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertObservationInfo(damage, speed));
        }
    }

    @Override
    public void alertRemoveObservationInf() {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertRemoveObservationInf());
        }
    }

    @Override
    public void alertCast(long windUpTime, long coolDownTime) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertCast(windUpTime, coolDownTime));
        }
    }

    @Override
    public void alertTakeDamage(int damage) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertTakeDamage(damage));
        }
    }
}
