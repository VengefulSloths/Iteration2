package DynamicImages;

/**
 * Created by alexs on 2/20/2016.
 */
public interface PositioningStrategy {
    int offsetXPixels(DynamicImage dynamicImage);
    int offsetYPixels(DynamicImage dynamicImage);
}
