package com.vengeful.sloths.Menu.MainMenu;

import com.vengeful.sloths.Menu.MainMenu.LoadMenu.ChooseLoadMenuItem;
import com.vengeful.sloths.Menu.MainMenu.LoadMenu.LoadGameMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuList;
import com.vengeful.sloths.Utility.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Created by harrison on 3/13/16.
 */
public class MainMenuLoad extends MainScrollableMenu{


    public MainMenuLoad(int height){
        super(height);
        //create list here for main menu
        try {
            this.setBg(ImageIO.read(new File("resources/backgrounds/mainMenuBg.png")));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new ChooseLoadMenuItem("Save 1"));
        //needs to be edited to select save file
        list.addItem(new ChooseLoadMenuItem("Save 2"));
        list.addItem(new ChooseLoadMenuItem("Save 3"));
        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setPadding(600);

    }


}
