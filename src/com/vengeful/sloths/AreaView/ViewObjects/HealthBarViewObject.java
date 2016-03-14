package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Utility.RealTuple;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by John on 3/3/2016.
 */
public class HealthBarViewObject extends MovingViewObject implements StatsObserver {
    private int currentHealth = 50;
    private int maxHealth = 100;

    private int observedDmg = 0;
    private int observedSpeed = 0;
    private boolean drawObservation = false;

    private static Font font = new Font("Futura", Font.BOLD, 15);



    private ArrayList<RealTuple<String, DynamicImage>> buffs = new ArrayList<>();

    public HealthBarViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy){
        super(r,s,coordinateStrategy,locationStrategy);
    }
    @Override
    public void paintComponent(Graphics2D g) {
        double offset = ((double)currentHealth/(double)maxHealth) * 32;
        g.setColor(Color.BLACK);
        g.fillRect(this.getXPixels() + getLocationXOffset() - 18,this.getYPixels() + getLocationYOffset() + - 102,36,10);
        g.setColor(Color.red);
        g.fillRect(this.getXPixels() + getLocationXOffset() - 16,this.getYPixels() + getLocationYOffset() + - 100, 32, 6);
        g.setColor(Color.green);
        g.fillRect(this.getXPixels() + getLocationXOffset() - 16,this.getYPixels() + getLocationYOffset() + - 100,(int)offset,6);


        int buffXOffset = -10*(buffs.size()-1) ;
        for ( RealTuple<String, DynamicImage> buff: buffs) {
            g.drawImage(buff.y.getImage(),
                    this.getXPixels() + this.getLocationXOffset() + buff.y.getXOffset() + buffXOffset,
                    this.getYPixels() + this.getLocationYOffset() + buff.y.getYOffset() - 114,
                    this);

            buffXOffset += 20;
        }


        if(this.drawObservation)
            drawObservation(g);


    }

    public void addBuff(String name) {
        this.buffs.add(new RealTuple<>(name, DynamicImageFactory.getInstance().loadDynamicImage("resources/buffs/" + name + ".xml")));
    }

    public void removeBuff(String name) {
        for (int i = buffs.size() - 1; i >=0; i--) {
            RealTuple<String, DynamicImage> buff = buffs.get(i);
            if (buff.x.equals(name)) {
                buffs.remove(buff);
                break;
            }
        }
    }

    private void drawObservation(Graphics2D g){
            g.setColor(Color.yellow);
            try {
                String damage = String.valueOf(observedDmg);
                String speed = String.valueOf(observedSpeed);
                Composite c = g.getComposite();
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.drawString("ATT", getLocationXOffset() + getXPixels() + 45 - metrics.stringWidth("ATT:")/2, getYPixels() + getLocationYOffset() - 110);
                g.drawString(damage, getLocationXOffset() + getXPixels() + 65 - metrics.stringWidth(damage)/2, getYPixels() + getLocationYOffset() - 110);
                g.drawString("SPEED", getLocationXOffset() + getXPixels() + 45 - metrics.stringWidth("SPEED:")/2, getYPixels() + getLocationYOffset() - 90);
                g.drawString(speed, getLocationXOffset() + getXPixels() + 75 - metrics.stringWidth(speed)/2, getYPixels() + getLocationYOffset() - 90);

                g.setComposite(c);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public void updateObservationInfo(int dmg, int speed){
        if(drawObservation == false)
            drawObservation = true;

        this.observedDmg = dmg;
        this.observedSpeed = speed;
    }

    public void removeObservationInfo(){
        drawObservation = false;
    }


    @Override
    public void accept(VOVisitor v) {

    }


    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void alertStatChanged(Stats stat) {
        this.currentHealth = stat.getCurrentHealth();
        this.maxHealth = stat.getMaxHealth();
    }
}
