package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 3/5/2016.
 */
public class TextLabelFactory {

    public TextLabelFactory() {

    }

    //public JLabel generateItemNameLabel(InventoryItem item, int fontSize, Color color) {
    public JLabel generateNameLabel(String name, int fontSize, Color color) {
        JLabel titleLabel = new JLabel(name);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, fontSize);
        titleLabel.setForeground(color);
        titleLabel.setFont(font);
        //titleLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.GRAY, Color.BLACK));
        return titleLabel;
    }

    public JLabel generateStatsLabel(String stat, int fontSize, Color color) {
        JLabel statsLabel = new JLabel(stat);
        Font font = new Font(statsLabel.getFont().getName(), Font.BOLD, fontSize);
        statsLabel.setForeground(color);
        statsLabel.setFont(font);
        statsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        return statsLabel;
    }

}
