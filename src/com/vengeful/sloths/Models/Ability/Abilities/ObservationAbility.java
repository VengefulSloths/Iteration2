package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.ModelConfig;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by luluding on 3/14/16.
 */
public class ObservationAbility extends Ability{

    private Entity entity;

    public ObservationAbility(Entity entity){
        super("Observation", 0, 0);
        this.entity = entity;
    }

    @Override
    public String getDescription() {
        return "Look at the little details";
    }

    @Override
    public int execute() {
        observeNPC();
        return 0;
    }



    public void observeNPC(){

        for(int i = 1; i < ModelConfig.getRadiusOfVisibility()+1; i++){
            Iterator<Coord> ring = HexMath.ring(entity.getLocation(), i);


            while (ring.hasNext()){
                Coord c = ring.next();

                if(HexMath.checkTileValidExists(c, Map.getInstance().getActiveMapArea().getMaxR(), Map.getInstance().getActiveMapArea().getMaxS())){
                    Tile t = Map.getInstance().getTile(c);

                    Entity[] e = t.getEntities();
                    for(int j = 0; j < e.length; j++){
                        generateObservedInfo(e[j], i);
                    }

                }
            }
        }
    }

    public void generateObservedInfo(Entity e, int radius){
        //observe only movement and damage
        int realDmg = 0;

        if(e.getEquipped().getWeapon() == null)
            realDmg = ModelConfig.calcuateDamage(0, e.getStats().getStrength(), 0);
        else
            realDmg = ModelConfig.calcuateDamage(e.getEquipped().getWeapon().getBaseDamage(), e.getStats().getStrength(), 0);


        int realSpeed = e.getStats().getMovement();
        int observationLevel = this.entity.getSkillManager().getObservationLevel();


        double radiusAcc = radius-1;
        double distanceFactor = 0;
        double accuBoost = 0; //formula actually assumes max skill level = 10

        double skillLevelFactor = 1.0 / (observationLevel+0.001+accuBoost); //my base
        distanceFactor = (1-skillLevelFactor) / (ModelConfig.getRadiusOfVisibility() * 10);
        //distanceFactor = skillLevelFactor / (ModelConfig.getRadiusOfVisibility());
        double observationFactor_L = 1 - (skillLevelFactor + (radiusAcc * distanceFactor));


        distanceFactor = (1-skillLevelFactor) / (ModelConfig.getRadiusOfVisibility() * 10);
        //distanceFactor = skillLevelFactor / (ModelConfig.getRadiusOfVisibility());
        double observationFactor_U = 1 + (skillLevelFactor + (radiusAcc * distanceFactor));

        Random r = new Random();
        double randomFactor = observationFactor_L + (observationFactor_U - observationFactor_L) * r.nextDouble();



        int showDmg = (int)(realDmg * randomFactor);
        int showSpeed = (int)(realSpeed * randomFactor);


        System.out.println("========== OBSERVE ==========");
        System.out.println("REAL DMG: " + realDmg + " REAL SPD: " + realSpeed);
        System.out.println("SHOW DMG: " + showDmg + " SHOW SPD: " + showSpeed);
        System.out.println("MIN: " + observationFactor_L);
        System.out.println("MAX: " + observationFactor_U);
        System.out.println("DISC: " + (radiusAcc * distanceFactor));
        System.out.println("RAND FACTOR: " + randomFactor);
        System.out.println("========== OBSERVE ==========");

        ArrayList<EntityObserver> entityObservers = e.getObservers();
        for(int i = 0; i < entityObservers.size(); i++){
            entityObservers.get(0).alertObservationInfo(showDmg, showSpeed);

            TimeModel.getInstance().registerAlertable(() -> {
                entityObservers.get(0).alertRemoveObservationInf();
            }, 40);
        }



    }

    @Override
    public void accept(ModelVisitor modelVisitor) {

    }
}
