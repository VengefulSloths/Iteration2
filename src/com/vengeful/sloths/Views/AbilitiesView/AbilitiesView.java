package com.vengeful.sloths.Views.AbilitiesView;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.*;
import com.vengeful.sloths.Views.View;
import com.vengeful.sloths.Views.ViewFactory.ItemViewObjectFactory;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class AbilitiesView extends View implements AbilityManagerObserver {

    private int viewIndex = 0;

    private ArrayList<AbilityViewObject> abilityList;
    private AbilityManager abilityManager;
    protected JPanel titlePanel;
    protected JPanel itemPanel;
    private int numRows;
    private int numCols;

    public ArrayList<AbilityViewObject> getItemList() {
        return abilityList;
    }
    public void setItemList(ArrayList<AbilityViewObject> itemList) {
        this.abilityList = itemList;
    }
    protected void setAbilityManager(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }
    public AbilityManager getAbilityManager() {
        return this.abilityManager;
    }

    public JPanel getItemPanel() {
        return itemPanel;
    }
    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public int getNumCols() {
        return numCols;
    }
    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
    public int getNumRows() {
        return numRows;
    }
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void resetView(boolean isActiveView) {
        if (this.abilityManager.getCurrentSize() > 0) {
            AbilityViewObject item = this.getFromItemList(this.viewIndex);

            if (item != null)
                this.setDeselected(this.getFromItemList(this.viewIndex));

            this.viewIndex = 0;

            item = this.getFromItemList(this.viewIndex);
            if (item != null && isActiveView)
                this.setSelected(this.getFromItemList(this.viewIndex));
        }
    }


    public void decrementViewIndex(int val) {
        System.out.println("View index BEFORE decrement: " + this.viewIndex);
        AbilityViewObject item = this.getFromItemList(this.viewIndex);


        if (item != null)
            this.setDeselected(this.getFromItemList(this.viewIndex));
        if (this.viewIndex - val <= 0) {
            this.viewIndex = 0;
        } else
            this.viewIndex -= val;


        item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.viewIndex));
    }

    public void incrementViewIndex(int val) {
        AbilityViewObject item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setDeselected(this.getFromItemList(this.viewIndex));

        if (this.viewIndex + val >= this.abilityManager.getCurrentSize()) {
            this.viewIndex = this.abilityManager.getCurrentSize() - 1;
        } else
            this.viewIndex += val;


        item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.viewIndex));

    }

    public int selectNorthItem() {

        decrementViewIndex(numCols);;

        return viewIndex;
    }

    public int selectSouthItem() {
        incrementViewIndex(numCols);;

        return viewIndex;
    }

    public int selectEastItem() {
        incrementViewIndex(1);

        return viewIndex;
    }

    public int selectWestItem() {
        decrementViewIndex(1);

        return viewIndex;
    }

    public Ability getCurrentItem() {
        Ability ability = this.getAbilityManager().getAbilityByIndex(this.viewIndex);
        return ability;
    }

    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
    }

    public AbilitiesView() { }

    public AbilitiesView(AbilityManager abilityManager) { //edit: can maybe delete later
        //this.itemList = new ArrayList<AbilityViewObject>();
        this.setItemList(new ArrayList<>());
        this.setAbilityManager(abilityManager);

        //Create a proxy for the observer, register the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyAbilityManagerObserver(this, this.abilityManager);
        ObserverManager.getInstance().addProxyObserver(pio);


        initWithInventory(this.getAbilityManager());
        initDefaultUI();
    }

    public AbilitiesView(AbilityManager abilityManager, int numRows, int numCols) {
        this.setNumCols(numCols);
        this.setNumRows(numRows);

        this.setItemList(new ArrayList<>());
        this.setAbilityManager(abilityManager);
        //Create a proxy for the observer, register the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyAbilityManagerObserver(this, abilityManager);

        ObserverManager.getInstance().addProxyObserver(pio);

        initWithInventory(this.getAbilityManager());
        initDefaultUI();
    }

    public void initDefaultUI() {
        //JPanel titlePanel = new JPanel();
        //JPanel itemPanel = new JPanel();
        titlePanel = new JPanel();
        itemPanel = new JPanel();
        JLabel title = generateTitleLabel("Abilities", 22, Color.WHITE);
        //itemPanel.setBorder(new LineBorder(Color.BLACK));
        //this.itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));
        titlePanel.setBackground(new Color(0f, 0f, 0f, 0f));
        itemPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        this.setBackground(new Color(0f,0f,0f,0.3f));
        titlePanel.setLayout(new BorderLayout());
        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        //itemPanel.setLayout(new GridBagLayout());
        itemPanel.setLayout(new GridLayout(10,1,0,0));
        this.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(itemPanel, BorderLayout.CENTER);
    }

    /* Initializes the itemList by generating ItemViewObjects from inventoryItems. Maybe make a factory? */
    public void initWithInventory(AbilityManager abilityManager) {
        System.out.println("Ability Manager contents: ");
        for (int i = 0; i < abilityManager.getCurrentSize(); ++i) {
            System.out.println("index : " + i);
            System.out.println(abilityManager.getAbilityByIndex(i));
            this.getItemList().add(new AbilityViewObject(abilityManager.getAbilityByIndex(i)));
        }
    }


    /*Removes InventoryItemViewObject from the itemList */
    public AbilityViewObject removeInventoryItemViewObject(InventoryItem item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        int index = 0;
        AbilityViewObject ivo = null;

        for (int i = 0; i < this.getItemList().size(); i++) {
            if (this.getItemList().get(i).getViewItem().equals(item)) {
                ivo = (AbilityViewObject) this.getItemList().get(i);
                ivo.setIsDisplayed(false); //edit?
                this.getItemList().remove(i);
            }
        }

        return ivo;
    }


    public int getItemListSize(){
        return this.abilityList.size();
    }

    public AbilityViewObject getFromItemList(int index) {
        try {
            return abilityList.get(index);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void equipAbility(int slot) {
        if (this.viewIndex <= 0 || this.viewIndex > this.abilityList.size()) this.viewIndex = 0;
        if (this.abilityList.size() == 0) return;

        AbilityViewObject avo = this.abilityList.get(this.viewIndex);
        System.out.println("Attempting to equip ability: " + avo.getAbility().getItemName());
        this.abilityManager.equipAbility(avo.getAbility(), slot);
    }

    public void setSelected(AbilityViewObject item){
        //give a border
        item.setSelected(true);
    }

    public void setDeselected(AbilityViewObject item){
        //give a border
        item.setSelected(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
        for(AbilityViewObject e: itemList) {
            e.paintComponent(g);
        }
        */
    }

    @Override
    public void alertAbilityAdded(Ability ability) {
        System.out.println("alert ability added");
        this.getItemList().add(new AbilityViewObject(ability));
    }

    @Override
    public void alertAbilityEquipped(Ability ability, int index) {

    }

    @Override
    public void alertAbilityUnequipped(Ability ability, int index) {

    }

}


