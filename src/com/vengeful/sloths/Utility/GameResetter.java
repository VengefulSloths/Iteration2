package com.vengeful.sloths.Utility;

import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Menu.MainMenu.MenuTester;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by John on 3/13/2016.
 */
public class GameResetter {

    public static void reset(){
        ModelEngine.getInstance().pauseGame();
        TimeModel.getInstance().reset();
        ViewTime.getInstance().reset();
        ViewEngine.getInstance().unpauseView();
        Avatar.getInstance().setStats(new Stats());
        Avatar.getInstance().setDead(false);
        //MainController.getInstance().reRegisterTickable();
        Avatar.getInstance().reset();
        Map.getInstance().reset();
        MenuTester.main(null);
    }
}
