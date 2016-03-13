package com.vengeful.sloths.Views.CharacterView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ListItemViewObject;
import com.vengeful.sloths.Views.StatsView.StatsView;
import com.vengeful.sloths.Views.StatsView.StatsViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class CharacterView extends JPanel {
   // public class CharacterViewManager extends JPanel {

    private InventoryView inventoryView;
    private EquipmentView equipmentView;
    private StatsView statsView;
    //rivate JPanel containerPanel;
    private View containerPanel;
    //private Inventory inventory;

    /* //Code for differently sized panels in the CharacterView
    private double leftPanelProportion = 0.21;
    private double rightPanelProportion = 0.21;
    private double centerPanelProportion = 1.0 - leftPanelProportion - rightPanelProportion;
    */

    public InventoryView getInventoryView() {
        return inventoryView;
    }
    public EquipmentView getEquipmentView() {
        return equipmentView;
    }
    public StatsView getStatsView() {
        return statsView;
    }

    protected static final int CHARACTER_VIEW_WIDTH =(int) Config.instance().getCharacterViewWidth();
    protected static final int CHARACTER_VIEW_HEIGHT = (int) Config.instance().getCharacterViewHeight();

    public static int getCharacterViewWidth() {
        return CHARACTER_VIEW_WIDTH;
    }
    public static int getCharacterViewHeight() {
        return CHARACTER_VIEW_HEIGHT;
    }

    public CharacterView(InventoryView iv, EquipmentView ev, StatsView sv) {
        this.inventoryView = iv;
        this.equipmentView = ev;
        this.statsView = sv;

        initDefaultUI();

    }

    public CharacterView() {

        /* the idea is to have the overall CVM have a flowlayout so it can be resized and a transparent
        background so you can see the views behind it, but within this otherwise pointless Panel
       is the gridlayout containerPanel that actually holds the 3 views (stats, inventory, equip)
         */

        //this.containerPanel.setBorder(new LineBorder(Color.black));

        initDefaultUI();

    }

    public void initDefaultUI() {

        JPanel pushPanel = new JPanel(); //this JPanel is used to center the CharacterView in the ViewManager. It "pushes" down the CharacterView in the ViewManager
        pushPanel.setPreferredSize(new Dimension(Config.instance().getAreaViewWidth(), Config.instance().getAreaViewHeight()/6));
        pushPanel.setBackground(new Color(0f,0f,0f,0f)); //pushPanel needs to be invisible


        //this.containerPanel = new JPanel();
        this.setBackground(new Color(0f,0f,0f,0f));
        //this.containerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.setLayout(new FlowLayout());
        /* begin old layout code */
        //this.containerPanel.setPreferredSize(new Dimension(this.getCharacterViewWidth(),this.getCharacterViewHeight()));
        //this.containerPanel.setLayout(new GridLayout(1,3));
        //this.containerPanel.add(this.inventoryView);
        //this.containerPanel.add(this.equipmentView);
        //this.containerPanel.add(this.statsView);


        this.containerPanel = new View();
        this.containerPanel.setBackgroundImageFileName(("resources/skyCharacterView.png"));
        this.containerPanel.setPreferredSize(new Dimension(this.getCharacterViewWidth(),this.getCharacterViewHeight()));
        this.containerPanel.setLayout(new GridLayout(1,3));
        this.containerPanel.add(this.inventoryView);
        this.containerPanel.add(this.equipmentView);
        this.containerPanel.add(this.statsView);
        //this.containerPanel.setBackground(new Color(1f,1f,1f,0.8f));

        /* end old layout code */
        /* begin new layout edit */
        //this.inventoryView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*leftPanelProportion)), this.getCharacterViewHeight()));
        //this.equipmentView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*centerPanelProportion)), this.getCharacterViewHeight()));
        //this.statsView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*rightPanelProportion)), this.getCharacterViewHeight()));
        //this.containerPanel.setLayout(new BorderLayout());
        //this.containerPanel.add(this.inventoryView, BorderLayout.WEST);
        //this.containerPanel.add(this.equipmentView,BorderLayout.CENTER);
        //this.containerPanel.add(this.statsView, BorderLayout.EAST);
        /* end new layout edit */
        this.add(pushPanel);
        this.add(containerPanel);
        //this.setBorder(new LineBorder(Color.WHITE));
        this.containerPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(15.0f)));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
