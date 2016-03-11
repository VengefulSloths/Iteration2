package com.vengeful.sloths.Menu;

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
            bg = ImageIO.read(new File("resources/backgrounds/mainMenuBg.png"));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new NewGameMenuItem());
        //needs to be edited to select save file
        list.addItem(new LoadGameMenuItem("Load Game"));
        list.addItem(new ExitGameMenuItem());
        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setPadding(600);

    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("painting");
        super.paintComponent(g);

        g.setFont(new Font("Helvetica",1,50));
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
