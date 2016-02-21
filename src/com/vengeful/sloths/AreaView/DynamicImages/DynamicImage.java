package DynamicImages;

import DynamicImages.PositioningStrategy;

import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public abstract class DynamicImage {
    private PositioningStrategy positioningStrategy;
    private int height;
    private int width;
    public abstract Image getImage();

    //TODO pull positioningStrategy out of the config
    public DynamicImage(int height, int width, PositioningStrategy positioningStrategy) {
        this.positioningStrategy = positioningStrategy;
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public int getXOffset() {
        return positioningStrategy.offsetXPixels(this);
    }
    public int getYOffset() {
        return positioningStrategy.offsetYPixels(this);
    }


}
