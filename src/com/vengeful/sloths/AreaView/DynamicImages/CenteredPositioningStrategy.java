package DynamicImages;

import DynamicImages.DynamicImage;
import DynamicImages.PositioningStrategy;

/**
 * Created by alexs on 2/20/2016.
 */
public class CenteredPositioningStrategy implements PositioningStrategy {

    @Override
    public int offsetXPixels(DynamicImage dynamicImage) {
        return -dynamicImage.getWidth()/2;
    }

    @Override
    public int offsetYPixels(DynamicImage dynamicImage) {
        return -dynamicImage.getHeight()/2;
    }
}
