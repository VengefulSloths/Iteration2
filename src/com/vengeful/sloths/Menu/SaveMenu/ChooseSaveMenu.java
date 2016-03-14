package com.vengeful.sloths.Menu.SaveMenu;


import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuList;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by harrison on 3/12/16.
 */
public class ChooseSaveMenu extends ScrollableMenu {

    public ChooseSaveMenu(int height){
        super(height);
        //create list here for main menu

        ScrollableMenuList list = new ScrollableMenuList();
        //list.addItem(new NewGameMenuItem());
        //needs to be edited to select save file
        list.addItem(new ChooseSaveMenuItem("Save 1"));
        list.addItem(new ChooseSaveMenuItem("Save 2"));
        list.addItem(new ChooseSaveMenuItem("Save 3"));

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
