package com.vengeful.sloths.Views.ViewFactory;

/**
 * Created by zach on 3/12/16.
 */
public class AbilityImageFactory extends ItemImageFactory {

    public AbilityImageFactory() {
        super();
    }

    public String generateItemImageFileName(ViewItem ability) {
        //String imageFileName = "resources/" + item.getItemName() + ".jpg";
        System.out.println(ability);
        String imageFileName = "resources/AbilityItems/" + ability.getItemName() + ".png";
        /*
        String imageFileName;
        if(item.getItemName()!=null) {
            imageFileName = "resources/" + item.getItemName() + ".png";
        } else {
            imageFileName = null;
        }
        */
        return imageFileName;
    }
}
