package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest;

import com.vengeful.sloths.Models.ModelVisitable;

/**
 * Created by luluding on 2/6/16.
 */
public abstract class Quest implements ModelVisitable{
    public abstract boolean attempt();
}
