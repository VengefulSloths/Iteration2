package com.vengeful.sloths.AreaView.DynamicImages;

/**
 * Created by alexs on 2/24/2016.
 */
public class CustomOffsetPositioningStrategy implements PositioningStrategy {
    int x,y;

    public CustomOffsetPositioningStrategy(int xPixelOffset, int yPixelOffset) {
        this.x = xPixelOffset;
        this.y = yPixelOffset;
    }
    @Override
    public int offsetXPixels(DynamicImage dynamicImage) {
        return x;
    }

    @Override
    public int offsetYPixels(DynamicImage dynamicImage) {
        return y;
    }
}
