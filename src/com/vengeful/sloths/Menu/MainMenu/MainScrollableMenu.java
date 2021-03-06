package com.vengeful.sloths.Menu.MainMenu;

import com.vengeful.sloths.Menu.MainMenu.LoadMenu.ChooseLoadMenuItem;
import com.vengeful.sloths.Menu.MainMenu.LoadMenu.LoadGameMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuList;
import com.vengeful.sloths.Utility.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Created by John on 3/7/2016.
 */
public class MainScrollableMenu extends ScrollableMenu {

    private BufferedImage bg;

    public MainScrollableMenu(int height){
        super(height);
        //create list here for main menu
        try {
            bg = ImageIO.read(new File("resources/backgrounds/sky_main_title.png"));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new NewGameMenuItem());
        //needs to be edited to select save file
        list.addItem(new MainMenuLoadItem("Load Game", this));
        list.addItem(new ExitGameMenuItem());
        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setPadding(600);

    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("painting");
        super.paintComponent(g);

        g.setFont(new Font("Helvetica",1,50));
        int offset = padding/2;
        int index = 0;
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(bg,0,0,1200,1000, null);
        g2d.drawImage(bg,0,0, Config.instance().getWindowWidth(), Config.instance().getWindowHeight(), null);
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

    public BufferedImage getBg() {
        return bg;
    }

    public void setBg(BufferedImage bg) {
        this.bg = bg;
    }

    public void setMenuList(){
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new NewGameMenuItem());
        //needs to be edited to select save file
        list.addItem(new MainMenuLoadItem("Load Game", this));
        list.addItem(new ExitGameMenuItem());
        this.setList(list);
    }
    public void setLoadList(){
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new ChooseLoadMenuItem("Save 1"));
        //needs to be edited to select save file
        list.addItem(new ChooseLoadMenuItem("Save 2"));
        list.addItem(new ChooseLoadMenuItem("Save 3"));
        this.setList(list);
    }
}
