package com.vengeful.sloths.Utility;

/**
 * Created by zach on 2/8/16.
 */
public class IsNumber {

    public static boolean isNumber(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
