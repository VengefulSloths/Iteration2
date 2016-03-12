package com.vengeful.sloths.Views.AbilitiesView;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Observers.AbilityManagerObserver;
import com.vengeful.sloths.Models.Observers.InventoryObserver;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 3/8/2016.
 */
public class GridAbilitiesView extends AbilitiesView implements AbilityManagerObserver {

    private ArrayList<AbilityViewObject> itemList;

    public GridAbilitiesView(AbilityManager abilityManager) {
        super(abilityManager);
        this.setNumRows(5); //default numRows
        this.setNumCols(4); //default numCols
    }

    public GridAbilitiesView(AbilityManager abilityManager, int numRows, int numCols) {
        super(abilityManager, numRows, numCols);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x;
        int y;
        int titlePanelWidth = this.getTitlePanel().getWidth();
        int titlePanelHeight = this.getTitlePanel().getHeight();
        int boxWidth = (int)((this.getItemPanel().getWidth()) * (2.0/((3*getNumCols())+1))); //this doesn't work because the x and y are relative to the abilityManager as a whole. Might need to move the paint to ItemPanel class.
        int boxHeight = (int)((this.getItemPanel().getHeight()) * (2.0/((3*getNumRows())+1))); //this doesn't work because the x and y are relative to the abilityManager as a whole. Might need to move the paint.
        int multipleX = 1;
        int multipleY = 1;

        GridCalculationStrategy gcs = new GridCalculationStrategy();
        for(int i=0; i<this.getItemListSize(); i++) {
            this.getFromItemList(i).paintComponent(g, gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i), gcs.calculateSlotWidth(), gcs.calculateSlotHeight());
            System.out.println("IS SELECTED??? " + this.getFromItemList(i).isSelected());
            if(this.getFromItemList(i).isSelected()) {
                Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN);
                b.paintBorder(this.getFromItemList(i), g, gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i), gcs.calculateSlotWidth(), gcs.calculateSlotHeight());
                g.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
                g.setColor(Color.GREEN);
                g.drawString(this.getFromItemList(i).getViewItem().getItemName(), gcs.calculateXCoordBasedOnIndex(i), gcs.calculateYCoordBasedOnIndex(i) + gcs.calculateSlotHeight() + boxHeight/4);
            }
        }

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
            //int xCoord = index%this.gridInventoryView.getNumCols();
            int columnSlotIndex = calculateColumnSlotNumberBasedOnIndex(index);
            int slotWidth = calculateSlotWidth();
            int multipleX = 1 + 3*columnSlotIndex;
            int xCoord = (slotWidth/2)* multipleX;
            //System.out.println("TRYING TO PRINT OUT ITEM FOR INDEX " + index + "at xCoord" + xCoord + "!!!!!");
            return xCoord;
        }

        public int calculateYCoordBasedOnIndex(int index) {

            int rowSlotIndex = calculateRowSlotNumberBasedOnIndex(index);
            int multipleY = 1 + 3*rowSlotIndex;
            int slotHeight = calculateSlotHeight();
            int lineLength = slotHeight/2;
            int yCoord = ((slotHeight/2) * multipleY) + getTitlePanel().getHeight();
            //System.out.println("TRYING TO PRINT OUT ITEM FOR INDEX " + index + " at yCoord " + yCoord + "!!" + "ROWslotInDEX is " + rowSlotIndex + "for linelength " + lineLength + "with titlepanel height " + getTitlePanel().getHeight());

            return yCoord;
        }

        public int calculateColumnSlotNumberBasedOnIndex(int index){
            //int xCoord = index%this.gridInventoryView.getNumCols();
            int columnSlot = index%getNumCols();
            return columnSlot;
        }

        public int calculateRowSlotNumberBasedOnIndex(int index) {
            //int yCoord = index/this.gridInventoryView.getNumCols();
            //int rowSlot = index/getNumRows();
            int rowSlot = index/getNumCols();
            //System.out.println("THESE ARE THE ROWSLOTOOTTTTOOSSS!!! " + rowSlot + "for numRows " + getNumRows());
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
