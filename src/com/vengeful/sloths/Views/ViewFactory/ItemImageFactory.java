package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 3/5/2016.
 */
public class ItemImageFactory extends ImageFactory {

    public ItemImageFactory() {

    }

    public String generateItemImageFileName(ViewItem item) {
        String imageFileName = "resources/InventoryItems/"+item.getItemName()+".png";
        return imageFileName;
    }


    public Image generateItemImageFromFile(String imageFileName) {
        ImageIcon itemImageIcon = new ImageIcon(imageFileName);
        Image itemImage = itemImageIcon.getImage();
        return itemImage;
    }

    public Image scaleItemImage(Image itemImage, int width, int height) {
        Image scaledImage=itemImage.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        return scaledImage;
    }

    public JLabel createItemImageLabel(Image itemImage) {
        JLabel picLabel = new JLabel(new ImageIcon(itemImage));
        return picLabel;
    }

    public Image handleScaledItemImageGeneration(ViewItem item, int width, int height) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        Image scaledImage = scaleItemImage(itemImage, width, height);
        return scaledImage;
    }

    public Image handleUnscaledItemImageGeneration(ViewItem item) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        return itemImage;
    }


    public JLabel handleUnscaledImageLabelGeneration(ViewItem item) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        JLabel itemImageJLabel = createItemImageLabel(itemImage);
        return itemImageJLabel;
    }



    public JLabel handleScaledImageLabelGeneration(ViewItem item, int viewWidth, int viewHeight) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        Image scaledImage = scaleItemImage(itemImage, viewWidth, viewHeight);
        JLabel scaledItemImageJLabel = createItemImageLabel(scaledImage);
        return scaledItemImageJLabel;
    }


        /*
    public Image scaleItemImage(Image itemImage, int viewWidth, int viewHeight, double horScale, double vertScale) {
        Image scaledImage=itemImage.getScaledInstance((int)(horScale*viewWidth),(int)(vertScale*viewHeight), Image.SCALE_SMOOTH);
        return scaledImage;
    }
    */

    /*
    public Image handleScaledItemImageGeneration(InventoryItem item, int viewWidth, int viewHeight, double horScale, double vertScale) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        Image scaledImage = scaleItemImage(itemImage, viewWidth, viewHeight, horScale, vertScale);
        return scaledImage;
    }
*/

    /*
    public Image handleGridScaledItemImageGeneration(InventoryItem item, int viewWidth, int viewHeight, double numRows, double numCols) {
        String imageFileName = generateItemImageFileName(item);
        Image itemImage = generateItemImageFromFile(imageFileName);
        Image scaledImage = scaleItemImage(itemImage, viewWidth, viewHeight, horScale, vertScale);
        return scaledImage;
    }
*/


}
