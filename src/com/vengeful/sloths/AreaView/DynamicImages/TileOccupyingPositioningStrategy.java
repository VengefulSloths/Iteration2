package com.vengeful.sloths.AreaView.DynamicImages;

/**
 * Created by Alex on 2/21/2016.
 */
public class TileOccupyingPositioningStrategy implements PositioningStrategy{

    @Override
    public int offsetXPixels(DynamicImage dynamicImage) {
        return -dynamicImage.getWidth()/2;
    }

    @Override
    public int offsetYPixels(DynamicImage dynamicImage) {
        return -dynamicImage.getHeight() +26;
    }
}
