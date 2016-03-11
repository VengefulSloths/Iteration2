package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.InventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;


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

    private ArrayList<ItemViewObject> itemList;
    private ItemViewObjectFactory ivoFactory;
    private Inventory inventory;
    //private JPanel titlePanel;
    //private JPanel itemPanel; //use this perhaps if we are just adding and removing JComponents using this.add() and this.remove()
    protected JPanel titlePanel;
    protected JPanel itemPanel;

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

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public InventoryView() { }

    public InventoryView(Inventory inventory) {
        //this.itemList = new ArrayList<ItemViewObject>();
        this.setItemList(new ArrayList<ItemViewObject>());
        this.setInventory(inventory);
        //Create a proxy for the observer, regester the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyInventoryObserver(this, inventory);

        ObserverManager.getInstance().addProxyObserver(pio);

        initWithInventory(this.getInventory());
        initDefaultUI();
    }

    public void initDefaultUI() {
        this.setBackgroundImageFileName("resources/inventoryBackground.png");
        //JPanel titlePanel = new JPanel();
        //JPanel itemPanel = new JPanel();
        titlePanel = new JPanel();
        itemPanel = new JPanel();
        JLabel title = generateTitleLabel("Inventory", 22, Color.WHITE);
        //itemPanel.setBorder(new LineBorder(Color.BLACK));
        //this.itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));
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
    }

    /* Initializes the itemList by generating ItemViewObjects from inventoryItems. Maybe make a factory? */
    public void initWithInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            //InventoryItem item = inventory.getItem(i);
            //this.itemList.add(new ItemViewObject(inventory.getItem(i)));
            //this.itemList.add(new ItemViewObject(inventory.getItem(i)));
            System.out.println("THIS IS THE ITEM " + inventory.getItem(i).getItemName() );
            this.getItemList().add(new ItemViewObject(inventory.getItem(i)));
            //this.getItemList().add(new ItemViewObject(inventory.getItem(i), this.getWidth(),this.getHeight()));
            //ivoFactory.generateItemViewObject(inventory.getItem(i), this.getWidth(), this.getHeight(), )
        }
    }


    /*Removes InventoryItemViewObject from the itemList */
    public ItemViewObject removeInventoryItemViewObject(InventoryItem item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        int index = 0;
        ItemViewObject ivo = null;

        for (int i = 0; i < this.getItemList().size(); i++) {
            if (this.getItemList().get(i).getInventoryItem().equals(item)) {
                ivo = (ItemViewObject) this.getItemList().get(i);
                ivo.setIsDisplayed(false); //edit?
                this.getItemList().remove(i);
            }
        }
        return ivo;
    }

    @Override
    public void alertItemAdded(InventoryItem item) {
        //manager.addInventoryItemViewObject(new ItemViewObject(item));
        this.getItemList().add(new ItemViewObject(item));
        System.out.println("AN ITEM WAS ADDED!!! " + item.getItemName());
    }

    @Override
    public void alertItemDropped(InventoryItem item) {
        this.removeInventoryItemViewObject(item); //what if have multiple of the same items?
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
        for(ItemViewObject e: itemList) {
            e.paintComponent(g);
        }
        */
    }

}


