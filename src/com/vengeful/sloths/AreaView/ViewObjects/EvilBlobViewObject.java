package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.awt.*;

/**
 * Created by John on 3/2/2016.
 */
public class EvilBlobViewObject extends HominidViewObject  {
    public EvilBlobViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);
    }

    @Override
    public void alertDeath(){
        super.alertDeath();
        (new SoundEffect("resources/audio/cyclops_death.wav")).play();

    }
}
