package com.vengeful.sloths.Models.Occupation;

import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by zach on 1/30/16.
 */
public class Smasher extends Occupation {

    public Smasher(Stats stats) {
        stats.add(new BaseStatsAddable(10, 0, 0, 0, 0));
    }

    @Override
    public void levelUp(Stats stats) {
        stats.add(new BaseStatsAddable(2, 1, 1, 1, 0));
    }

}
