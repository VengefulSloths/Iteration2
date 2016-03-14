package com.vengeful.sloths.Menu.MainMenu;


import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Menu.ScrollableMenu;

import java.awt.*;

public class MenuTester {

    public static void main(String[] args) {
	// write your code here
        //JFrame window = new JFrame("Test Window");
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

        mainController.setMainMenuControllerState(menu);

        window.start();
    }
}
