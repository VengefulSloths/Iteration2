package DynamicImages;

import DynamicImages.DynamicImage;
import DynamicImages.PositioningStrategy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class SingleFrameImage extends DynamicImage {
    private Image image;
    public SingleFrameImage(String configPath, PositioningStrategy positioningStrategy) {
        super(52, 78, positioningStrategy);
        this.image = (new ImageIcon(configPath)).getImage();
    }
    public Image getImage() {
        return image;
    }
}
