package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by alexs on 3/3/2016.
 */
public interface HandState {
    void paintFront(Graphics2D g);
    void paintBack(Graphics2D g);
    void setLocation(int r, int s);
    void changeDirection(Direction d);
    void alertMove(int r, int s, long duration);
    void attack(int r, int s, long duration);
}
