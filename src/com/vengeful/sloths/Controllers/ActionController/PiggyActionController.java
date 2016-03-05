package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by zach on 3/4/16.
 */
public class PiggyActionController extends ActionController {

    public PiggyActionController(Entity entity){
        this.setEntity(entity);
    }

    @Override
    public void action(Target target) {
        if(target != null) {
            target.accept(this);
        }else{
            //always return to avatar even if you can technically see him
            //might need to do something here with teleporting
            Target idleTarget = new AvatarTarget(2);
            idleTarget.setCoord(Avatar.getInstance().getLocation());
            idleTarget.accept(this);
            System.out.println("idle");
        }
    }

    @Override
    public void visitAvatarTarget(AvatarTarget avatar) {

        if(this.checkLocation(avatar, 1)) { // 1 meaning he can follow an adjacent square
            // adjacent from Avatar, just idle
            System.out.println("PIGGY IS standing by the avatar!");

        }else{
            // find and walk towards avatar

            this.getEntity().move(this.getTargetDirection(avatar));
        }
    }

    @Override
    public void visitPiggyTarget(PiggyTarget piggy) {
        // Mixed instance
        // Do nothing
    }

    @Override
    public void visitAggressiveNPCTarget(AggressiveNPCTarget aNPC) {
        // maybe attack
        if(this.checkLocation(aNPC, 1)) { //1 meaning he can attack an adjacent square
            //make attack command
            System.out.println("PIGGY IS attacking an aggressive nPC");
            this.getEntity().attack(this.getTargetDirection(aNPC)); //tweak a bit later

        }else{
            //move towards it
            //bfs here
            //actually gonna try shitty directional code to not do bfs kek
            System.out.println("PIGGY IS finding an aggressive NPC!!!");

            this.getEntity().move(this.getTargetDirection(aNPC));
        }
    }

    @Override
    public void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC) {
        // maybe attack
    }

    @Override
    public void visitMapItemTarget(MapItemTarget mapItemTarget) {
        if(this.checkLocation(mapItemTarget, 1)) { //1 meaning he can attack an adjacent square
            // @TODO
//            this.getEntity().pickup(this.getTargetDirection(mapItemTarget));
            // WE NEED TO IMPLEMENT THE PICKUP(DIRECTION) command!

        }else{
            //move towards it
            //bfs here
            //actually gonna try shitty directional code to not do bfs kek
            System.out.println("PIGGY IS finding a mapItem!");

            this.getEntity().move(this.getTargetDirection(mapItemTarget));
        }

    }
}