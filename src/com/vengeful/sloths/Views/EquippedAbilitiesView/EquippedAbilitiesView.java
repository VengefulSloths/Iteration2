package com.vengeful.sloths.Views.EquippedAbilitiesView;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.*;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zschultz on 2/21/2016.
 */
public class EquippedAbilitiesView extends View implements AbilityManagerObserver {

    private JPanel titlePanel;
    private JPanel itemPanel;
    private AbilityManager abilityManager;

    private EquippedAbilitiesViewObject slot0;
    private EquippedAbilitiesViewObject slot1;
    private EquippedAbilitiesViewObject slot2;
    private EquippedAbilitiesViewObject slot3;

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public void setAbilityManager(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }

    public EquippedAbilitiesViewObject getSlot0() {
        return this.slot0;
    }
    public void setSlot0(EquippedAbilitiesViewObject viewObj) {
        this.slot0 = viewObj;
    }

    public EquippedAbilitiesViewObject getSlot1() {
        return slot1;
    }

    public void setSlot1(EquippedAbilitiesViewObject slot1) {
        this.slot1 = slot1;
    }

    public EquippedAbilitiesViewObject getSlot2() {
        return slot2;
    }

    public void setSlot2(EquippedAbilitiesViewObject slot2) {
        this.slot2 = slot2;
    }

    public EquippedAbilitiesViewObject getSlot3() {
        return slot3;
    }

    public void setSlot3(EquippedAbilitiesViewObject slot3) {
        this.slot3 = slot3;
    }


    public EquippedAbilitiesView(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
        ProxyObserver pio = new ProxyAbilityManagerObserver(this, abilityManager);
        ObserverManager.getInstance().addProxyObserver(pio);
        initDefaultUI();
    }


    public void initDefaultUI() {
        this.titlePanel = new JPanel();
        this.itemPanel = new JPanel();

        //this.setBackgroundImageFileName("resources/skyInventory2.png");
        this.setBackground(new Color(0f,0f,0f,0.3f));
        JLabel title = generateTitleLabel("Equipped Abilities", 22, Color.WHITE);
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
        //this.setBorder(new LineBorder(Color.WHITE));
    }

    public void addItem(ItemViewObject item) {
        this.itemPanel.add(item);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*begin vertically aligned box drawing */
        int widthDivisor = 16;
        int heightDivisor = 12;
        int lineVertMultiple = (int) (heightDivisor / 3.0);
        int lineDrawX = this.itemPanel.getWidth() / 2;
        int lineHor = this.itemPanel.getWidth() / widthDivisor;
        int lineVert = this.itemPanel.getHeight() / heightDivisor - 15;
        int boxXCoord = lineHor;
        int box1Y = lineVert + this.titlePanel.getHeight() + 10;
        int box2Y = box1Y + lineVertMultiple * lineVert;
        int box3Y = box2Y + lineVertMultiple * lineVert;
        int box4Y = box3Y + lineVertMultiple * lineVert;
        int boxWidth = 14 * lineHor;
        int boxHeight = 2 * lineVert;
        int drawStringHorMultiple = (int) ((1 / 2.0) * widthDivisor);
        g.setColor(Color.WHITE);
        g.drawRect(boxXCoord, box1Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box2Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box3Y, boxWidth, boxHeight);
        g.drawRect(boxXCoord, box4Y, boxWidth, boxHeight);
        g.drawLine(lineDrawX, box1Y+boxHeight, lineDrawX, box1Y+2*boxHeight);
        g.drawLine(lineDrawX, box2Y+boxHeight, lineDrawX, box2Y+2*boxHeight);
        g.drawLine(lineDrawX, box3Y+boxHeight, lineDrawX, box3Y+2*boxHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        String ability0String = "Ability 1";
        String ability1String = "Ability 2";
        String ability2String = "Ability 3";
        String ability3String = "Ability 4";
        int ability0StringWidth = g.getFontMetrics().stringWidth(ability0String);
        int ability1StringWidth = g.getFontMetrics().stringWidth(ability1String);
        int ability2StringWidth = g.getFontMetrics().stringWidth(ability2String);
        int ability3StringWidth = g.getFontMetrics().stringWidth(ability3String);
        g.drawString(ability0String, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * ability0StringWidth), box1Y - 10);
        g.drawString(ability1String, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * ability1StringWidth), box2Y - 10);
        g.drawString(ability2String, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * ability2StringWidth), box3Y - 10);
        g.drawString(ability3String, lineHor * drawStringHorMultiple - (int) ((1.0 / 2) * ability3StringWidth), box4Y - 10);
        /* end vertically aligned box drawing */

        if(this.getSlot0()!=null) {
            this.getSlot0().paintComponent(g, boxXCoord, box1Y, boxWidth, boxHeight);
        }
        if(this.getSlot1()!=null) {
            this.getSlot1().paintComponent(g, boxXCoord, box2Y, boxWidth, boxHeight);
        }
        if((this.getSlot2()!=null)) {
            this.getSlot2().paintComponent(g, boxXCoord, box3Y, boxWidth, boxHeight);
        }
        if((this.getSlot3()!=null)) {
            this.getSlot3().paintComponent(g, boxXCoord, box4Y, boxWidth, boxHeight);
        }
    }

    @Override
    public void alertAbilityAdded(Ability ability) {

    }

    @Override
    public void alertAbilityEquipped(Ability ability, int index) {
        switch (index) {
            case 0:
                System.out.println("Equipping to slot 0");
                this.setSlot0(new EquippedAbilitiesViewObject(ability));
                break;
            case 1:
                System.out.println("Equipping to slot 1");
                this.setSlot1(new EquippedAbilitiesViewObject(ability));
                break;
            case 2:
                System.out.println("Equipping to slot 2");
                this.setSlot2(new EquippedAbilitiesViewObject(ability));
                break;
            case 3:
                System.out.println("Equipping to slot 3");
                this.setSlot3(new EquippedAbilitiesViewObject(ability));
                break;
        }
    }

    @Override
    public void alertAbilityUnequipped(Ability ability, int index) {
        switch (index) {
            case 0:
                this.setSlot0(null);
                break;
            case 1:
                this.setSlot1(null);
                break;
            case 2:
                this.setSlot2(null);
                break;
            case 3:
                this.setSlot3(null);
                break;
        }
    }
}


