package com.vengeful.sloths.Views.StatsView;

import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class StatsView extends View implements StatsObserver {
    private JPanel titlePanel;
    private JPanel statsPanel;
    private Stats stats;

    StatsViewObjectManager manager;

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public StatsView(Stats stats) {
        this.stats = stats;
        initDefaultUI();
    }

    public StatsView() {
        initDefaultUI();
    }

    public void initDefaultUI() {

        JLabel title = generateTitleLabel("Stats", 16, Color.WHITE);
        titlePanel = new JPanel();
        statsPanel = new JPanel();

        //this.setBackgroundImageFileName(backgroundImageFileName);
        this.setBackgroundImageFileName("resources/statsBackground.png");
        this.titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.statsPanel.setBackground(new Color(0f,0f,0f,0f));

        GridLayout grid = new GridLayout(15,3);
        grid.setVgap(0);

        this.setLayout(new BorderLayout());
        this.titlePanel.setLayout(new BorderLayout());
        this.statsPanel.setLayout(grid);
        //this.statsPanel.setLayout(new FlowLayout());

        //this.titlePanel.setBorder(new LineBorder(Color.BLACK));
        this.statsPanel.setBorder(new LineBorder(Color.BLACK));

        this.titlePanel.add(title, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.CENTER);

    }

    public void addStat(StatsViewObject stat) {
        this.statsPanel.add(stat);
    }

    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        generateImageBackground("resources/statsBackground.png", g);

        Iterator<StatsViewObject> iterator = manager.iterator();
        while(iterator.hasNext()) {
            StatsViewObject current = iterator.next();
            this.add(current);
            //current.paintComponent(g);
        }
    }

    public void displayStats() {
        Iterator<StatsViewObject> iterator = manager.iterator();
        while(iterator.hasNext()) {
            StatsViewObject current = iterator.next();
            this.add(current);
            //current.paintComponent(g);
        }
    }
*/

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void alertStatChanged(Stats stat) {
        manager.setStats(stat);
    }



}
