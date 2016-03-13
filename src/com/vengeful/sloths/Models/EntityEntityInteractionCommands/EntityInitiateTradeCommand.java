package com.vengeful.sloths.Models.EntityEntityInteractionCommands;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by harrison on 3/13/16.
 */
public class EntityInitiateTradeCommand {
    private Entity target;
    private Inventory targInv;
    private int bargainSkill;

    public EntityInitiateTradeCommand(Entity target) {
        Avatar a = Avatar.getInstance();
                this.target = target;
                this.bargainSkill = a.getSkillManager().getBargainLevel();
                target.setDead(true);
                targInv = target.getInventory();
    }

    public void execute() {
        if (target != null) {
            MainController.getInstance().setTradeControllerState(target, targInv, bargainSkill);
        }
    }

}
