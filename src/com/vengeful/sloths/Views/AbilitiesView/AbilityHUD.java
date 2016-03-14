package com.vengeful.sloths.Views.AbilitiesView;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Observers.AbilityManagerObserver;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/13/2016.
 */
public class AbilityHUD extends View implements AbilityManagerObserver {

    private int height;
    private int width;
    private int startX;
    private int startY;
    private Image abilityIcon = (new ImageIcon("resources/ability_bg_outline.png")).getImage();
    private Image activeAbilityIcon = (new ImageIcon("resources/ability_icon_active_outline.png")).getImage();

    private int[] active = new int[4];
    private int[] windup = new int[4];

    private AbilityViewObject[] abilities = new AbilityViewObject[4];


    public AbilityHUD(int width, int height){
        this.height = height;
        this.width = width;

        this.setPreferredSize(new Dimension(this.width, this.height));

        this.startX = 400 - this.width/2;
        this.startY = 965 - this.height;
        Avatar.getInstance().getAbilityManager().registerObserver(this);

        Ability[] activeAbilities = Avatar.getInstance().getAbilityManager().getActiveAbilities();

        for(int i = 0; i < 4; ++i){
            abilities[i] = null;
            active[i] = 0;
        }

        for (int i = 0; i < activeAbilities.length; i++) {
            if(activeAbilities[i] != null) {
                abilities[i] = new AbilityViewObject(activeAbilities[i]);
                windup[i] = activeAbilities[i].getWindTicks();
            }
        }


        //try to set pre-existing abilities


    }
    @Override
    public void alertAbilityAdded(Ability ability) {

    }

    @Override
    public void alertAbilityEquipped(Ability ability, int index) {
        windup[index] = ability.getWindTicks();
        abilities[index] = new AbilityViewObject(ability);
    }

    @Override
    public void alertAbilityUnequipped(Ability ability, int index) {

    }

    @Override
    public void paintComponent(Graphics g){
        int offsetX = startX;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Helvetica",1,60));
        g2d.setColor(new Color(0,0,0,.7f));
        g2d.fillRect(startX + 10,startY+11, 295, 75);
        //g2d.fillRect(0,0,1000,1000);


        for(int i = 0; i < 4; ++i){

            if(active[i] > 0){
                g2d.drawImage(activeAbilityIcon, offsetX, startY, this);
                active[i] -= 1;
            }else {
                g2d.drawImage(abilityIcon, offsetX, startY, this);
            }

            if(abilities[i] != null){
                abilities[i].paintComponent(g,offsetX + 10, startY + 10, 54, 76);
            }
            offsetX += 84;
        }
    }
    public void setAbilityactive(int abilityNumber){
        active[abilityNumber] = windup[abilityNumber];
    }
}
