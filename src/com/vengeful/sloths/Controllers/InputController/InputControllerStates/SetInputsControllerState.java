package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.InputChangeMenu;
import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.KeyBindCommandFactory;
import com.vengeful.sloths.Controllers.InputController.MainController;

/**
 * Created by John on 3/10/2016.
 */
public class SetInputsControllerState extends InputControllerState {
    InputChangeMenu menu;
    KeyBindCommandFactory keyBindCommandFactory;//= new KeyBindCommandFactory((AdaptableStrategy) MainController.getInstance().getInputStrategy());
    //lol the above line is pretty volatile tbh, would refactor if time is had
    private AdaptableStrategy adaptableStrategy;
    public SetInputsControllerState(AdaptableStrategy adaptableStrategy){
        this.adaptableStrategy = adaptableStrategy;
        this.keyBindCommandFactory = new KeyBindCommandFactory(adaptableStrategy);
    }


    public InputChangeMenu getMenu() {
        return menu;
    }

    public void setMenu(InputChangeMenu menu) {
        this.menu = menu;
    }


    public void setKey(int key){
        System.out.println("making the create key command");
        keyBindCommandFactory.createKeyBindCommand(key ,menu.getMenuItem()).execute();
        menu.refreshKeys();
    }
    @Override
    public void continuousFunction() {

    }

    @Override
    public void onDeregister() {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public void handleTalkKey() {

    }

    @Override
    public boolean handleInventoryKey() {

        return false;
    }

    @Override
    public boolean handleAbilitiesKey() {
        return false;
    }

    @Override
    public void handleAbility1Key() {

    }

    @Override
    public void handleAbility2Key() {

    }

    @Override
    public void handleAbility3Key() {

    }

    @Override
    public void handleAbility4Key() {

    }

    @Override
    public void handleObservationKey() {

    }

    @Override
    public void resetView(boolean isActive) {

    }

    @Override
    public boolean handleEquipmentKey() {
        return false;
    }

    @Override
    public boolean handleESCKey() {

        //go back to area view or menu view or whatever
        MainController.getInstance().setInGameMenuControllerState();
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }

    @Override
    public boolean handleSouthKey() {
        return false;
    }

    @Override
    public boolean handleSouthEastKey() {
        return false;
    }

    @Override
    public boolean handleWestKey() {
        return false;
    }

    @Override
    public boolean handleEastKey() {
        return false;
    }

    @Override
    public boolean handleNorthWestKey() {
        return false;
    }

    @Override
    public boolean handleNorthKey() {
        return false;
    }

    @Override
    public boolean handleNorthEastKey() {
        return false;
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
        return false;
    }

    @Override
    public boolean handleRightKey() {
        return false;
    }

    @Override
    public boolean handleDownKey() {
        menu.down();
        return false;
    }

    @Override
    public boolean handleUpKey() {
        menu.up();
        return false;
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

    }

    @Override
    public boolean handleSpaceKey() {
        return false;
    }

    @Override
    public void handleReleaseSouthWestKey() {

    }

    @Override
    public void handleReleaseSouthKey() {

    }

    @Override
    public void handleReleaseSouthEastKey() {

    }

    @Override
    public void handleReleaseWestKey() {

    }

    @Override
    public void handleReleaseEastKey() {

    }

    @Override
    public void handleReleaseNorthWestKey() {

    }

    @Override
    public void handleReleaseNorthKey() {

    }

    @Override
    public void handleReleaseNorthEastKey() {

    }

    @Override
    public void handleReleaseCenterKey() {

    }

    @Override
    public boolean handleReleaseLeftKey() {
        return false;
    }

    @Override
    public boolean handleReleaseRightKey() {
        return false;
    }

    @Override
    public boolean handleReleaseDownKey() {
        return false;
    }

    @Override
    public boolean handleReleaseUpKey() {
        return false;
    }


}
