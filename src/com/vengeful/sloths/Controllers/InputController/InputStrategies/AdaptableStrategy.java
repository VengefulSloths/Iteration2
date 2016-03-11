package com.vengeful.sloths.Controllers.InputController.InputStrategies;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by John on 3/10/2016.
 */
public class AdaptableStrategy extends InputStrategy {

    HashMap<Integer, Integer> keyMappings = new HashMap<Integer, Integer>();

    @Override
    public void interpretPressedKey(int key, InputControllerState state) {

        KeyMapping mapppedKey = this.getMappedKey(key);
        if(mapppedKey != null) {
            switch (mapppedKey) {
                case NORTHWEST:
                    state.handleNorthWestKey();
                    break;
                case NORTH:
                    state.handleNorthKey();
                    break;
                case NORTHEAST:
                    state.handleNorthEastKey();
                    break;
                case SOUTHWEST:
                    state.handleSouthWestKey();
                    break;
                case SOUTH:
                    state.handleSouthKey();
                    break;
                case SOUTHEAST:
                    state.handleSouthEastKey();
                    break;
                case INVENTORY:
                    state.handleInventoryKey();
                    break;
                case EQUIPMENT:
                    state.handleEquipmentKey();
                    break;
                case ESC:
                    state.handleESCKey();
                    break;
                case UP:
                    state.handleUpKey();
                    break;
                case DOWN:
                    state.handleDownKey();
                    break;
                case RIGHT:
                    state.handleRightKey();
                    break;
                case LEFT:
                    state.handleLeftKey();
                    break;
                case SPACE:
                    state.handleSpaceKey();
                    break;
                case ENTER:
                    state.handleCenterKey();
                    break;
                case SAVE:
                    state.handleSaveKey();
                    break;
                case EAST:
                    state.handleEastKey();
                    break;
                case WEST:
                    state.handleWestKey();
                    break;
                case CENTER:
                    state.handleCenterKey();
                    break;
                case DROP:
                    state.handleDropKey();
                    break;
                default:
                    //do nothing not a supported key
            }
        }
    }

    @Override
    public void interpretReleasedKey(int key, InputControllerState state) {
        KeyMapping mapppedKey = this.getMappedKey(key);
        if(mapppedKey != null) {
            switch (mapppedKey) {
                case NORTHWEST:
                    state.handleNorthWestKey();
                    break;
                case NORTH:
                    state.handleNorthKey();
                    break;
                case NORTHEAST:
                    state.handleNorthEastKey();
                    break;
                case SOUTHWEST:
                    state.handleSouthWestKey();
                    break;
                case SOUTH:
                    state.handleSouthKey();
                    break;
                case SOUTHEAST:
                    state.handleSouthEastKey();
                    break;
                case INVENTORY:
                    state.handleInventoryKey();
                    break;
                case EQUIPMENT:
                    state.handleEquipmentKey();
                    break;
                case ESC:
                    state.handleESCKey();
                    break;
                case UP:
                    state.handleUpKey();
                    break;
                case DOWN:
                    state.handleDownKey();
                    break;
                case RIGHT:
                    state.handleRightKey();
                    break;
                case LEFT:
                    state.handleLeftKey();
                    break;
                case SPACE:
                    state.handleSpaceKey();
                    break;
                case ENTER:
                    state.handleCenterKey();
                    break;
                case SAVE:
                    state.handleSaveKey();
                    break;
                case EAST:
                    state.handleEastKey();
                    break;
                case WEST:
                    state.handleWestKey();
                    break;
                case CENTER:
                    state.handleCenterKey();
                    break;
                case DROP:
                    state.handleDropKey();
                    break;
                default:
                    //do nothing not a supported key
            }
        }
    }

    private KeyMapping getMappedKey(int key){
        KeyMapping mappedKey = KeyMapping.values()[keyMappings.get(key)];
        return mappedKey;
    }
}
