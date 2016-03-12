package com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;

/**
 * Created by luluding on 3/12/16.
 */
public class RemoveTrapAbility extends Ability{
    /* Sneak only*/

    private Entity entity;

    private int manaCost = 1;


    public RemoveTrapAbility(Entity entity, int windTicks, int coolTicks){
        this.entity = entity;
        this.setWindTicks(windTicks);
        this.setCoolTicks(coolTicks);
    }


    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getBaneLevel(), entity.getSkillManager().getMaxBaneLevel()))
            return 0;


        this.entity.setActive(true);
        this.entity.decMana(this.manaCost);


        Coord facingCoord = HexMath.getNextFacingCoord(entity.getLocation(), entity.getFacingDirection());

        TimeModel.getInstance().registerAlertable(() -> {
            Map.getInstance().getActiveMapArea().getTile(facingCoord).removeTrap();
        }, getWindTicks());

        TimeModel.getInstance().registerAlertable(() -> {
            this.entity.setActive(false);
        }, getCoolTicks());


        return getCoolTicks();
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitRemoveTrapAbility(this);
    }
}
