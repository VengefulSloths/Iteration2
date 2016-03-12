package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

import java.awt.*;

/**
 * Created by John on 3/10/2016.
 */
public class KeyBindMenuItem implements ScrollableMenuItem {
    private String name;
    private KeyMapping keyMapping;

    public KeyBindMenuItem(String name, KeyMapping keyMapping){
        this.name = name;
        this.keyMapping = keyMapping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void select(ScrollableMenuItemCommand command) {
        //do something
        command.execute();
    }

    @Override
    public void paintComponent(Graphics2D g2d, int xPos, int yPos, int width, int height) {
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString(this.name, width / 2 - metrics.stringWidth(this.name)/2, yPos + height/2 + metrics.getAscent()/2);
    }

    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    public void setKeyMapping(KeyMapping keyMapping) {
        this.keyMapping = keyMapping;
    }
}
