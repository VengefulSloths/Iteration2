package com.vengeful.sloths.Utility;

/**
 * Created by luluding on 3/12/16.
 */
public class ModelConfig {

    private static final int RADIUS_OF_VISIBILITY = 5; //for player

    private static final int ONE_HANDED_WINDUP = 4;
    private static final int TWO_HANDED_WINDUP = 10;
    private static final int KNUCKLE_WINDUP = 4;
    private static final int ONE_HANDED_SPEED = 12;
    private static final int TWO_HANDED_SPEED = 30;
    private static final int KNUCKLE_SPEED = 8;

    private static final int MANA_COST_LOW = 2;
    private static final int MANA_COST_MID = 4;
    private static final int MANA_COST_HIGH = 6;

    private static final int INITIAL_SKILLPOINT = 3; //give player 3 skill points on start


    public static int getInitialSkillpoint() {
        return INITIAL_SKILLPOINT;
    }

    public static int getRadiusOfVisibility() {
        return RADIUS_OF_VISIBILITY;
    }

    public static int getOneHandedWindup() {
        return ONE_HANDED_WINDUP;
    }

    public static int getTwoHandedWindup() {
        return TWO_HANDED_WINDUP;
    }

    public static int getKnuckleWindup() {
        return KNUCKLE_WINDUP;
    }

    public static int getOneHandedSpeed() {
        return ONE_HANDED_SPEED;
    }

    public static int getTwoHandedSpeed() {
        return TWO_HANDED_SPEED;
    }

    public static int getKnuckleSpeed() {
        return KNUCKLE_SPEED;
    }

    public static int getManaCostLow() {
        return MANA_COST_LOW;
    }

    public static int getManaCostMid() {
        return MANA_COST_MID;
    }

    public static int getManaCostHigh() {
        return MANA_COST_HIGH;
    }

}
