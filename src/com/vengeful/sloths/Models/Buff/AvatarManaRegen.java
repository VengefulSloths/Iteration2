package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.TimeModel.Tickable;

/**
 * Created by John on 3/14/2016.
 */
public class AvatarManaRegen implements Tickable {
    int count = 1;
    @Override
    public void tick() {
        ++count;
        if(count % 150 == 0){
            Avatar.getInstance().getStats().add(new HealthManaExperienceAddable(0,0,3,0,0));

        }
    }
}
