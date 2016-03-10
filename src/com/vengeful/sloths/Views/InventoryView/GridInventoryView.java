package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Observers.InventoryObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lenovo on 3/8/2016.
 */
public class GridInventoryView extends InventoryView implements InventoryObserver {

    private ArrayList<ItemViewObject> itemList;
    private int numRows;
    private int numCols;

    public int getNumRows() {
        return numRows;
    }
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }
    public int getNumCols() {
        return numCols;
    }
    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public GridInventoryView(Inventory inventory) {
        super(inventory);
        this.setNumRows(5);
        this.setNumCols(4);
    }

    public GridInventoryView(Inventory inventory, int numRows, int numCols) {
        super(inventory);
        this.numRows=numRows;
        this.numCols=numCols;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x;
        int y;
        int titlePanelWidth = this.getTitlePanel().getWidth();
        int titlePanelHeight = this.getTitlePanel().getHeight();
        int boxWidth = (int)((this.getItemPanel().getWidth()) * (2.0/((3*numCols)+1))); //this doesn't work because the x and y are relative to the inventory as a whole. Might need to move the paint to ItemPanel class.
        int boxHeight = (int)((this.getItemPanel().getHeight()) * (2.0/((3*numRows)+1))); //this doesn't work because the x and y are relative to the inventory as a whole. Might need to move the paint.
        int multipleX = 1;
        int multipleY = 1;

        GridCalculationStrategy gcs = new GridCalculationStrategy();

        for(int i=0; i<this.getItemList().size(); i++) {
            this.getItemList().get(i).paintComponent(g, gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i), gcs.calculateSlotWidth(), gcs.calculateSlotHeight());
        }

        for (int i = 0; i < this.getNumRows(); i++) { //for each column
            for (int j = 0; j < this.getNumCols(); j++) { //for each row
                y = ((boxHeight / 2) * multipleY) + titlePanelHeight; //the y coord to draw at is half the boxHeight * the Y multiple (which increments by 3)
                x = (boxWidth / 2) * multipleX; //the x coord to draw at is half the boxWwidth * the X multiple
                g.drawRect(x, y, boxWidth, boxHeight);
                multipleX +=3;
            }
            multipleX=1;
            multipleY+=3;
        }
    }

    private class GridCalculationStrategy {

        public GridCalculationStrategy() {

        }

        public int calculateXCoordBasedOnIndex(int index){
            //int xCoord = index%this.gridInventoryView.getNumCols();
            int columnSlotIndex = calculateColumnSlotNumberBasedOnIndex(index);
            int slotWidth = calculateSlotWidth();
            int multipleX = 1 + 3*index;
            int xCoord = (slotWidth/2)* multipleX;
            return xCoord;
        }

        public int calculateYCoordBasedOnIndex(int index) {

            int rowSlotIndex = calculateRowSlotNumberBasedOnIndex(index);
            int slotHeight = calculateSlotHeight();
            int yCoord = (slotHeight/2) + 3*rowSlotIndex + getTitlePanel().getHeight();
            return yCoord;
        }

        public int calculateColumnSlotNumberBasedOnIndex(int index){
            //int xCoord = index%this.gridInventoryView.getNumCols();
            int columnSlot = index%getNumCols();
            return columnSlot;
        }

        public int calculateRowSlotNumberBasedOnIndex(int index) {
            //int yCoord = index/this.gridInventoryView.getNumCols();
            int rowSlot = index/getNumRows();
            return rowSlot;
        }

        public int calculateSlotWidth() {
            //int slotWidth = (int)((this.gridInventoryView.getItemPanel().getWidth()) * (2.0/((3*this.gridInventoryView.getNumCols())+1)));
            //int slotWidth = (int)((getItemPanel().getWidth()) * (2.0/((3*this.gridInventoryView.getNumCols())+1)));
            int slotWidth = (int)((getItemPanel().getWidth()) * (2.0/((3*getNumCols())+1)));
            return slotWidth;
        }

        public int calculateSlotHeight() {
            //int slotHeight = (int)((this.gridInventoryView.getItemPanel().getHeight()) * (2.0/((3*this.gridInventoryView.getNumRows())+1)));
            //int slotHeight = (int)((getItemPanel().getHeight()) * (2.0/((3*this.gridInventoryView.getNumRows())+1)));
            int slotHeight = (int)((getItemPanel().getHeight()) * (2.0/((3*getNumRows())+1)));
            return slotHeight;
        }



    }

}
