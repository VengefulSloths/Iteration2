package com.vengeful.sloths.Views.HUDView;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class HUDView extends View implements StatsObserver {

    private Component leftPanel;
    private Component centerPanel;
    private Component rightPanel;


    private LivesView livesView;
    private IconView iconView;
    private HealthBarView healthBarView;

    private double leftPanelProportion = 0.25;
    private double rightPanelProportion = 0.20;
    private double centerPanelProportion = 1.0 - leftPanelProportion - rightPanelProportion;

    public HUDView() {

    }

    public HUDView(int viewWidth, int viewHeight) {

        super(viewWidth,viewHeight);
        Avatar.getInstance().getStats().registerObserver(this);
        this.setPreferredSize(new Dimension(360, 130));
        initHUDPanel(this.getViewWidth(), this.getViewHeight());
        this.setBackground(new Color(0f,0f,0f,0f));
        //this.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        alertStatChanged(Avatar.getInstance().getStats());
    }


    public void initHUDPanel(int viewWidth, int viewHeight) {

        //this.setLayout(new BorderLayout());
        initLeftPanel();
        initCenterPanel();
        initRightPanel();
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.add(this.rightPanel, BorderLayout.EAST);
    }

    public void initLeftPanel() {
        this.iconView = new IconView();
        this.leftPanel = iconView;
    }


    public void initCenterPanel() {
        this.healthBarView = new HealthBarView();
        this.centerPanel = healthBarView;
    }

    public void initRightPanel() {
        this.livesView = new LivesView();
        this.rightPanel = livesView;
    }


    @Override
    public void alertStatChanged(Stats stats) {
        //update health
        this.healthBarView.getHealthBar().setCurrentProgress(stats.getCurrentHealth());
        this.healthBarView.getHealthBar().setMaxProgress(stats.getMaxHealth());
        //update mana
        this.healthBarView.getManaBar().setCurrentProgress(stats.getCurrentMana());
        this.healthBarView.getManaBar().setMaxProgress(stats.getMaxMana());
        //update xp
        this.healthBarView.getXpBar().setCurrentProgress(stats.getCurrentExperience());
        this.healthBarView.getXpBar().setMaxProgress(stats.getMaxExperience());

        //set level
        this.iconView.setLevel(stats.getLevel());

        //set lives
        this.livesView.setNumLives(stats.getLives());


    }
}
