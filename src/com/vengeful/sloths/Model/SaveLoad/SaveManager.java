package com.vengeful.sloths.Model.SaveLoad;

/**
 * Created by Ian on 2/21/2016.
 */
public class SaveManager {
    public void save(){
        SaveVisitor sv = new SaveVisitor(string fileName);
        Map.vist(sv);
    }
}
