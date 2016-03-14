package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.sun.deploy.security.DeployManifestChecker;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.ModelConfig;

import java.sql.Time;
import java.util.Iterator;

/**
 * Created by luluding on 3/13/16.
 */
public class NPCFallAsleepAbility extends Ability{
    /* Summoner ability, uses enchantment level */

    private Entity entity;
    private int windupTicks;
    private int coolDownTicks;
    private int sleepTime;

    private int manaCost = ModelConfig.getManaCostHigh();

    public NPCFallAsleepAbility(Entity entity, int windup, int cooldown, int sleepTime){
        super("NPC Go To Sleep", windup, cooldown);
        this.entity = entity;
        this.windupTicks = windup;
        this.coolDownTicks = cooldown;
        this.sleepTime = sleepTime;
    }

    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getEnchantment(), entity.getSkillManager().getMaxEnchantment()))
            return 0;

        if(entity.getStats().getCurrentMana() < manaCost)
            return 0;

        this.entity.setActive(true);
        this.entity.decMana(this.manaCost);

        Iterator<EntityObserver> observers = entity.getObservers().iterator();
        while (observers.hasNext()) {
            System.out.println("alerting cast spells");
            observers.next().alertCast(getWindTicks()* TimeController.MODEL_TICK, getCoolTicks()*TimeController.MODEL_TICK);
        }

        TimeModel.getInstance().registerAlertable(() -> {
            Iterator<Entity> target = getTarget();
            if(target != null){
                while (target.hasNext()){
                    Entity e = target.next();
                    e.setStunned(true);
                    System.out.println("NPC GOES TO SLEEP");
                    TimeModel.getInstance().registerAlertable(() -> {
                        e.setStunned(true);
                        System.out.println("NPC WAKES UP");
                    }, sleepTime);
                }
            }
        }, windupTicks);

        TimeModel.getInstance().registerAlertable(() -> {
            entity.setActive(false);
        }, coolDownTicks);


        return coolDownTicks;
    }


    private Iterator<Entity> getTarget(){
        Coord facingCoord = HexMath.getNextFacingCoord(entity.getLocation(), entity.getFacingDirection());
        Tile t = Map.getInstance().getActiveMapArea().getTile(facingCoord);
        if(t == null)
            return null;

        return t.getEntityIterator();
    }

    @Override
    protected void abilityFailHook() {
        Iterator<Entity> target = getTarget();
        if(target != null){
            while(target.hasNext()) {
                target.next().enrage();
                System.out.println("ABILITY FAILED, NPC IS ANGRYYYYY");
            }
        }
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitNPCFallAsleepAbility(this);
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
