package com.vengeful.sloths.Views.EquipmentView;

import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class EquipmentView extends View {

    private JPanel titlePanel;
    private JPanel itemPanel;
    private Equipped equipment;

    public Equipped getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipped equipment) {
        this.equipment = equipment;
    }

    public EquipmentView(Equipped equipment) {
        this.equipment = equipment;
        initDefaultUI();
    }

    public EquipmentView() {
     initDefaultUI();
    }

    public void initDefaultUI() {
        this.titlePanel = new JPanel();
        this.itemPanel = new JPanel();

        this.setBackgroundImageFileName("resources/inventoryBackground.png");
        JLabel title = generateTitleLabel("Equipment", 22, Color.WHITE);
        this.add(title);

        this.titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.itemPanel.setBackground(new Color(0f,0f,0f,0f));

        this.setLayout(new BorderLayout());
        GridLayout grid = new GridLayout(10,1); //adjust this if want different arrangements
        this.itemPanel.setLayout(grid);
        this.titlePanel.setLayout(new BorderLayout());

        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        //this.itemPanel.setBorder(new LineBorder(Color.BLACK))
        //this.itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));

        this.titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(itemPanel, BorderLayout.CENTER);
    }

    public void addItem(ItemViewObject item) {
        this.itemPanel.add(item);
    }

}
