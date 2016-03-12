package com.vengeful.sloths.Views.AbilitiesSkillsView;

import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Views.AbilitiesView.AbilitiesView;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.StatsView.StatsView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by zach on 3/11/16.
 */
public class AbilitiesSkillView extends JPanel {
    // public class CharacterViewManager extends JPanel {

    private AbilitiesView abilitiesView;
    private EquipmentView equipmentView;
    private StatsView statsView;
    private JPanel containerPanel;
    //private Inventory inventory;

    public AbilitiesView getAbilitiesView() {
        return abilitiesView;
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

    public AbilitiesSkillView(AbilitiesView av, EquipmentView ev, StatsView sv) {
        this.abilitiesView = av;
        this.equipmentView = ev;
        this.statsView = sv;

        initDefaultUI();

    }

    public AbilitiesSkillView() {

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

        this.containerPanel = new JPanel();
        this.setBackground(new Color(0f,0f,0f,0f));
        this.containerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.setLayout(new FlowLayout());
        this.containerPanel.setPreferredSize(new Dimension(this.getCharacterViewWidth(),this.getCharacterViewHeight()));
        this.containerPanel.setLayout(new GridLayout(1,3));
        this.containerPanel.add(this.abilitiesView);
//        this.containerPanel.add(this.equipmentView);
//        this.containerPanel.add(this.statsView);
        this.add(pushPanel);
        this.add(containerPanel);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
