package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Buff.PoisonBuff;
import com.vengeful.sloths.Models.Buff.TimedBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.ModelConfig;

import java.util.Iterator;

/**
 * Created by luluding on 3/13/16.
 */
public class WeakenNPCAbility extends Ability{

    /* Used by summoner only, enchantment skill */
    private int manaCost = ModelConfig.getManaCostMid();

    private Entity entity;
    private int windupTicks;
    private int coolDownTicks;

    public WeakenNPCAbility(Entity entity, int windupTicks, int coolDownTicks){
        super("Weaken NPC", windupTicks, coolDownTicks);
        this.entity = entity;
        this.windupTicks = windupTicks;
        this.coolDownTicks = coolDownTicks;
    }


    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getEnchantment(), entity.getSkillManager().getMaxEnchantment()))
            return 0;

        this.entity.setActive(true);
        this.entity.decMana(this.manaCost);

        Iterator<EntityObserver> observers = entity.getObservers().iterator();
        while (observers.hasNext()) {
            System.out.println("alerting cast spells");
            observers.next().alertCast(getWindTicks()* TimeController.MODEL_TICK, getCoolTicks()*TimeController.MODEL_TICK);
        }


        TimeModel.getInstance().registerAlertable(() -> {
            Entity[] target = getTarget();
            if(target != null){
                for(int i = 0; i < target.length; i++){
                    int strength = (int)Math.round(target[i].getStats().getStrength() / 2.0);
                    int agility = (int)Math.round(target[i].getStats().getAgility() / 2);
                    int intellect = (int)Math.round(target[i].getStats().getAgility() / 2);
                    int hardiness = (int)Math.round(target[i].getStats().getHardiness() / 2);
                    int movement = (int)Math.round(target[i].getStats().getMovement() / 2);
                    target[i].getBuffManager().addBuff(new TimedBuff(target[i].getObservers(),
                                                                        target[i].getBuffManager(),
                                                                        new BaseStatsAddable(-strength, -agility, -intellect, -hardiness, -movement),
                                                                        "weaken",
                                                                        600));
                }
            }
        }, windupTicks);

        TimeModel.getInstance().registerAlertable(() -> {
            entity.setActive(false);
        }, coolDownTicks);

        return coolDownTicks;
    }


    private Entity[] getTarget(){
        Coord facingCoord = HexMath.getNextFacingCoord(entity.getLocation(), entity.getFacingDirection());
        Tile t = Map.getInstance().getActiveMapArea().getTile(facingCoord);
        if(t == null)
            return null;

        return t.getEntities();
    }


    @Override
    public void accept(ModelVisitor modelVisitor) {
        //TODO: add to visitor
    }
}
