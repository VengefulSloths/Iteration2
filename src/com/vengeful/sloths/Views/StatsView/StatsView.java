package com.vengeful.sloths.Views.StatsView;

import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;
import com.vengeful.sloths.Models.Observers.ProxyStatsObserver;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class StatsView extends View implements StatsObserver {
    private JPanel titlePanel = new JPanel();
    private JPanel statsPanel = new JPanel();
    private Stats stats;
    private ArrayList<StatsViewObject> statsList;
    public JPanel getTitlePanel() {
        return titlePanel;
    }
    public JPanel getStatsPanel() {
        return statsPanel;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
        StatsViewObjectFactory factory = new StatsViewObjectFactory();
        statsList = factory.generateStatsViewObjects(stats);

    }

    public StatsView(Stats stats) {
        this.stats = stats;
        ProxyObserver pio = new ProxyStatsObserver(this, stats);
        ObserverManager.getInstance().addProxyObserver(pio);

        StatsViewObjectFactory factory = new StatsViewObjectFactory();
        statsList = factory.generateStatsViewObjects(stats);

        initDefaultUI();
    }

    public StatsView() {
        initDefaultUI();
    }

    public void initDefaultUI() {

        JLabel title = generateTitleLabel("Stats", 22, Color.WHITE);
        //titlePanel = new JPanel();
        //statsPanel = new JPanel();

        //this.setBackgroundImageFileName("resources/skyInventory2.png");
        this.setBackground(new Color(0f,0f,0f,0.3f));
        this.titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.statsPanel.setBackground(new Color(0f,0f,0f,0f));

        GridLayout grid = new GridLayout(15,3);
        grid.setVgap(0);

        this.setLayout(new BorderLayout());
        this.titlePanel.setLayout(new BorderLayout());
        this.statsPanel.setLayout(grid);
        //this.statsPanel.setLayout(new FlowLayout());
        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));

        //this.titlePanel.setBorder(new LineBorder(Color.BLACK));
        //this.setBorder(new LineBorder(Color.WHITE));
        this.titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.CENTER);
        //populateView(this.statsList);

    }

    public void addStat(StatsViewObject stat) {
        this.statsPanel.add(stat);
    }

    public void populateView(ArrayList<StatsViewObject> statsList) {
        for(StatsViewObject s: statsList)
            this.statsPanel.add(s);
            this.statsPanel.revalidate();
            this.statsPanel.repaint();
    }

    public void emptyView() {
        for(StatsViewObject s: statsList)
            this.statsPanel.remove(s);
            this.statsPanel.revalidate();
            this.statsPanel.repaint();
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

    @Override
    public void alertStatChanged(Stats stat) {
        //manager.setStats(stat);
        //this.emptyView();
        this.setStats(stat);
        //this.populateView(this.statsList);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = this.getWidth()/2;
        int y;
        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int stringHeight = g.getFontMetrics().getHeight();
        y = this.getTitlePanel().getHeight() + stringHeight + this.getStatsPanel().getWidth()/10;
        for (int i=0; i<this.statsList.size(); i++) {
            this.statsList.get(i).paintComponent(g,x,y);
            y+=(stringHeight+15);
        }
    }




}
