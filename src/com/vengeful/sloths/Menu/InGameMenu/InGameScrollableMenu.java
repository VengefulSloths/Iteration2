package com.vengeful.sloths.Menu.InGameMenu;

import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.EnterKeyBindViewMenuItem;
import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.KeyBindMenuItem;
import com.vengeful.sloths.Menu.MainMenu.ExitGameMenuItem;

import com.vengeful.sloths.Menu.MainMenu.LoadMenu.LoadGameMenuItem;
import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.MainMenu.NewGameMenuItem;
import com.vengeful.sloths.Menu.SaveMenu.SaveGameMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Created by John on 3/11/2016.
 */
public class InGameScrollableMenu extends ScrollableMenu {
    //private BufferedImage bg;

    public InGameScrollableMenu(int height){
        super(height);
        //create list here for main menu

        ScrollableMenuList list = new ScrollableMenuList();
        //list.addItem(new NewGameMenuItem());
        //needs to be edited to select save file
        list.addItem(new EnterKeyBindViewMenuItem());//TODO change this to an actual keybinding item
        list.addItem(new SaveGameMenuItem("Save Game"));
        //list.addItem(new LoadGameMenuItem("Load Game"));
        list.addItem(new ExitToMainMenuItem());
        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(1200,1000));
        //this.setBounds(300, 200, 600, 600); //try this
        this.setPadding(600);
        this.setBackground(new Color(0f,0f,0f,0.5f));

    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("painting");
        super.paintComponent(g);

        g.setFont(new Font("Helvetica",1,50));
        int offset = padding/2;
        int index = 0;
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(bg,300,200,600,600, null);
        Iterator iter = list.getIterator();
        while (iter.hasNext()) {
            if(index == list.getCurrentIndex()){
                //System.out.println(list.getCurrentIndex());
                ScrollableMenuItem current = (ScrollableMenuItem) iter.next();
                g2d.setColor(new Color(0, 0, 255, 80));
                g2d.fillRect(padding/2, offset , this.getWidth() - padding, itemHeight);
                current.paintComponent(g2d, padding , offset, this.getWidth(), itemHeight);
            }else{
                ScrollableMenuItem current = (ScrollableMenuItem) iter.next();
                current.paintComponent(g2d, padding, offset, this.getWidth(), itemHeight);
            }
            offset += itemHeight;
            index++;
        }
    }
}
