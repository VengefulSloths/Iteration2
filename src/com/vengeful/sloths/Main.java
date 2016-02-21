package com.vengeful.sloths;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;

public class Main {

    public static void main(String[] args) {
	// write your code here
        StrengthAddable cat = new StrengthAddable(5);
        System.out.println("this is the str: " + cat.getStrength());
    }
}
