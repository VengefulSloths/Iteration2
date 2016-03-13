package com.vengeful.sloths.Views.ViewFactory;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class EquipmentImageFactory extends ItemImageFactory {

    public EquipmentImageFactory() {
        super();
    }

    public String generateItemImageFileName(ViewItem equipment) {
        System.out.println(equipment);
        String imageFileName = "resources/EquipmentItems/" + equipment.getItemName() + ".png";
        return imageFileName;
    }

}
