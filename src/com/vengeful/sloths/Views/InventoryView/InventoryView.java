package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.InventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;


import com.vengeful.sloths.Utility.RealTuple;
import com.vengeful.sloths.Views.View;
import com.vengeful.sloths.Views.ViewFactory.ItemViewObjectFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class InventoryView extends View implements InventoryObserver {

    private int inventoryIndex = 0;

    private ArrayList<ItemViewObject> itemList;
    private ItemViewObjectFactory ivoFactory;
    private Inventory inventory;
    //private JPanel titlePanel;
    //private JPanel itemPanel; //use this perhaps if we are just adding and removing JComponents using this.add() and this.remove()
    protected JPanel titlePanel;
    protected JPanel itemPanel;
    //private JLabel goldAmmount;
    private int numRows;
    private int numCols;
    private GoldView goldPanel;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public ArrayList<ItemViewObject> getItemList() {
        return itemList;
    }
    public void setItemList(ArrayList<ItemViewObject> itemList) {
        this.itemList = itemList;
    }
    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public JPanel getItemPanel() {
        return itemPanel;
    }

    public int getInventoryIndex() { return this.inventoryIndex; }

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

    public void resetInventoryView() {
        if (this.inventory.getCurrentSize() > 0) {
            ItemViewObject item = this.getFromItemList(this.inventoryIndex);

            if (item != null)
                this.setDeselected(this.getFromItemList(this.inventoryIndex));

            this.inventoryIndex = 0;

            item = this.getFromItemList(this.inventoryIndex);
            if (item != null)
                this.setSelected(this.getFromItemList(this.inventoryIndex));
        }
    }


    public void decrementInventoryIndex(int val) {
        System.out.println("Inventory index BEFORE decrement: " + this.inventoryIndex);
        ItemViewObject item = this.getFromItemList(this.inventoryIndex);


        if (item != null)
            this.setDeselected(this.getFromItemList(this.inventoryIndex));
        if (this.inventoryIndex - val <= 0) {
            this.inventoryIndex = 0;
        } else
            this.inventoryIndex -= val;


        item = this.getFromItemList(this.inventoryIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.inventoryIndex));

        System.out.println("Inventory size is: " + this.getInventory().getCurrentSize());
    }

    public void incrementInventoryIndex(int val) {
        ItemViewObject item = this.getFromItemList(this.inventoryIndex);
        if (item != null)
            this.setDeselected(this.getFromItemList(this.inventoryIndex));

        if (this.inventoryIndex + val >= this.inventory.getCurrentSize()) {
            this.inventoryIndex = this.inventory.getCurrentSize() - 1;
        } else
            this.inventoryIndex += val;


        item = this.getFromItemList(this.inventoryIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.inventoryIndex));

        System.out.println("Inventory size is: " + this.getInventory().getCurrentSize());

    }

    public int selectNorthItem() {

        decrementInventoryIndex(numCols);

        return inventoryIndex;
    }

    public int selectSouthItem() {
        incrementInventoryIndex(numCols);

        return inventoryIndex;
    }

    public int selectEastItem() {
        incrementInventoryIndex(1);

        return inventoryIndex;
    }

    public int selectWestItem() {
        decrementInventoryIndex(1);

        return inventoryIndex;
    }

    public InventoryItem getCurrentItem() {
        if (this.itemList.isEmpty()) return null;

        InventoryItem item = (InventoryItem) this.itemList.get(this.inventoryIndex).getViewItem();
        return item;
    }

    public void useCurrentlySelectedItem() {
        if (this.inventory.getCurrentSize() <= 0) return;

        InventoryItem item = (InventoryItem) this.itemList.get(this.inventoryIndex).getViewItem();

        if (item != null) {
            decrementInventoryIndex(0);
            item.interact();
            this.getInventory().removeItem(item);
            this.dropViewItem();
        }
    }

    public InventoryView() { }

    public InventoryView(Inventory inventory) { //edit: can maybe delete later
        //this.itemList = new ArrayList<AbilityViewObject>();
        this.setItemList(new ArrayList<ItemViewObject>());
        this.setInventory(inventory);
        //Create a proxy for the observer, regester the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyInventoryObserver(this, inventory);
        ObserverManager.getInstance().addProxyObserver(pio);

        initWithInventory(this.getInventory());
        initDefaultUI();
    }

    public InventoryView(Inventory inventory, int numRows, int numCols) {
        this.setNumCols(numCols);
        this.setNumRows(numRows);
        //this.itemList = new ArrayList<AbilityViewObject>();
        this.setItemList(new ArrayList<ItemViewObject>());
        this.setInventory(inventory);
        //Create a proxy for the observer, regester the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyInventoryObserver(this, inventory);

        ObserverManager.getInstance().addProxyObserver(pio);

        initWithInventory(this.getInventory());
        initDefaultUI();
    }

    public void initDefaultUI() {
        //this.setBackgroundImageFileName("resources/skyInventory2.png");
        this.setBackground(new Color(0f,0f,0f,0.3f));
        titlePanel = new JPanel();
        itemPanel = new JPanel();
        //goldPanel = new JPanel();
        String label = getInvLabel();
        JLabel title = generateTitleLabel(label, 22, Color.WHITE);

        //itemPanel.setBorder(new LineBorder(Color.BLACK));
        //this.itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));
        goldPanel = new GoldView(inventory);
        goldPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        goldPanel.setPreferredSize(new Dimension(this.getWidth(), 40));


        titlePanel.setBackground(new Color(0f, 0f, 0f, 0f));
        itemPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        titlePanel.setLayout(new BorderLayout());
        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        //itemPanel.setLayout(new GridBagLayout());
        itemPanel.setLayout(new GridLayout(10,1,0,0));
        this.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(itemPanel, BorderLayout.CENTER);
        this.add(goldPanel, BorderLayout.SOUTH);
        //this.setBorder(new LineBorder(Color.WHITE));
    }

    protected void setString(String s) {
        //do nothing
    }

    protected String getInvLabel() {
        return "Inventory";
    }

    /* Initializes the itemList by generating ItemViewObjects from inventoryItems. Maybe make a factory? */
    public void initWithInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getCurrentSize(); ++i) {
            this.getItemList().add(new ItemViewObject(inventory.getItem(i)));
        }
    }


    /*Removes InventoryItemViewObject from the itemList */
    public ItemViewObject removeInventoryItemViewObject(InventoryItem item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        int index = 0;
        ItemViewObject ivo = null;
        System.out.println("YOU ARE TRYING TO DROP " + item.getItemName());

        for (int i = 0; i < this.getItemList().size(); i++) {
            if (this.getItemList().get(i).getViewItem().equals(item)) {
                ivo = (ItemViewObject) this.getItemList().get(i);
                System.out.println("ACTUALLY DROPPING " + ivo.getViewItem().getItemName());
                ivo.setIsDisplayed(false); //edit?
                //this.getItemList().remove(i);
                //this.getItemList().remove(ivo);
                this.getItemList().remove(inventoryIndex);
            }
        }
        return ivo;
    }

    @Override
    public void alertItemAdded(InventoryItem item) {
        //manager.addInventoryItemViewObject(new AbilityViewObject(item));
        System.out.println("Item is : " + item);
        RealTuple<ItemViewObject, InventoryItem> itemTuple = new RealTuple<>(new ItemViewObject(item), item);
        this.getItemList().add(new ItemViewObject(item));
        System.out.println("AN ITEM WAS ADDED!!! " + item.getItemName());
    }

    @Override
    public void alertItemDropped(InventoryItem item) {
       //this.removeInventoryItemViewObject(item); //what if have multiple of the same items?
        decrementInventoryIndex(1);
    }

    public void dropViewItem(){
        itemList.remove(inventoryIndex);
    }
    public int getItemListSize(){
        return this.itemList.size();
    }

    public ItemViewObject getFromItemList(int index) {
        try {
            return itemList.get(index);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void setSelected(ItemViewObject item){
        //give a border
        item.setSelected(true);
    }

    public void setDeselected(ItemViewObject item){
        //give a border
        item.setSelected(false);
    }

    public void paintComponent(Graphics g) {
        try {
            goldPanel.setText("Gold: " + inventory.getGold());
        }catch (NullPointerException e){
            //this is okay
        }
        super.paintComponent(g);
    }

}


