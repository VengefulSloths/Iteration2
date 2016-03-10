package com.vengeful.sloths.Views.StatsView;

import com.vengeful.sloths.Views.ViewFactory.TextLabelFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class StatsViewObject extends JPanel {

    JLabel statName;
    JLabel statValue;
    private JPanel statsObjectPanel;
    private String stat;
    private String value;
    private TextLabelFactory labelFactory = new TextLabelFactory();

    public StatsViewObject(String stat, int value){

        this.statsObjectPanel = new JPanel();
        this.statName = labelFactory.generateStatsLabel(stat + ": ", 15, Color.BLACK);
        this.statValue = labelFactory.generateStatsLabel(String.valueOf(value), 13, Color.BLUE);
        this.setBackground(new Color(0f,0f,0f,0f));

        FlowLayout flow = new FlowLayout();
        flow.setAlignment(FlowLayout.CENTER);
        flow.setHgap(0);

        this.statsObjectPanel.setLayout(flow);
        this.statsObjectPanel.setBackground(new Color(0f,0f,0f,0f));
        this.statsObjectPanel.add(this.statName);
        this.statsObjectPanel.add(this.statValue);
        this.add(statsObjectPanel);

    }


    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);

    }

}
