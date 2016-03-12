package com.vengeful.sloths.Views.EquipmentView;

import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Weapon;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.EquipmentObserver;
import com.vengeful.sloths.Models.Observers.ProxyEquipmentObserver;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class EquipmentView extends View implements EquipmentObserver {

    private JPanel titlePanel;
    private JPanel itemPanel;
    private Equipped equipment;

    private EquipmentViewObject hat;
    private EquipmentViewObject weapon;
    private EquipmentViewObject mount;

    public Equipped getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipped equipment) {
        this.equipment = equipment;
    }


    public EquipmentViewObject getHat() {
        return hat;
    }
    public void setHat(EquipmentViewObject hat) {
        this.hat = hat;
    }
    public EquipmentViewObject getWeapon() {
        return weapon;
    }
    public void setWeapon(EquipmentViewObject weapon) {
        this.weapon = weapon;
    }
    public EquipmentViewObject getMount() {
        return mount;
    }
    public void setMount(EquipmentViewObject mount) {
        this.mount = mount;
    }


    public EquipmentView(Equipped equipment) {
        this.equipment = equipment;
        ProxyObserver pio = new ProxyEquipmentObserver(this, equipment);
        ObserverManager.getInstance().addProxyObserver(pio);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        /* begin triangle drawing */ /*
        int vert1x = (int)((1.0/3)*this.itemPanel.getWidth());
        int vert2x = (int)((1.0/2)*this.itemPanel.getWidth());
        int vert3x = (int)((2.0/3)*this.itemPanel.getWidth());
        int vert1y = (int)((2.0/3)*this.itemPanel.getHeight());
        int vert2y = (int)((1.0/3)*this.itemPanel.getHeight());
        int vert3y = (int)((2.0/3)*this.itemPanel.getHeight());
        System.out.println("VERT 1X IS " + vert1x + ", VERT2X is " + vert2x + "VERT3X IS " + vert3x);
        g.setColor(Color.GREEN);
        g.drawLine(vert1x, vert1y, vert2x, vert2y);
        g.drawLine(vert2x, vert2y, vert3x, vert3y);
        g.drawLine(vert3x, vert3y, vert1x, vert1y);
       */ /* end triangle drawing */

        /*begin vertically aligned box drawing */
        int widthDivisor = 16;
        int heightDivisor = 12;
        int lineVertMultiple = (int) (heightDivisor / 3.0);
        int lineDrawX = this.itemPanel.getWidth() / 2;
        int lineHor = this.itemPanel.getWidth() / widthDivisor;
        int lineVert = this.itemPanel.getHeight() / heightDivisor;
        int boxXCoord = lineHor;
        int box1Y = lineVert + this.titlePanel.getHeight() + 10;
        int box2Y = box1Y + lineVertMultiple * lineVert;
        int box3Y = box2Y + lineVertMultiple * lineVert;
        int boxWidth = 14 * lineHor;
        int boxHeight = 2 * lineVert;
        int drawStringHorMultiple = (int) ((1 / 2.0) * widthDivisor);
        g.setColor(Color.BLACK);
        g.drawRect(boxXCoord, box1Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box2Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box3Y, boxWidth, boxHeight);
        //g.drawLine(lineDrawX, box1Y+boxHeight, lineDrawX, box1Y+2*boxHeight);
        //g.drawLine(lineDrawX, box2Y+boxHeight, lineDrawX, box2Y+2*boxHeight);
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        String headGearString = "Headgear";
        String weaponString = "Weapon";
        String mountString = "Mount";
        int headGearStringWidth = g.getFontMetrics().stringWidth(headGearString);
        int weaponStringWidth = g.getFontMetrics().stringWidth(weaponString);
        int mountStringWidth = g.getFontMetrics().stringWidth(mountString);
        g.drawString("Headgear", lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * headGearStringWidth), box1Y - 10);
        g.drawString("Weapon", lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * weaponStringWidth), box2Y - 10);
        g.drawString("Mount", lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * mountStringWidth), box3Y - 10);
        /* end vertically aligned box drawing */

        if(this.getHat()!=null) {
            this.getHat().paintComponent(g, boxXCoord, box1Y, boxWidth, boxHeight);
        }
        if(this.getWeapon()!=null) {
            this.getWeapon().paintComponent(g, boxXCoord, box2Y, boxWidth, boxHeight);
        }
        if((this.getMount()!=null)) {
            this.getMount().paintComponent(g, boxXCoord, box3Y, boxWidth, boxHeight);
        }

    }

    @Override
    public void alertWeaponEquipped(Weapon weapon) {
        this.setWeapon(new EquipmentViewObject(weapon));
    }

    @Override
    public void alertWeaponUnequipped(Weapon weapon) {
        this.setWeapon(null);
    }

    @Override
    public void alertHatEquipped(Hat hat) {
        this.setHat(new EquipmentViewObject(hat));
    }

    @Override
    public void alertHatUnequipped(Hat hat) {
        this.setHat(null);
    }

    @Override
    public void alertMountEquipped(Mount mount) {
        this.setMount(new EquipmentViewObject(mount));
    }

    @Override
    public void alertMountUnequipped(Mount mount) {
        this.setMount(null);
    }

}


