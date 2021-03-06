package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.AreaView.AvatarViewFollower;
import com.vengeful.sloths.Menu.InGameMenu.InGameScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Utility.CartesionDirection;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Views.ViewManager.ViewManager;

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

    @Override
    public void handleAbility1Key() {
        MainController.getInstance().setAbilityActive(0);
        Avatar.getInstance().doAbility(0);
    }

    @Override
    public void handleAbility2Key() {
        MainController.getInstance().setAbilityActive(1);
        Avatar.getInstance().doAbility(1);
    }

    @Override
    public boolean handleAbilitiesKey() {
        MainController.getInstance().setAbilitySkillsControllerState();
        return true;
    }

    @Override
    public void handleAbility3Key() {
        System.out.println("333333333333333333333333333");
        MainController.getInstance().setAbilityActive(2);
        Avatar.getInstance().doAbility(2);
    }

    @Override
    public void handleAbility4Key() {
        MainController.getInstance().setAbilityActive(3);
        Avatar.getInstance().doAbility(3);
    }

    @Override
    public void handleObservationKey() {
        Avatar.getInstance().observe();
    }

    ///////////////////////handle key presses
    @Override
    public boolean handleInventoryKey() {
        //MainController.getInstance().setInventoryControllerState();
        MainController.getInstance().setInventoryEquipmentControllerState();
        return true;
    }

    @Override
    public void handleTalkKey() {
        Avatar.getInstance().talk();
    }

    @Override
    public boolean handleESCKey() {
        //ScrollableMenu menu = new InGameScrollableMenu(80);
        MainController.getInstance().setInGameMenuControllerState();
        return true;
    }

    @Override
    public boolean handleSouthWestKey() {
        if(avatar.getFacingDirection() != Direction.SW) {
            avatar.changeDirection(Direction.SW);
        }
        this.currentMovementDirection = Direction.SW;
        System.out.println("southwest");
        return true;
    }

    @Override
    public void resetView(boolean isActive) {

    }

    @Override
    public boolean handleSouthKey() {
        if(avatar.getFacingDirection() != Direction.S) {
            avatar.changeDirection(Direction.S);
        }
        this.currentMovementDirection = Direction.S;
        return true;
    }

    @Override
    public boolean handleSouthEastKey() {
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
    public boolean handleWestKey() {
        //TODO: test ability, remove
        System.out.println("X pressed!!!!");
        Avatar.getInstance().doAbility(0);
        return true;
    }

    @Override
    public boolean handleEastKey() {


        return false;
    }

    @Override
    public boolean handleNorthWestKey() {
        if(avatar.getFacingDirection() != Direction.NW) {
            avatar.changeDirection(Direction.NW);
        }
        this.currentMovementDirection = Direction.NW;
        return true;
    }

    @Override
    public boolean handleNorthKey() {
        if(avatar.getFacingDirection() != Direction.N) {
            avatar.changeDirection(Direction.N);
        }
        this.currentMovementDirection = Direction.N;
        return true;
    }

    @Override
    public boolean handleNorthEastKey() {
        if(avatar.getFacingDirection() != Direction.NE) {
            avatar.changeDirection(Direction.NE);
        }
        this.currentMovementDirection = Direction.NE;
        return true;
    }

    @Override
    public void handleEnterKey() {
        avatar.mount();
    }

    @Override
    public boolean handleCenterKey() {
        return false;
    }

    @Override
    public boolean handleDropKey() {
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
    public void handleReleaseSouthWestKey() {
        if(currentMovementDirection == Direction.SW){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseSouthKey() {
        if(currentMovementDirection == Direction.S){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseSouthEastKey() {
        if(currentMovementDirection == Direction.SE){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseWestKey() {

    }

    @Override
    public void handleReleaseEastKey() {

    }

    @Override
    public void handleReleaseNorthWestKey() {
        if(currentMovementDirection == Direction.NW){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseNorthKey() {
        if(currentMovementDirection == Direction.N){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseNorthEastKey() {
        if(currentMovementDirection == Direction.NE){
            currentMovementDirection = null;
        }
    }

    @Override
    public void handleReleaseCenterKey() {

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

    public void handleSaveKey(){
//        //quick call to save for testing
//        sm.save();
    }

    @Override
    public boolean handleEquipmentKey() {
        return false;
    }

}
