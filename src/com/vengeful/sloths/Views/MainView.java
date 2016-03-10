package com.vengeful.sloths.Views;


import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.HUDView.HUDView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.StatsView.StatsView;
import com.vengeful.sloths.Views.ViewManager.ViewManager;
import com.vengeful.sloths.Utility.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by echristiansen on 2/21/2016.
 */
//public class MainView extends JFrame {
    public class MainView extends JPanel {

    int width = (int) Config.instance().getWindowWidth();
    int height = (int) Config.instance().getWindowHeight();

    public MainView() {

        this.setLayout(new BorderLayout());
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        //this.setLocationRelativeTo(null);
        //JPanel mainPanel = new JPanel();
        //mainPanel.setBackground(Color.cyan);
        //this.add(mainPanel);

    }



}
