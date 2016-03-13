package com.vengeful.sloths.Controllers.InputController;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.*;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Menu.CharacterCreation.CharacterCreationView;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Models.DialogueTrade.DialogContainer;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.TimeModel.Tickable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Views.PickPocketView.PickPocketView;
import com.vengeful.sloths.Views.TradeView.GridAvatarInvViewTrading;
import com.vengeful.sloths.Views.TradeView.GridEntityInvViewTrading;
import com.vengeful.sloths.Views.TradeView.TradeView;
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
    //private InventoryControllerState inventoryControllerState;
    private InventoryEquipmentControllerState inventoryEquipmentControllerState;

    private MenuControllerState menuControllerState;
    private AbilitySkillsControllerState abilitySkillsControllerState;
    private SetInputsControllerState setInputsControllerState;
    private CharacterCreationControllerState characterCreationControllerState;
    private PickPocketControllerState pickPocketControllerState;
    private DialogControllerState dialogControllerState;
    private TradeControllerState tradeContollerState;

    public InventoryEquipmentControllerState getInventoryEquipmentControllerState() {
        return inventoryEquipmentControllerState;
    }

    public void setInventoryEquipmentControllerState(InventoryEquipmentControllerState inventoryEquipmentControllerState) {
        this.inventoryEquipmentControllerState = inventoryEquipmentControllerState;
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
        //inventoryControllerState = new InventoryControllerState();
        inventoryEquipmentControllerState = new InventoryEquipmentControllerState();
        menuControllerState = new MenuControllerState();
        abilitySkillsControllerState = new AbilitySkillsControllerState();
        setInputsControllerState = new SetInputsControllerState(inputStrategy);
        characterCreationControllerState = new CharacterCreationControllerState();
        pickPocketControllerState = new PickPocketControllerState();
        dialogControllerState = new DialogControllerState();

        state = avatarControllerState;

        map = Map.getInstance();

        inputHandler = new InputHandler(this);
        ViewEngine.getInstance().addKeyListener(inputHandler);
        TimeModel.getInstance().registerTickable(this);
    }


    public void init(ViewManager vm) {
        this.viewManager = vm;
        //inventoryControllerState.setInventoryView(this.viewManager.getCharacterView().getInventoryView());
        inventoryEquipmentControllerState.setCharacterView(this.viewManager.getCharacterView()); //edit?
        abilitySkillsControllerState.setAbilitiesSkillView(this.viewManager.getAbilitiesSkillView());
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

    public void setDialogControllerState(DialogContainer dialogContainer){
        viewManager.openDialogView();

        dialogContainer.registerObserver(viewManager.getDialogView());

        dialogControllerState.setDialogContainer(dialogContainer);
        this.state = dialogControllerState;
    }

    public void setAvatarControllerState(){
        ModelEngine.getInstance().unpauseGame();
        this.state = this.avatarControllerState;
        viewManager.closeCharacterView();
        viewManager.closeAbilitiesSkillsView();
        viewManager.closeMenuView();
        viewManager.closeKeyBindView();
        viewManager.closeChooseSaveView();
        viewManager.closePickPocketView();
        viewManager.closeDialogView();
        viewManager.closeTradeView();
        System.out.println("Switching to avatar state");
    }

    public void setInventoryEquipmentControllerState() {
        this.state = this.inventoryEquipmentControllerState;
        this.inventoryEquipmentControllerState.resetViews();
        viewManager.openCharacterView();
        System.out.println("Switching to Inventory Equipment state");
    }

    /*
    public void setInventoryControllerState(){
        this.state = this.inventoryControllerState;
        this.inventoryControllerState.resetInventoryView();
        viewManager.openCharacterView();
        System.out.println("Switching to inventory state");
    }
    */


    public void setMenuControllerState(ScrollableMenu menu) {
        this.menuControllerState.setScrollableMenu(menu);
        this.state = this.menuControllerState;

    }

    public void setAbilitySkillsControllerState(){
        this.state = this.abilitySkillsControllerState;
        this.abilitySkillsControllerState.resetViews();
        viewManager.openAbilitiesSkillsView();
        System.out.println("Switching to inventory state");
    }

    public void setInGameMenuControllerState(){
        viewManager.closeChooseSaveView();
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

    public void setChooseSaveControllerState(){
        viewManager.closeMenuView();
        this.menuControllerState.setScrollableMenu(viewManager.getChooseSaveView());
        viewManager.openChooseSaveView();
        this.state = menuControllerState;
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

    public void setCharacterCreationControllerState(){
        ViewEngine.getInstance().killOldView();
        CharacterCreationView view = new CharacterCreationView(500,400);
        ViewEngine.getInstance().registerView(view);
        characterCreationControllerState.setMenu(view);
        this.state = characterCreationControllerState;
    }

    public void setTradeControllerState(Entity target, Inventory targInv, int bargainSkill) {

        GridAvatarInvViewTrading avatarInvView = new GridAvatarInvViewTrading(Avatar.getInstance().getInventory(), bargainSkill);
        //will need a different constructor and pass both bargin skills in and calculate based off that...or something...
        GridEntityInvViewTrading entityInvView = new GridEntityInvViewTrading(targInv, bargainSkill);
        TradeView tradeView = new TradeView(avatarInvView, entityInvView, bargainSkill);
        this.tradeContollerState = new TradeControllerState(tradeView);
        viewManager.setTradeView(tradeView);
        viewManager.openTradeView();
        this.state = this.tradeContollerState;
//        tradeContollerState.initTradeValues(tradeView, targInv,target);
    }
    public void setPickPocketControllerState(Entity e, Inventory inv, int pickPocketSkill){
        //make a pickpocketView
        PickPocketView pickPocketView = new PickPocketView(inv, e, pickPocketSkill);
        viewManager.setPickPocketView(pickPocketView);
        viewManager.openPickPocketView();
        this.state = this.pickPocketControllerState;
        pickPocketControllerState.initPickPocketValues(pickPocketView, inv,e);
        //REMEMBER TO RESET ACTIVE
        //PROBABLY GO IN A CALL IN PICKPOCKETVIEW
    }
}
