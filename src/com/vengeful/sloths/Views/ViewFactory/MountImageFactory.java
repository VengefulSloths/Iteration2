package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class MountImageFactory extends EquipmentImageFactory {

    public MountImageFactory() {
        super();
    }

    public String generateItemImageFileName(ViewItem mount) {
        Mount m = (Mount) mount;
        String imageFileName = "resources/EquipmentItems/" + m.getName() + ".png";
        //System.out.println("ATTEMPTING TO PRINT ITEM NAME MOUNT? " + m.getName());
        return imageFileName;
    }
}
