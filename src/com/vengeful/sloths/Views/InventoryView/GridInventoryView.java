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
  //  public Iterator<ItemViewObject> iterator() {
//        return itemList.iterator();
    //}


    public GridInventoryView(Inventory inventory) {
        super(inventory);
        //manager.initWithInventory(inventory);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        Iterator<ItemViewObject> iter = this.iterator();
        int boxWidth = (int) (this.getWidth()/6.5); //for a 5x3 inventory
        int boxHeight = this.getHeight()/8; // for a 5x3 inventory
        int multipleX = 1;
        int multipleY = 1;
        int x;
        int y;
        int numRows = 5;
        int numCols = 4;
        int columnPrintCounter = 0;
        int rowPrintCounter = 0;
        /*
        for (int i = 0; i < numCols; i++) { //for each column
            for (int j = 0; j < numRows; j++) { //for each row
                y = (boxHeight / 2) * multipleY; //the y coord to draw at is half the boxHeight * the Y multiple (which increments by 3)
                x = (boxWidth / 2) * multipleX; //the x coord to draw at is half the boxWwidth * the X multiple
                g.drawRect(x, y, boxWidth, boxHeight);
                //this.itemList.get(i).paintComponent(g,x,y,boxWidth,boxHeight);
                multipleY +=3;
            }
            multipleY=1;
            multipleX+=3;
        }
*/

        for(ItemViewObject e: this.getItemList()) {
            while(rowPrintCounter<numRows){
                while (columnPrintCounter<numCols) {
                    y=(boxHeight/2)*multipleY;
                    x=(boxWidth/2)*multipleX;
                        e.paintComponent(g, x, y, boxWidth, boxHeight);
                        e.setIsDisplayed(true);

                    /*
                    if(!e.isDisplayed()) {
                        e.paintComponent(g, x, y, boxWidth, boxHeight);
                        e.setIsDisplayed(true);
                        System.out.println("E IS NOT DISPLAYED!!!!!!!");
                    } */
                    multipleX+=3;
                    columnPrintCounter++;
                }
                columnPrintCounter=0;
                multipleX=1;
                multipleY+=3;
                rowPrintCounter++;
            }
        }

    }

}
