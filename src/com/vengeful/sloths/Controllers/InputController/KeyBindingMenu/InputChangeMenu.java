package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Menu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Created by John on 3/10/2016.
 */
public class InputChangeMenu extends ScrollableMenu {


    private BufferedImage bg;

    public InputChangeMenu(int height){
        super(height);
        //create list here for main menu
        try {
            bg = ImageIO.read(new File("resources/backgrounds/mainMenuBg.png"));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new NewGameMenuItem());
        list.addItem(new MainMenuItem("Load Game"));
        list.addItem(new ExitGameMenuItem());
        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setBounds(100, 100, 1000, 800);
        //this.setPreferredSize(new Dimension(1000,800));
        this.setPadding(100);

    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("painting");
        super.paintComponent(g);

        g.setFont(new Font("Helvetica",1,20));
        int offset = padding/2;
        int index = 0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bg,0,0,1200,1000, null);
        Iterator iter = list.getIterator();
        while (iter.hasNext()) {
            if(index == list.getCurrentIndex()){
                System.out.println(list.getCurrentIndex());
                ScrollableMenuItem current = (ScrollableMenuItem) iter.next();
                g2d.setColor(new Color(255, 0, 255, 80));
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
