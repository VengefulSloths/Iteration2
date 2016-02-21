package ViewObjects.LocationStrategies;

import ViewObjects.LocationStrategies.LocationStrategy;

/**
 * Created by alexs on 2/20/2016.
 */
public class CenterAvatarLocationStrategy implements LocationStrategy {
    @Override
    public int offsetXPixels(int xPixels) {
        return xPixels;
    }

    @Override
    public int offsetYPixels(int yPixels) {
        return yPixels;
    }
}
