package com.vengeful.sloths.Models.DialogueTrade;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/12/2016.
 */
public abstract class DialogContainer extends DialogObservable {

    private int cursor = 0;
    private ArrayList<String> dialogList = new ArrayList<>();
    Iterator<DialogObserver> iter;


    public DialogContainer(){
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
            current.alertDialogChange(dialogList.get(cursor));
        }
        ++cursor;
    }

    protected abstract void terminalAction();

    public void appendDialog(String nextDialog) {
        dialogList.add(nextDialog);
    }


}
