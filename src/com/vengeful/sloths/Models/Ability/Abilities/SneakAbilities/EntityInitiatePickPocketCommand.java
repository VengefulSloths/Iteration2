package com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by harrison on 3/12/16.
 */
public class EntityInitiatePickPocketCommand {
    private Entity target;
    private Inventory targInv;
    private int pickPocketSkill;

    public EntityInitiatePickPocketCommand(){
        Avatar a = Avatar.getInstance();
        Direction dir = a.getFacingDirection();
        Coord c = a.getLocation();

        Coord dst = new Coord(c.getR(), c.getS());
        switch (dir) {
            case N:
                dst.setS(dst.getS() - 1);
                break;
            case S:
                dst.setS(dst.getS() + 1);
                break;
            case NE:
                dst.setR(dst.getR() + 1);
                dst.setS(dst.getS() - 1);
                break;
            case NW:
                dst.setR(dst.getR() - 1);
                break;
            case SE:
                dst.setR(dst.getR() + 1);
                break;
            case SW:
                dst.setR(dst.getR() - 1);
                dst.setS(dst.getS() + 1);
                break;
            default:
                break;
        }
        Entity[] e = Map.getInstance().getTile(dst).getEntities();
        this.target = e[0];
        this.pickPocketSkill = a.getSkillManager().getPickPocketLevel();
        target.setDead(true);
        //a.setDead(true);
        targInv = target.getInventory();
    }

    public void execute(){
        if(target != null){
            MainController.getInstance().setPickPocketControllerState(target, targInv, pickPocketSkill);
        }
    }
}
