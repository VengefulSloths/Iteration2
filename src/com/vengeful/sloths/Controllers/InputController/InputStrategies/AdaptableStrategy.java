package com.vengeful.sloths.Controllers.InputController.InputStrategies;

import com.intellij.util.containers.HashMap;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;

import java.awt.event.KeyEvent;

/**
 * Created by John on 3/10/2016.
 */
public class AdaptableStrategy extends InputStrategy {

    HashMap<Integer, KeyMapping> keyMappings = new HashMap<>();

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
                    state.handleReleaseNorthWestKey();
                    break;
                case NORTH:
                    state.handleReleaseNorthKey();
                    break;
                case NORTHEAST:
                    state.handleReleaseNorthEastKey();
                    break;
                case SOUTHWEST:
                    state.handleReleaseSouthWestKey();
                    break;
                case SOUTH:
                    state.handleReleaseSouthKey();
                    break;
                case SOUTHEAST:
                    state.handleReleaseSouthEastKey();
                    break;
                case INVENTORY:
                    //state.handleReleaseInventoryKey();
                    break;
                case EQUIPMENT:
                    //state.handleReleaseEquipmentKey();
                    break;
                case ESC:
                    //state.handleReleaseESCKey();
                    break;
                case UP:
                    state.handleReleaseUpKey();
                    break;
                case DOWN:
                    state.handleReleaseDownKey();
                    break;
                case RIGHT:
                    state.handleReleaseRightKey();
                    break;
                case LEFT:
                    state.handleReleaseLeftKey();
                    break;
                case SPACE:
                    //state.handleReleaseSpaceKey();
                    break;
                case ENTER:
                    state.handleReleaseCenterKey();
                    break;
                case SAVE:
                    //state.handleReleaseSaveKey();
                    break;
                case EAST:
                    state.handleReleaseEastKey();
                    break;
                case WEST:
                    state.handleReleaseWestKey();
                    break;
                case CENTER:
                    state.handleReleaseCenterKey();
                    break;
                case DROP:
                    //state.handleReleaseDropKey();
                    break;
                default:
                    //do nothing not a supported key
            }
        }
    }

    private KeyMapping getMappedKey(int key){
        KeyMapping mappedKey = keyMappings.get(key);
        return mappedKey;
    }

    public void setKeyMappings(int key, KeyMapping value){
        //maybe add logic if that key is already mapped somehwhere
        keyMappings.put(key, value);
    }
}
