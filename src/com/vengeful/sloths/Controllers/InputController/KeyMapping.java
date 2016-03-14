package com.vengeful.sloths.Controllers.InputController;

/**
 * Created by John on 3/10/2016.
 */
public enum KeyMapping {
    SOUTHWEST(0),
    SOUTH(1),
    SOUTHEAST(2),
    WEST(3),
    CENTER(4),
    EAST(5),
    NORTHWEST(6),
    NORTH(7),
    NORTHEAST(8),
    INVENTORY(9),
    EQUIPMENT(10),
    ENTER(11),
    SPACE(12),
    ESC(13),
    LEFT(14),
    RIGHT(15),
    DOWN(16),
    UP(17),
    DROP(18),
    SAVE(19),
    ABILITY_1(20),
    ABILITY_2(21),
    ABILITY_3(22),
    ABILITY_4(23),
    ABILITIES(24),
    TALK(25),
    OBSERVE(26);


    private int value;

    KeyMapping(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static KeyMapping fromInt(int i){
        for(KeyMapping km : KeyMapping.values()){
            if(km.getValue() == i){
                return km;
            }
        }
        return null;
    }
}
