package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;
import com.vengeful.sloths.Views.ViewFactory.TextLabelFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by lenovo on 3/1/2016.
 */
public class ListItemViewObject extends ItemViewObject {

    private ItemImageFactory imageFactory = new ItemImageFactory();
    private TextLabelFactory textLabelFactory = new TextLabelFactory();
    private JLabel itemImage;
    //private Image itemImage;
    private JLabel itemLabel;
    private JPanel itemObjectPanel; //we have nested panels here because we may want to resize this nested panel
    //even though its parent may have a gridlayout. This may or may not work and may or may not be necessary
    //private InventoryItem item;

    /* Constructor with itemName (will be item object), inventoryView width, inventoryView height, horizontal and vert scale */
    //public ListItemViewObject(String item, int containerWidth, int containerHeight, double horScale, double vertScale) {
    public ListItemViewObject(InventoryItem item, int containerWidth, int containerHeight, double horScale, double vertScale) {
        this.setViewItem(item);
        //this.itemImage = imageFactory.handleScaledItemImageGeneration(this.getInventoryItem(), containerWidth, containerHeight);
        this.itemImage = imageFactory.handleScaledImageLabelGeneration(this.getViewItem(), containerWidth, containerHeight);
        initDefaultAppearance();
    }

    //public ListItemViewObject(String item) {
    public ListItemViewObject(InventoryItem item) {
        this.setViewItem(item);
        //this.itemImage = this.imageFactory.handleUnscaledItemImageGeneration(this.getInventoryItem());
        this.itemImage = this.imageFactory.handleUnscaledImageLabelGeneration(this.getViewItem());
        initDefaultAppearance();
    }

    public void initDefaultAppearance() {

        this.setBackground(new Color(0f,0f,0f,0f));
        this.itemLabel = this.textLabelFactory.generateNameLabel(this.getViewItem().getItemName(), 12, Color.white);
        this.itemObjectPanel = new JPanel();
        FlowLayout flow = new FlowLayout();
        flow.setAlignment(FlowLayout.CENTER);
        flow.setHgap(100);
        this.itemObjectPanel.setLayout(flow);
        this.itemObjectPanel.setBackground(new Color(0f,0f,0f,0f));
        this.itemObjectPanel.add(this.itemImage);
        this.itemObjectPanel.add(this.itemLabel);
        //this.itemObjectPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color. BLACK, Color.WHITE));
        this.itemObjectPanel.setBorder(new LineBorder(Color.WHITE));
        //Dimension objectPanelDimension = new Dimension((int)0.05*containerWidth, (int) 0.10*containerHeight); //edit?
        //itemObjectPanel.setPreferredSize(objectPanelDimension); //edit?
        this.add(this.itemObjectPanel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawString("TESTING TESTING 124", 4, 50);

    }

    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);
        System.out.println("SHIT'S GETTING CALLED!!!");
    }

}


