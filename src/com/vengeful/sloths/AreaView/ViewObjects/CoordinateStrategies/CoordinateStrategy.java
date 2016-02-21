package ViewObjects.CoordinateStrategies;

import ViewObjects.ViewObject;

/**
 * Created by alexs on 2/20/2016.
 */
public interface CoordinateStrategy {
    void adjustXY(int r, int s, ViewObject subject);
}
