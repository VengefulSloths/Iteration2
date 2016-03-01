package com.vengeful.sloths.Controllers.InputController;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.InputStrategy;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.QWEASDInputStrategy;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;

/**
 * Created by John on 2/29/2016.
 */
public class MainController {

    private Avatar player;
    private Inventory inventory;
    private InputControllerState state;
    private Map map;
    private InputStrategy inputStrategy;

    private static MainController ourInstance = new MainController();

    public static MainController getInstance() {
        return ourInstance;
    }

    private MainController() {
        player = Avatar.getInstance();
        inventory = player.getInventory();
        //state = something;
        map = Map.getInstance();
        inputStrategy = new QWEASDInputStrategy();//for testing
    }

    public void dispatchPressedKey(int key){
        inputStrategy.interpretPressedKey(key, state);
    }

    public void dispatchReleasedKey(int key){
        inputStrategy.interpretReleasedKey(key, state);
    }


}
