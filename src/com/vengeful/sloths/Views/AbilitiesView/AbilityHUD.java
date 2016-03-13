package com.vengeful.sloths.Views.AbilitiesView;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Observers.AbilityManagerObserver;
import com.vengeful.sloths.Views.View;

import java.awt.*;

/**
 * Created by John on 3/13/2016.
 */
public class AbilityHUD extends View implements AbilityManagerObserver {

    private int height;
    private int width;
    private int startX;
    private int startY;

    private AbilityViewObject[] abilities = new AbilityViewObject[4];


    public AbilityHUD(int width, int height){
        this.height = height;
        this.width = width;

        this.setPreferredSize(new Dimension(this.width, this.height));
        this.startX = this.getViewWidth()/2 - this.width/2;
        this.startY = this.getViewHeight() - this.height;
        Avatar.getInstance().getAbilityManager().registerObserver(this);

    }
    @Override
    public void alertAbilityAdded(Ability ability) {

    }

    @Override
    public void alertAbilityEquipped(Ability ability, int index) {

    }

    @Override
    public void alertAbilityUnequipped(Ability ability, int index) {

    }

    @Override
    public void paintComponent(Graphics g){

    }
}
