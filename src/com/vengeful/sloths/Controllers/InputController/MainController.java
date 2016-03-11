package com.vengeful.sloths.Controllers.InputController;

import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.AvatarControllerState;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InventoryControllerState;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.MenuControllerState;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.InputStrategy;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.QWEASDInputStrategy;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.TimeModel.Tickable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Views.ViewManager.ViewManager;

/**
 * Created by John on 2/29/2016.
 */
public class MainController implements Tickable{

    private Avatar player;
    private Inventory inventory;
    private InputControllerState state;
    private Map map;
    private InputStrategy inputStrategy;
    private InputHandler inputHandler;

    private ViewManager viewManager;

    //states
    private AvatarControllerState avatarControllerState;
    private InventoryControllerState inventoryControllerState;
    private MenuControllerState menuControllerState;

    public InventoryControllerState getInventoryControllerState() {
        return inventoryControllerState;
    }

    public void setInventoryControllerState(InventoryControllerState inventoryControllerState) {
        this.inventoryControllerState = inventoryControllerState;
    }

    private static MainController ourInstance = new MainController();

    public static MainController getInstance() {
        return ourInstance;
    }

    private MainController() {
        player = Avatar.getInstance();
        inventory = player.getInventory();
        avatarControllerState = new AvatarControllerState();
        inventoryControllerState = new InventoryControllerState();
        menuControllerState = new MenuControllerState();

        state = avatarControllerState;

        map = Map.getInstance();
        inputStrategy = new QWEASDInputStrategy();//for testing
        inputHandler = new InputHandler(this);
        ViewEngine.getInstance().addKeyListener(inputHandler);
        TimeModel.getInstance().registerTickable(this);
    }


    public void init(ViewManager vm) {
        this.viewManager = vm;
        inventoryControllerState.setInventoryView(this.viewManager.getCharacterView().getInventoryView());
    }

    public void dispatchPressedKey(int key){
        inputStrategy.interpretPressedKey(key, state);
    }

    public void dispatchReleasedKey(int key){
        inputStrategy.interpretReleasedKey(key, state);
    }


    public void setAvatarControllerState(){
        this.state = this.avatarControllerState;
        viewManager.closeCharacterView();
        viewManager.closeMenuView();
        System.out.println("Switching to avatar state");
    }

    public void setInventoryControllerState(){
        this.state = this.inventoryControllerState;
        viewManager.openCharacterView();
        System.out.println("Switching to inventory state");
    }

    public void setMenuControllerState(ScrollableMenu menu) {
        this.menuControllerState.setScrollableMenu(menu);
        this.state = this.menuControllerState;

    }

    public void setInGameMenuControllerState(){
        this.menuControllerState.setScrollableMenu(viewManager.getMenuView());
        viewManager.openMenuView();
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public Avatar getPlayer(){
        return this.player;
    }

    @Override
    public void tick() {
        state.continuousFunction();
    }
}
