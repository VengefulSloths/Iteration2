package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Observers.InventoryObserver;
import com.vengeful.sloths.Utility.Config;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lenovo on 3/8/2016.
 */
public class GridInventoryView extends InventoryView implements InventoryObserver {

    private ArrayList<ItemViewObject> itemList;

    public GridInventoryView(Inventory inventory) {
        super(inventory);
        this.setNumRows(5); //default numRows
        this.setNumCols(4); //default numCols
    }
    public GridInventoryView(){}

    public GridInventoryView(Inventory inventory, int numRows, int numCols) {
        super(inventory, numRows, numCols);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x;
        int y;
        int titlePanelWidth = this.getTitlePanel().getWidth();
        int titlePanelHeight = this.getTitlePanel().getHeight();
        int boxWidth = (int)((this.getItemPanel().getWidth()) * (2.0/((3*getNumCols())+1))); //this doesn't work because the x and y are relative to the inventory as a whole. Might need to move the paint to ItemPanel class.
        int boxHeight = (int)((this.getItemPanel().getHeight()) * (2.0/((3*getNumRows())+1))); //this doesn't work because the x and y are relative to the inventory as a whole. Might need to move the paint.
        int multipleX = 1;
        int multipleY = 1;

        g.setFont(new Font("Helvetica",1,15));
        GridCalculationStrategy gcs = new GridCalculationStrategy();
        for(int i=0; i<this.getItemListSize(); i++) { //edit: change the gridwidth to be 1 less?
            g.setColor(Color.WHITE);
            this.getFromItemList(i).paintComponent(g, gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i), gcs.calculateSlotWidth(), gcs.calculateSlotHeight());
            g.drawString(this.getFromItemList(i).getViewItem().getItemName(), gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i) + gcs.calculateSlotHeight() + boxHeight/4);
            if(this.getFromItemList(i).isSelected()) { //edit?
                Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE);
                b.paintBorder(this.getFromItemList(i), g, gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i), gcs.calculateSlotWidth(), gcs.calculateSlotHeight());
                //g.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
                //g.setColor(Color.WHITE);
                //g.drawString(this.getFromItemList(i).getViewItem().getItemName(), gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i) + gcs.calculateSlotHeight() + boxHeight/4);
            }
        }
//        g.setFont(new Font("Helvetica",1,40));
//        g.setColor(Color.orange);
//        g.drawString("Gold: "+ Avatar.getInstance().getInventory().getGold(),100,86);
        /*
        for (int i = 0; i < this.getNumRows(); i++) { //for each column
            for (int j = 0; j < this.getNumCols(); j++) { //for each row
                y = ((boxHeight / 2) * multipleY) + titlePanelHeight; //the y coord to draw at is half the boxHeight * the Y multiple (which increments by 3)
                x = (boxWidth / 2) * multipleX; //the x coord to draw at is half the boxWwidth * the X multiple
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxWidth, boxHeight);
                multipleX +=3;
            }
            multipleX=1;
            multipleY+=3;
        }
        */
    }

    private class GridCalculationStrategy {

        public GridCalculationStrategy() {

        }

        public int calculateXCoordBasedOnIndex(int index){
            int columnSlotIndex = calculateColumnSlotNumberBasedOnIndex(index);
            int slotWidth = calculateSlotWidth();
            int multipleX = 1 + 3*columnSlotIndex;
            int xCoord = (slotWidth/2)* multipleX;
            return xCoord;
        }

        public int calculateYCoordBasedOnIndex(int index) {

            int rowSlotIndex = calculateRowSlotNumberBasedOnIndex(index);
            int multipleY = 1 + 3*rowSlotIndex;
            int slotHeight = calculateSlotHeight();
            int lineLength = slotHeight/2;
            int yCoord = ((slotHeight/2) * multipleY) + getTitlePanel().getHeight();
            return yCoord;
        }

        public int calculateColumnSlotNumberBasedOnIndex(int index){
            int columnSlot = index%getNumCols();
            return columnSlot;
        }

        public int calculateRowSlotNumberBasedOnIndex(int index) {
            int rowSlot = index/getNumCols();
            return rowSlot;
        }

        public int calculateSlotWidth() {
            int slotWidth = (int)((getItemPanel().getWidth()) * (2.0/((3*getNumCols())+1)));
            return slotWidth;
        }

        public int calculateSlotHeight() {
            int slotHeight = (int)((getItemPanel().getHeight()) * (2.0/((3*getNumRows())+1)));
            return slotHeight;
        }



    }

}
