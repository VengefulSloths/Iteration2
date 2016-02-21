package ViewObjects.CoordinateStrategies;

import ViewObjects.CoordinateStrategies.CoordinateStrategy;
import ViewObjects.ViewObject;

/**
 * Created by alexs on 2/20/2016.
 */
public class SimpleHexCoordinateStrategy implements CoordinateStrategy {

    private final int HEX_WIDTH = 56;
    private final int HEX_HEIGHT = 48;
    private final int CENTER_X_OFFSET = 39;
    private final int CENTER_Y_OFFSET = 26;
    @Override
    public void adjustXY(int r, int s, ViewObject subject) {
        subject.setXPixels(r*HEX_WIDTH+ CENTER_X_OFFSET);
        subject.setYPixels( HEX_HEIGHT*s + HEX_HEIGHT*r/2 + CENTER_Y_OFFSET);
    }
}
