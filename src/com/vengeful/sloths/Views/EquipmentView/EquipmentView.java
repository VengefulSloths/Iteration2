package com.vengeful.sloths.Views.EquipmentView;

import com.vengeful.sloths.Models.Entity.Avatar;
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
import com.vengeful.sloths.Views.AbilitiesView.AbilityViewObject;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Array;
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
    private MountViewObject mount;

    private ArrayList<EquipmentViewObject> equipmentViewObjectList = new ArrayList<>(3); //needed?
    private EquipmentViewObject equipmentViewObjectArray[] = new EquipmentViewObject[3];
    private int selectionIndex;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public int getSelectionIndex() {
        return selectionIndex;
    }
    public void setSelectionIndex(int selectionIndex) {
        this.selectionIndex = selectionIndex;
    }
    public ArrayList<EquipmentViewObject> getEquipmentViewObjectList() {
        return equipmentViewObjectList;
    }
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
        this.equipmentViewObjectArray[0] = this.hat;
    }
    public EquipmentViewObject getWeapon() {
        return weapon;
    }
    public void setWeapon(EquipmentViewObject weapon) {
        this.weapon = weapon;
        this.equipmentViewObjectArray[1] = this.weapon;
    }
    public MountViewObject getMount() {
        return mount;

    }
    public void setMount(MountViewObject mount) {
        this.mount = mount;
        this.equipmentViewObjectArray[2] = this.mount;
    }


    public EquipmentView(Equipped equipment) {
        this.equipment = equipment;
        initializeEquipment(equipment);
        ProxyObserver pio = new ProxyEquipmentObserver(this, equipment);
        ObserverManager.getInstance().addProxyObserver(pio);
        initDefaultUI();
        this.selectionIndex=0;
    }

    public void resetView(boolean isActiveView) {
        this.selectionIndex = 0;
    }

    public void initializeEquipment(Equipped equipment) {
        if (equipment.getHat()!=null) {
            this.setHat(new EquipmentViewObject(equipment.getHat()));
        }
        if (equipment.getWeapon()!=null) {
            this.setWeapon(new EquipmentViewObject(equipment.getWeapon()));
        }
        if (equipment.getMount()!=null) {
            this.setMount(new MountViewObject(equipment.getMount()));
        }

    }

    public void initDefaultUI() {
        this.titlePanel = new JPanel();
        this.itemPanel = new JPanel();
        this.setBackground(new Color(0f,0f,0f,0.3f));
        JLabel title = generateTitleLabel("Equipment", 22, Color.WHITE);
        this.add(title);
        this.titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.itemPanel.setBackground(new Color(0f,0f,0f,0f));
        this.setLayout(new BorderLayout());
        GridLayout grid = new GridLayout(10,1); //adjust this if want different arrangements
        this.itemPanel.setLayout(grid);
        this.titlePanel.setLayout(new BorderLayout());
        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        this.titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(itemPanel, BorderLayout.CENTER);
    }

    public void addItem(ItemViewObject item) {
        this.itemPanel.add(item);
    }

    public int selectNorthItem() {
        if(this.selectionIndex < 1) {
            return this.getSelectionIndex();
        } else {
            if(this.equipmentViewObjectArray[selectionIndex]!=null) {
                this.equipmentViewObjectArray[selectionIndex].setSelected(false);
            }
            this.selectionIndex--;
            if(this.equipmentViewObjectArray[selectionIndex]!=null) {
                this.equipmentViewObjectArray[selectionIndex].setSelected(true);
            }
            System.out.println("THE SELECTION INDEX IS NOW!!: " + this.getSelectionIndex());
            return this.getSelectionIndex();
        }
    }

    public int selectSouthItem() {
        if(this.selectionIndex>1) {
            return this.getSelectionIndex();
        } else {
            if(this.equipmentViewObjectArray[selectionIndex]!=null) {
                this.equipmentViewObjectArray[selectionIndex].setSelected(false);
            }
            this.selectionIndex++;
            if(this.equipmentViewObjectArray[selectionIndex]!=null) {
                this.equipmentViewObjectArray[selectionIndex].setSelected(true);
            }
            System.out.println("THE SELECTION INDEX IS NOW!!: " + this.getSelectionIndex());
            return this.getSelectionIndex();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        g.setColor(Color.WHITE);
        g.drawRect(boxXCoord, box1Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box2Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box3Y, boxWidth, boxHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        String headGearString = "Headgear";
        String weaponString = "Weapon";
        String mountString = "Mount";
        int headGearStringWidth = g.getFontMetrics().stringWidth(headGearString);
        int weaponStringWidth = g.getFontMetrics().stringWidth(weaponString);
        int mountStringWidth = g.getFontMetrics().stringWidth(mountString);
        int stringHeight = g.getFontMetrics().getHeight();
        g.drawString(headGearString, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * headGearStringWidth), box1Y - 10);
        g.drawString(weaponString, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * weaponStringWidth), box2Y - 10);
        g.drawString(mountString, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * mountStringWidth), box3Y - 10);
        g.drawLine(lineDrawX, box1Y + boxHeight, lineDrawX, box1Y + 2 * boxHeight - stringHeight);
        g.drawLine(lineDrawX, box2Y + boxHeight, lineDrawX, box2Y + 2 * boxHeight - stringHeight);


        /* end vertically aligned box drawing */

        if (this.equipmentViewObjectArray[0] != null) {
            this.equipmentViewObjectArray[0].paintComponent(g, boxXCoord, box1Y, boxWidth, boxHeight);
        }
        if (this.equipmentViewObjectArray[1] != null) {
            this.equipmentViewObjectArray[1].paintComponent(g, boxXCoord, box2Y, boxWidth, boxHeight);
        }
        if (this.equipmentViewObjectArray[2] != null) {
            this.equipmentViewObjectArray[2].paintComponent(g, boxXCoord, box3Y, boxWidth, boxHeight);
        }

        //want to call paintComponent even on null slots because then they can say nothing displayed??

            Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN);
            if (this.selectionIndex == 0 && this.isSelected()) { //edit
                b.paintBorder(this.getHat(), g, boxXCoord, box1Y, boxWidth, boxHeight);
            } else if (this.selectionIndex==0 && !this.isSelected()) {
                b = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color. WHITE);
                b.paintBorder(this.getHat(), g, boxXCoord, box1Y, boxWidth, boxHeight);
            }

        }


    public void unequipItem() {
        if(selectionIndex==0 && this.equipmentViewObjectArray[0]!=null) {
            this.getHat().getEquipmentItem().removeFromEquipped(this.equipment);
            this.equipmentViewObjectArray[0]=null;
            //this.revalidate();
            //this.repaint();
        } else if(selectionIndex==1 && this.equipmentViewObjectArray[1]!=null) {
            this.getWeapon().getEquipmentItem().removeFromEquipped(this.equipment);
            this.equipmentViewObjectArray[1]=null;
        } else if(selectionIndex==2 && this.equipmentViewObjectArray[2]!=null) {
            //this.getMount().getEquipmentItem().removeFromEquipped(this.equipment);
            System.out.println("remioving " + this.getMount().getMount() + " from equipment");
            this.getMount().getMount().removeFromEquipped(this.equipment);
            this.equipmentViewObjectArray[2]=null;
        }

    }

        @Override
        public void alertWeaponEquipped(Weapon weapon) {
            if (this.getWeapon() != null) {
                Avatar.getInstance().getInventory().addItem(this.getWeapon().getEquipmentItem());
            }
            this.setWeapon(new EquipmentViewObject(weapon));
        }

        @Override
        public void alertWeaponUnequipped(Weapon weapon) {
            this.setWeapon(null);
        }

        @Override
        public void alertHatEquipped(Hat hat) {
            if (this.getHat() != null) {
                Avatar.getInstance().getInventory().addItem(this.getHat().getEquipmentItem());
            }
            this.setHat(new EquipmentViewObject(hat));
        }

        @Override
        public void alertHatUnequipped(Hat hat) {
            this.setHat(null);
        }

        @Override
        public void alertMountEquipped(Mount mount) {
            if (this.getMount() != null) {
                System.out.println("MOUNT  IS: " + this.getMount().getEquipmentItem());
                Avatar.getInstance().getInventory().addItem(this.getMount().getEquipmentItem());
            }
            this.setMount(new MountViewObject(mount));
        }

        @Override
        public void alertMountUnequipped(Mount mount) {
            this.setMount(null);
        }

    public void setSelected(EquipmentViewObject equipment){
        //give a border
        equipment.setSelected(true);
    }

    public void setDeselected(EquipmentViewObject equipment){
        //give a border
        equipment.setSelected(false);
    }




}


