package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.AreaView.AvatarViewFollower;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Utility.CartesionDirection;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 2/29/2016.
 */
public class AvatarControllerState extends InputControllerState {

    private Avatar avatar = Avatar.getInstance();
    private AvatarViewFollower follower = AvatarViewFollower.getInstance();
    private Direction currentMovementDirection = null;
    private CartesionDirection currentFollowerDirection = new CartesionDirection();

    @Override
    public void continuousFunction() {
        //do something continuously

        follower.move(currentFollowerDirection.getDirection());


        if(currentMovementDirection != null){
            avatar.move(currentMovementDirection);
        }
    }
    @Override
    public void onDeregister() {

    }

    @Override
    public void onRegister() {

    }



    ///////////////////////handle key presses
    @Override
    public boolean handleIKey() {
        MainController.getInstance().setInventoryControllerState();
        return true;
    }

    @Override
    public boolean handleEKey() {
        //TODO: test ability. Remove
        System.out.println("R PRESSED");
        avatar.doAbility(1);
        return true;
    }

    @Override
    public boolean handleESCKey() {
        return false;
    }

    @Override
    public boolean handle1Key() {
        if(avatar.getFacingDirection() != Direction.SW) {
            avatar.changeDirection(Direction.SW);
        }
        this.currentMovementDirection = Direction.SW;
        return true;
    }

    @Override
    public boolean handle2Key() {
        if(avatar.getFacingDirection() != Direction.S) {
            avatar.changeDirection(Direction.S);
        }
        this.currentMovementDirection = Direction.S;
        return true;
    }

    @Override
    public boolean handle3Key() {
        if(avatar.getFacingDirection() != Direction.SE) {
            avatar.changeDirection(Direction.SE);
        }
        this.currentMovementDirection = Direction.SE;
        return true;
    }

    @Override
    public boolean handleSpaceKey() {
        avatar.attack(avatar.getFacingDirection());
        return true;
    }

    @Override
    public boolean handle4Key() {
        return false;
    }

    @Override
    public boolean handle6Key() {
        return false;
    }

    @Override
    public boolean handle7Key() {
        if(avatar.getFacingDirection() != Direction.NW) {
            avatar.changeDirection(Direction.NW);
        }
        this.currentMovementDirection = Direction.NW;
        return true;
    }

    @Override
    public boolean handle8Key() {
        if(avatar.getFacingDirection() != Direction.N) {
            avatar.changeDirection(Direction.N);
        }
        this.currentMovementDirection = Direction.N;
        return true;
    }

    @Override
    public boolean handle9Key() {
        if(avatar.getFacingDirection() != Direction.NE) {
            avatar.changeDirection(Direction.NE);
        }
        this.currentMovementDirection = Direction.NE;
        return true;
    }

    @Override
    public boolean handle5Key() {
        avatar.mount();
        return true;
    }

    @Override
    public boolean handleDKey() {
        return false;
    }

    @Override
    public boolean handleLeftKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.W);
        return true;
    }

    @Override
    public boolean handleRightKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.E);
        return true;
    }

    @Override
    public boolean handleDownKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.S);
        return true;
    }

    @Override
    public boolean handleUpKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.N);
        return true;
    }


    /////////////////////////handle key releases/////////////////////////
    @Override
    public void handleRelease1Key() {
        if(currentMovementDirection == Direction.SW){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease2Key() {
        if(currentMovementDirection == Direction.S){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease3Key() {
        if(currentMovementDirection == Direction.SE){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease4Key() {

    }

    @Override
    public void handleRelease6Key() {

    }

    @Override
    public void handleRelease7Key() {
        if(currentMovementDirection == Direction.NW){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease8Key() {
        if(currentMovementDirection == Direction.N){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease9Key() {
        if(currentMovementDirection == Direction.NE){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleRelease5Key() {

    }

    @Override
    public boolean handleReleaseLeftKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.E);
        return true;
    }

    @Override
    public boolean handleReleaseRightKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.W);
        return true;
    }

    @Override
    public boolean handleReleaseDownKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.N);
        return true;
    }

    @Override
    public boolean handleReleaseUpKey() {
        this.currentFollowerDirection.addDirection(CartesionDirection.Direction.S);
        return true;
    }
}
