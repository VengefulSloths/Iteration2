package com.vengeful.sloths.Utility;

/**
 * Created by alexs on 3/3/2016.
 */
public enum WeaponClass {
    ONE_HAND(0),
    TWO_HAND(1),
    FISTS(2),
    BOW(3),
    THROW(4);

    private int value;

    WeaponClass(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static WeaponClass fromInt(int i){
        for(WeaponClass km : WeaponClass.values()){
            if(km.getValue() == i){
                return km;
            }
        }
        return null;
    }
}
