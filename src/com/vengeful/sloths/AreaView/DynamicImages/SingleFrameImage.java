package com.vengeful.sloths.AreaView.DynamicImages;


import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class SingleFrameImage extends DynamicImage {
    private Image image;
    public SingleFrameImage(String configPath, int width, int height, PositioningStrategy positioningStrategy) {
        super(width, height, positioningStrategy);
        this.image = (new ImageIcon(configPath)).getImage();
    }
    public Image getImage() {
        return image;
    }
}
