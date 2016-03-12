package com.vengeful.sloths.Controllers.InputController.InputStrategies;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 3/10/2016.
 */
public class AdaptableStrategy extends InputStrategy implements ModelVisitable {

    HashMap<Integer, KeyMapping> keyMappings = new HashMap<>();

    public AdaptableStrategy(){
        //need to preset some keys
        setKeyMappings(KeyEvent.VK_W, KeyMapping.NORTH);
        setKeyMappings(KeyEvent.VK_S, KeyMapping.SOUTH);
        setKeyMappings(KeyEvent.VK_Q, KeyMapping.NORTHWEST);
        setKeyMappings(KeyEvent.VK_E, KeyMapping.NORTHEAST);
        setKeyMappings(KeyEvent.VK_A, KeyMapping.SOUTHWEST);
        setKeyMappings(KeyEvent.VK_D, KeyMapping.SOUTHEAST);
        setKeyMappings(KeyEvent.VK_SPACE, KeyMapping.SPACE);
        setKeyMappings(KeyEvent.VK_ENTER, KeyMapping.ENTER);
        setKeyMappings(KeyEvent.VK_ESCAPE, KeyMapping.ESC);
        setKeyMappings(KeyEvent.VK_UP, KeyMapping.UP);
        setKeyMappings(KeyEvent.VK_DOWN, KeyMapping.DOWN);
        setKeyMappings(KeyEvent.VK_LEFT, KeyMapping.LEFT);
        setKeyMappings(KeyEvent.VK_RIGHT, KeyMapping.RIGHT);
        setKeyMappings(KeyEvent.VK_I, KeyMapping.INVENTORY);
        setKeyMappings(KeyEvent.VK_P, KeyMapping.SAVE);
        //setKeyMappings(KeyEvent.VK_S, KeyMapping.SOUTH);
    }

    public AdaptableStrategy(boolean createFromLoad){

    }

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

    public int getKeyCode(KeyMapping mapping){
        for(Map.Entry<Integer, KeyMapping> entry : keyMappings.entrySet()){
            if(entry.getValue().equals(mapping)){
                return entry.getKey();
            }
        }
        System.out.println("keybind error");
        return -1;
    }

    public void setKeyMappings(int key, KeyMapping value){
        //maybe add logic if that key is already mapped somehwhere
        if(keyMappings.containsKey(key)){
            //need to swap
            KeyMapping tmp = keyMappings.get(key);
            int tmpkey = this.getKeyCode(value);
            if(tmpkey != -1){
                keyMappings.put(tmpkey, tmp);
            }else{
                //idk but we need to notify the view somehow
            }
            keyMappings.put(key, value);
        }else {
            keyMappings.put(key, value);
        }
    }

    public HashMap<Integer, KeyMapping> getKeyMappings() {
        return keyMappings;
    }

    public void setKeyMappings(HashMap<Integer, KeyMapping> keyMappings) {
        this.keyMappings = keyMappings;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAdaptableStrategy(this);
    }
}
