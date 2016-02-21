package ViewObjects;

import DynamicImages.CenteredPositioningStrategy;
import DynamicImages.DynamicImage;
import DynamicImages.SingleFrameImage;
import ViewObjects.CoordinateStrategies.CoordinateStrategy;
import ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class GrassViewObject extends ViewObject {
    private DynamicImage grassImage;
    public GrassViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy) {
        super(r, s, coordinateStrategy, locationStrategy);
        grassImage = new SingleFrameImage("resources/terrain/grass.png", new CenteredPositioningStrategy());
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(grassImage.getImage(), this.getXPixels() + grassImage.getXOffset(), this.getYPixels()+ grassImage.getYOffset(), this);
    }
}
