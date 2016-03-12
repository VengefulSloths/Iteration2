package com.vengeful.sloths.Controllers.InputController;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.*;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.InputStrategy;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.QWEASDInputStrategy;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.GameEngine;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.TimeModel.Tickable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Views.ViewManager.ViewManager;

import java.awt.event.KeyEvent;

/**
 * Created by John on 2/29/2016.
 */
public class MainController implements Tickable{

    private Avatar player;
    private Inventory inventory;
    private InputControllerState state;
    private Map map;
    private AdaptableStrategy inputStrategy;
    private InputHandler inputHandler;

    private ViewManager viewManager;

    //states
    private AvatarControllerState avatarControllerState;
    private InventoryControllerState inventoryControllerState;
    private MenuControllerState menuControllerState;
    private SetInputsControllerState setInputsControllerState;


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
        inputStrategy = new AdaptableStrategy();//for testing
        player = Avatar.getInstance();
        inventory = player.getInventory();
        avatarControllerState = new AvatarControllerState();
        inventoryControllerState = new InventoryControllerState();
        menuControllerState = new MenuControllerState();
        setInputsControllerState = new SetInputsControllerState((AdaptableStrategy) inputStrategy);

        state = avatarControllerState;

        map = Map.getInstance();

        inputHandler = new InputHandler(this);
        ViewEngine.getInstance().addKeyListener(inputHandler);
        TimeModel.getInstance().registerTickable(this);
    }


    public void init(ViewManager vm) {
        this.viewManager = vm;
        inventoryControllerState.setInventoryView(this.viewManager.getCharacterView().getInventoryView());
    }

    public void dispatchPressedKey(int key){

        if(state != setInputsControllerState) {
            inputStrategy.interpretPressedKey(key, state);
        }else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_ESCAPE ){
            inputStrategy.interpretPressedKey(key, state);

        }else{
            setInputsControllerState.setKey(key);
        }
    }

    public void dispatchReleasedKey(int key){
        inputStrategy.interpretReleasedKey(key, state);
    }


    public void setAvatarControllerState(){
        ModelEngine.getInstance().unpauseGame();
        this.state = this.avatarControllerState;
        viewManager.closeCharacterView();
        viewManager.closeMenuView();
        viewManager.closeKeyBindView();
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
        ModelEngine.getInstance().pauseGame();
        this.menuControllerState.setScrollableMenu(viewManager.getMenuView());
        viewManager.openMenuView();
        this.state = menuControllerState;
    }

    public void setSetInputsControllerState(){
        this.setInputsControllerState.setMenu(viewManager.getKeyBindView());
        viewManager.openKeyBindView();
        this.state = setInputsControllerState;
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

    public AdaptableStrategy getInputStrategy() {
        return inputStrategy;
    }

    public void setInputStrategy(AdaptableStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }
}
