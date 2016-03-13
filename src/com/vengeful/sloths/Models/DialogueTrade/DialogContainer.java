package com.vengeful.sloths.Models.DialogueTrade;

import com.vengeful.sloths.Models.Entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/12/2016.
 */
public abstract class DialogContainer extends DialogObservable {
    private Entity speaker;
    private int cursor = 0;
    private ArrayList<String> dialogList = new ArrayList<>();
    Iterator<DialogObserver> iter;

    private String name;

    public DialogContainer(String name){
        this.name = name;
    }
    public DialogContainer(Entity speaker){
        this.speaker = speaker;
        this.name = speaker.getName();
    }

    public void next(){
        //check to see if terminal
        if(cursor == dialogList.size()){
            cursor = 0;
            terminalAction();
            return;
        }
        //if not then alert next string
        iter = this.getObserversIterator();
        while(iter.hasNext()){
            DialogObserver current = iter.next();
            current.alertDialogChange(dialogList.get(cursor), name);
        }
        ++cursor;
    }

    protected abstract void terminalAction();

    public void appendDialog(String nextDialog) {
        dialogList.add(nextDialog);
    }

    public Entity getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Entity speaker) {
        this.speaker = speaker;
    }


}
