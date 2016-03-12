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

    public String getStat() {
        return stat;
    }
    public void setStat(String stat) {
        this.stat = stat;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public StatsViewObject(String stat, int value){
        this.stat = stat;
        this.value = String.valueOf(value);

        /*
        this.statsObjectPanel = new JPanel();
        this.statName = labelFactory.generateStatsLabel(stat + ": ", 16, Color.BLACK);
        this.statValue = labelFactory.generateStatsLabel(String.valueOf(value), 14, Color.BLUE);
        this.setBackground(new Color(0f,0f,0f,0f));

        FlowLayout flow = new FlowLayout();
        flow.setAlignment(FlowLayout.CENTER);
        flow.setHgap(0);

        this.statsObjectPanel.setLayout(flow);
        this.statsObjectPanel.setBackground(new Color(0f,0f,0f,0f));
        this.statsObjectPanel.add(this.statName);
        this.statsObjectPanel.add(this.statValue);
        this.add(statsObjectPanel, 0);
        */

    }



    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);

    }

    public void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        String statString = this.getStat() + ": " + this.getValue();
        int stringWidth = g.getFontMetrics().stringWidth(statString);
        g.drawString(statString, x - stringWidth/2, y);

    }


}
