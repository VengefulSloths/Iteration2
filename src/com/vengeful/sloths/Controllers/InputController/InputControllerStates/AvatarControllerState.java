package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 2/29/2016.
 */
public class AvatarControllerState extends InputControllerState {

    private Avatar avatar = Avatar.getInstance();
    private Direction currentMovementDirection = null;

    @Override
    public void continuousFunction() {
        //do something continuously
        if(currentMovementDirection != null){
            System.out.println("moving");
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
        return false;
    }

    @Override
    public boolean handleEKey() {
        return false;
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
        System.out.println("handling 2 key");
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

        return false;
    }

    @Override
    public boolean handleDKey() {
        return false;
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
}