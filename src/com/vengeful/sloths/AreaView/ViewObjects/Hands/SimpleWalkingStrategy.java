package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.ViewTime;

/**
 * Created by alexs on 3/4/2016.
 */
public class SimpleWalkingStrategy implements WalkingStrategy {

    private int handStrideDistance;

    public SimpleWalkingStrategy(int handStrideDistance) {
        this.handStrideDistance = handStrideDistance;
    }

    @Override
    public void startWalking(SmartHandViewObject hand, long duration) {
        ViewTime.getInstance().registerAlert(duration/5, () -> doWalk(hand, duration, 0));
    }

    public void doWalk(SmartHandViewObject hand, long duration, int step) {
        switch (step) {
            case 0:
                hand.setOffset(handStrideDistance);
                break;
            case 1:
                hand.setOffset(0);
                break;
            case 2:
                hand.setOffset(-handStrideDistance);
                break;
            case 3:
                hand.setOffset(0);
                break;
        }
        if (step != 3) {
            ViewTime.getInstance().registerAlert(duration/5, () -> doWalk(hand, duration, step + 1));
        }
    }
}
