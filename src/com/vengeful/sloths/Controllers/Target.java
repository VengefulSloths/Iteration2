package com.vengeful.sloths.Controllers;

import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 2/22/16.
 */
public class Target<T> {
    private Coord coord;
    private T targetType;
    // 0 - highest ... 100 - lowest
    private int priority;

    public int getPriority() { return this.priority; }
}
