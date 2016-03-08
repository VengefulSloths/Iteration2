package com.vengeful.sloths;

import Menu.MainScrollableMenu;
import Menu.ScrollableMenu;
import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.Map.*;

import java.awt.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ViewEngine window = ViewEngine.getInstance();

        //make test list
        //ScrollableMenuList list = new ScrollableMenuList();


        ScrollableMenu menu = new MainScrollableMenu(120);



        menu.setPreferredSize(new Dimension(80, 600));
        //menu.setBackground(Color.green);
        //menu.setBorder(BorderFactory.createBevelBorder(1, Color.ORANGE, Color.ORANGE));
        window.registerView(menu);
        //window.setSize(1200, 1000);

        MainController mainController = MainController.getInstance();

        mainController.setMenuControllerState(menu);

        window.start();

    }
}
