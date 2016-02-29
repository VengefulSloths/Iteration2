package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexs on 2/20/2016.
 */
public class driver {
    public static void main(String ... args) {
        System.out.println("Starting driver");

        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchNewGame());
        launcher.launch();




        //Below here is test code

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        Avatar testAvatar = Avatar.getInstance();

        int count = 0;
        Iterator<Coord> iter = HexMath.sortedRing(new Coord(3,4),3);
        while (iter.hasNext()) {
            final Coord current = iter.next();
            final int sample = count;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (sample == 0) testAvatar.setFacingDirection(Direction.SE);
                    if (sample == 5) testAvatar.setFacingDirection(Direction.S);
                    if (sample == 9) testAvatar.setFacingDirection(Direction.SW);
                    if (sample == 12) testAvatar.setFacingDirection(Direction.NW);
                    if (sample == 15) testAvatar.setFacingDirection(Direction.N);
                    if (sample == 17) testAvatar.setFacingDirection(Direction.NE);
                    testAvatar.move(testAvatar.getFacingDirection());
                }
            }, (++count), TimeUnit.SECONDS);
        }

        int countOffset = count;
        count = 0;
        Iterator<Coord> iter2 = HexMath.sortedRing(new Coord(3,4),3);
        while (iter2.hasNext()) {
            final Coord current = iter2.next();
            final int sample = count;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (sample == 0) testAvatar.setFacingDirection(Direction.SE);
                    if (sample == 4) testAvatar.setFacingDirection(Direction.S);
                    if (sample == 7) testAvatar.setFacingDirection(Direction.SW);
                    if (sample == 10) testAvatar.setFacingDirection(Direction.NW);
                    if (sample == 13) testAvatar.setFacingDirection(Direction.N);
                    if (sample == 16) testAvatar.setFacingDirection(Direction.NE);
                    testAvatar.move(testAvatar.getFacingDirection());
                }
            }, (++count) + countOffset , TimeUnit.SECONDS);
        }

        System.out.println("Finished with driver");
    }
}
