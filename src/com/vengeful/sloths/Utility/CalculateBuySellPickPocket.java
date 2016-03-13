package com.vengeful.sloths.Utility;

/**
 * Created by harrison on 3/13/16.
 */
public class CalculateBuySellPickPocket {

    public static int CalculateBuyPrice(int value, int bargainSkill){
        if(bargainSkill != 0){
            value = value * 5 / bargainSkill;
        }else{
            value = value * 5 / 1;
        }
        return value;
    }

    public static int CalculateSellPrice(int value, int bargainSkill){
        if(bargainSkill != 0){
            value = value + (int)Math.ceil(bargainSkill * 1.5);
        }else{
            value = value + 2;
        }
        return value;
    }

    public static int CalculatePickpocketChance(int value, int pickPocketSkill){
        if(value <= 100){
            value = 100 - (int)Math.floor(value/pickPocketSkill);
        }
        else{
            int difference = value - 100;
            value = value - difference;
            difference = (int)Math.floor(difference / (3 * pickPocketSkill));
            value = 100 - (int)Math.floor(value/pickPocketSkill);
            value = value - difference;
        }
        if(value <= 0){
            return 0;
        }
        return value;

    }
}
