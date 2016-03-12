package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Menu.*;
import com.vengeful.sloths.Utility.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Created by John on 3/10/2016.
 */
public class InputChangeMenu extends ScrollableMenu {

    private AdaptableStrategy adaptableStrategy;
    private BufferedImage bg;

    public InputChangeMenu(int height){
        super(height);
        adaptableStrategy = (AdaptableStrategy) MainController.getInstance().getInputStrategy();
        //create list here for main menu
        try {
            bg = ImageIO.read(new File("resources/test_bg.png"));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new KeyBindMenuItem("Abilities", KeyMapping.ABILITIES));
        list.addItem(new KeyBindMenuItem("Inventory", KeyMapping.INVENTORY));
        list.addItem(new KeyBindMenuItem("Equipment", KeyMapping.INVENTORY));
        list.addItem(new KeyBindMenuItem("Move North", KeyMapping.NORTH));
        list.addItem(new KeyBindMenuItem("Move NorthEast", KeyMapping.NORTHEAST));
        list.addItem(new KeyBindMenuItem("Move NorthWest", KeyMapping.NORTHWEST));
        list.addItem(new KeyBindMenuItem("Move South", KeyMapping.SOUTH));
        list.addItem(new KeyBindMenuItem("Move SouthEast", KeyMapping.SOUTHEAST));
        list.addItem(new KeyBindMenuItem("Move SouthWest", KeyMapping.SOUTHWEST));
        list.addItem(new KeyBindMenuItem("Ability 0", KeyMapping.ABILITY_0));
        list.addItem(new KeyBindMenuItem("Ability 1", KeyMapping.ABILITY_1));
        list.addItem(new KeyBindMenuItem("Ability 2", KeyMapping.ABILITY_2));
        list.addItem(new KeyBindMenuItem("Ability 3", KeyMapping.ABILITY_3));

        this.setList(list);
        //this.setBackground(Color.GRAY);
        this.setBounds(100, 100, 1000, 800);
        //this.setPreferredSize(new Dimension(1000,800));
        this.setPadding(100);

    }

    @Override
    protected void paintComponent(Graphics g) {
       // System.out.println("painting");
        super.paintComponent(g);

        g.setFont(new Font("Helvetica",1,20));
        int offset = padding/2;
        int index = 0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        //g2d.fillRect(0,0,1200,1000);
        //g2d.drawImage(bg,0,0,1200,1000, null);
        g2d.drawImage(bg, 0, 0, Config.instance().getWindowWidth(), Config.instance().getWindowHeight(), null);
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

    public KeyBindMenuItem getMenuItem(){
        return (KeyBindMenuItem) list.getCurrentMenuItem();
    }


    public void refreshKeys(){
        Iterator<ScrollableMenuItem> iter = list.getIterator();
        while(iter.hasNext()){
            ((KeyBindMenuItem)iter.next()).updateKeyBind();
        }
    }
}
